#!/bin/bash

set -e
set -x

mvnSettings="$1"

oldSnapshotVersion=$(xpath pom.xml "/project/version/text()" 2>/dev/null)
taggedVersion=${oldSnapshotVersion#-SNAPSHOT}
tag="gatein-quickstart-${taggedVersion}"
qsNo=$(echo "${oldSnapshotVersion}" | sed 's/.*-qs-\([0-9]*\)-.*/\1/')
newSnapshotVersion=${oldSnapshotVersion/qs-${qsNo}/qs-$((qsNo + 1))}

die() { echo "$@" 1>&2 ; exit 1; }

# ensure we are on master
currentBranch=$(git rev-parse --abbrev-ref HEAD)
if [ "${currentBranch}" != "master" ]; then
    die "You must be on master branch to run $0"
fi
if ! git diff-index --cached --quiet HEAD --; then
    die "There must not be any pending changes to run $0"
fi

mvn clean install -P generate-readmes --settings "$mvnSettings"
./src/build/sh/md2html.sh

if ! git diff-index --cached --quiet HEAD --; then
    die "generate-readmes caused changes. Commit manually and re-run $0"
fi

./src/build/sh/mvn-tag.sh "$mvnSettings" "${taggedVersion}" "${newSnapshotVersion}"

mvn clean install -P prepare-zips --settings "$mvnSettings"


echo "You might want to push now:\ngit push upstream master && git push -u origin master && git push upstream ${tag}"
