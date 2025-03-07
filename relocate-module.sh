#!/bin/bash

# Directory containing the Koin source code you want to repackage.
KOIN_SRC_DIR="$KOIN_PROJECTS_DIR/" #"/Users/arnaud/workspace/koin-projects/koin/projects/"

# rename_gradle_module.sh
#
# Usage:
#   ./rename_gradle_module.sh <old-module-relative-path> <new-module-relative-path>
#
# Example:
#   ./rename_gradle_module.sh /core/koin-core /core/relocated-koin-core
#
# This script:
# 1. Updates settings.gradle.kts to replace the module path.
# 2. Updates all Gradle build files (.gradle.kts and .gradle) in the project.
# 3. Renames the physical module directory.

if [ "$#" -ne 2 ]; then
    echo "Usage: $0 <old-module-relative-path> <new-module-relative-path>"
    exit 1
fi

# Get the old and new module relative paths.
OLD_MODULE_REL="$1"
NEW_MODULE_REL="$2"

# Assume the script is executed from the project root.
PROJECT_ROOT=$KOIN_SRC_DIR

# Define the settings file path.
SETTINGS_FILE="$PROJECT_ROOT/settings.gradle.kts"
if [ ! -f "$SETTINGS_FILE" ]; then
    echo "Error: settings.gradle.kts not found in project root."
    exit 1
fi

# Remove any leading slash from the module paths.
OLD_PATH="${OLD_MODULE_REL#/}"
NEW_PATH="${NEW_MODULE_REL#/}"

# Convert directory paths to Gradle module notation.
# e.g. "core/koin-core" becomes ":core:koin-core"
OLD_GRADLE_MODULE=":${OLD_PATH//\//:}"
NEW_GRADLE_MODULE=":${NEW_PATH//\//:}"

echo "Renaming module ${OLD_GRADLE_MODULE} to ${NEW_GRADLE_MODULE}"

# 1. Update settings.gradle.kts
sed -i.bak "s|$OLD_GRADLE_MODULE|$NEW_GRADLE_MODULE|g" "$SETTINGS_FILE"
rm "$SETTINGS_FILE.bak"

# 2. Update all Gradle build files (.gradle.kts and .gradle) in the project.
find "$PROJECT_ROOT" -type f \( -name "*.gradle.kts" -o -name "*.gradle" \) -print0 | while IFS= read -r -d '' file; do
    sed -i.bak "s|$OLD_GRADLE_MODULE|$NEW_GRADLE_MODULE|g" "$file"
    rm "$file.bak"
done

# 3. Rename the physical module directory.
OLD_MODULE_DIR="$PROJECT_ROOT$OLD_MODULE_REL"
NEW_MODULE_DIR="$PROJECT_ROOT$NEW_MODULE_REL"

# Create parent directory for the new module directory if it doesn't exist.
NEW_PARENT_DIR=$(dirname "$NEW_MODULE_DIR")
mkdir -p "$NEW_PARENT_DIR"

if [ -d "$OLD_MODULE_DIR" ]; then
    mv "$OLD_MODULE_DIR" "$NEW_MODULE_DIR"
    echo "Renamed directory from $OLD_MODULE_DIR to $NEW_MODULE_DIR"
else
    echo "Error: Module directory $OLD_MODULE_DIR not found."
    exit 1
fi

echo "Module renaming to ${NEW_GRADLE_MODULE} complete."
