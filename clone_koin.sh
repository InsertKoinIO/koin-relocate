#!/bin/bash
# clone_koin.sh
#
# This script clones the Koin repository with a specified tag.
#
# Usage:
#   ./clone_koin.sh <tag> 
#
# The script uses the repository: git@github.com:InsertKoinIO/koin.git

# Check if a tag was provided
if [ "$#" -lt 1 ]; then
    echo "Usage: $0 <tag>"
    exit 1
fi

TAG="$1"
DEST_DIR="$KOIN_DIR"

REPO_URL="https://github.com/InsertKoinIO/koin.git"

echo "Cloning repository $REPO_URL with tag '$TAG' into directory '$DEST_DIR'..."

# Clone the repository using the provided tag.
# --single-branch ensures only the specified branch or tag is cloned.
# --depth 1 creates a shallow clone, which speeds up the process.
git clone --branch "$TAG" --depth 1 --single-branch "$REPO_URL" "$DEST_DIR"

if [ $? -eq 0 ]; then
    echo "Repository cloned successfully."
else
    echo "Error: Failed to clone repository."
    exit 1
fi
