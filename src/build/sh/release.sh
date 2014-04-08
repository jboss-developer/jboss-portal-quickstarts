#!/bin/bash
# prerequisites on fedora:
# perl-XML-XPath

set -e
set -x

mvnSettings="$1"

oldSnapshotVersion=$(xpath pom.xml "/project/version/text()" 2>/dev/null)
taggedVersion=$(echo "${oldSnapshotVersion}" | sed 's/-SNAPSHOT//')
tag="${taggedVersion}"
qsNo=$(echo "${oldSnapshotVersion}" | sed 's/.*.build-\([0-9]*\)-.*/\1/')
newSnapshotVersion=${oldSnapshotVersion/build-${qsNo}/build-$((qsNo + 1))}

die() { echo "$@" 1>&2 ; exit 1; }

# ensure we are on 6.2.x-develop
currentBranch=$(git rev-parse --abbrev-ref HEAD)
if [ "${currentBranch}" != "6.2.x-develop" ]; then
    die "You must be on 6.2.x-develop branch to run $0"
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

mvn install -P prepare-zips --settings "$mvnSettings"

echo -e "You might want to push now:\ngit push upstream ${currentBranch} && git push upstream ${tag}"
