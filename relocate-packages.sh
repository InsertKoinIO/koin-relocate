#!/bin/bash

# TODO extract as variable
# Directory containing the Koin source code you want to repackage.
KOIN_SRC_DIR="$KOIN_PROJECTS_DIR" #"/Users/arnaud/workspace/koin-projects/koin/projects"


if [ "$#" -ne 1 ]; then
    echo "Usage: $0 <relocation-prefix>"
    exit 1
fi

RELOCATION_PREFIX="$1"

TARGET_PACKAGE="$RELOCATION_PREFIX.koin"

echo "Relocating packages from org.koin to $TARGET_PACKAGE"

TARGET_PACKAGE_PATH=$(echo $TARGET_PACKAGE | tr '.' '/')

# 1. Replace package declarations and import statements in all Kotlin files.
find "$KOIN_SRC_DIR" -type f -name "*.kt" -print0 | while IFS= read -r -d '' file; do
    # Use sed to replace 'org.koin' with your target package.
    sed -i.bak "s/org\.koin/${TARGET_PACKAGE//./\\.}/g" "$file"
    # Optionally remove backup files
    rm "$file.bak"
done

# 2. Move the directory structure:
#    All files under .../org/koin/ should be moved to .../io/your/sdk/koin/
if [ -d "$KOIN_SRC_DIR/org/koin" ]; then
    mkdir -p "$KOIN_SRC_DIR/$TARGET_PACKAGE_PATH"
    mv "$KOIN_SRC_DIR/org/koin/"* "$KOIN_SRC_DIR/$TARGET_PACKAGE_PATH/"
    # Optionally, remove the now-empty original directories.
    rm -rf "$KOIN_SRC_DIR/org"
fi

echo "Relocating packages $TARGET_PACKAGE complete."
