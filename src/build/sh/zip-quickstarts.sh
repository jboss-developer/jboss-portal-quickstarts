#!/bin/bash

set -e
#set -x

QUICKSTARTS_ROOT_DIR="$1"
OUTPUT_ZIP="$2"

rm -Rf "$QUICKSTARTS_ROOT_DIR/target/assembly-prepare"
mkdir -p "$QUICKSTARTS_ROOT_DIR/target/assembly-prepare"

function stripInclusionComments {
    for f in "$@"
    do
        grep -v "<\!--~.*~-->" "$f" > "$f.grep.tmp"
        mv "$f.grep.tmp" "$f"
    done
}

cd "$QUICKSTARTS_ROOT_DIR"
find . -type f \
    | grep -v "/dist/" | grep -v "/target/" | grep -v "\./src/" | grep -v "\./pom.xml$" \
    | grep -v "/\.[^/][^/]*$" | grep -v "/\.[^/][^/]*/" \
    | xargs tar cf - | (cd "$QUICKSTARTS_ROOT_DIR/target/assembly-prepare" && tar xf - )

cd "$QUICKSTARTS_ROOT_DIR/target/assembly-prepare"

for f in $(find . -iname readme.md -type f)
do
    stripInclusionComments "$f"
done

zip -rq "$OUTPUT_ZIP" .
