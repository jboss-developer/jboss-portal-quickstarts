#!/bin/bash

set -e
#set -x

mvnSettings="$1"
taggedVersion="$2"
newVersion="$3"

rootDir="$(pwd)"

./src/build/sh/mvn-versions-set.sh "$mvnSettings" "${taggedVersion}"
tag="gatein-quickstart-${taggedVersion}"
msg="Tagged ${tag}"
git commit -a -m "${msg}"
git tag -a "${tag}" -m "${msg}"
./src/build/sh/mvn-versions-set.sh "$mvnSettings" "${newVersion}"
git commit -a -m "Prepare for next development iteration"
git checkout "${tag}"
