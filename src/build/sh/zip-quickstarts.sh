#!/bin/bash

set -e
set -x

QUICKSTARTS_ROOT_DIR="$1"
OUTPUT_ZIP="$2"

ZIPPED_DIR="$QUICKSTARTS_ROOT_DIR/target/assembly-prepare/$(basename "$OUTPUT_ZIP" .zip)"

rm -Rf "$QUICKSTARTS_ROOT_DIR/target/assembly-prepare"
mkdir -p "$ZIPPED_DIR"

function stripInclusionComments {
    for f in "$@"
    do
        grep -v "<\!--~.*~-->" "$f" > "$f.grep.tmp"
        mv "$f.grep.tmp" "$f"
    done
}

cd "$QUICKSTARTS_ROOT_DIR"
# make the path absolute to be able to cd to it again
QUICKSTARTS_ROOT_DIR="$(pwd)"
find . -type f \
    | grep -v "/dist/" | grep -v "/target/" | grep -v "\./src/" | grep -v "\./pom.xml$" \
    | grep -v "/\.[^/][^/]*$" | grep -v "/\.[^/][^/]*/" \
    | xargs tar cf - | (cd "$ZIPPED_DIR" && tar xf - )

cd "$ZIPPED_DIR"

for f in $(find . -iname readme.md -type f)
do
    stripInclusionComments "$f"
done

cd "$QUICKSTARTS_ROOT_DIR/target/assembly-prepare"

zip -rq "$OUTPUT_ZIP" "$(basename "$ZIPPED_DIR")"
