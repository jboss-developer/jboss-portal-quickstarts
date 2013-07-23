#!/bin/bash

set -e
set -x

mvnSettings="$1"
redhatVersion="$2"

redhatTag="gatein-quickstart-${redhatVersion}"

jdfBranch="${redhatTag}-jdf"
jdfTag="${jdfBranch}-1"

git fetch --tags prod
git checkout -b "${jdfBranch}" "refs/tags/${redhatTag}"

rm -f "README.html"
for projectDir in $(find "$(pwd)" -maxdepth 1 -type d -not -name ".*" -not -name "target" -not -name "src"); do
        rm -f "${projectDir}/README.html"
done
git commit -a -m "Removed README.html files for JDF"
git tag -a "${jdfTag}" -m "Tagged ${jdfTag}"

echo -e "You might want to push now:\ngit push upstream ${jdfTag}"
