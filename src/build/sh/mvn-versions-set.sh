#!/bin/bash

set -e
set -x

mvnSettings="$1"
newVersion="$2"

rootDir="$(pwd)"

mvn versions:set --settings "$mvnSettings" "-DnewVersion=${newVersion}" -DgenerateBackupPoms=false
for projectDir in $(find "$(pwd)" -maxdepth 1 -type d -not -name ".*" -not -name "target" -not -name "src"); do
        echo "${projectDir}"
        cd "${projectDir}"
        mvn versions:set --settings "$mvnSettings" "-DnewVersion=${newVersion}" -DgenerateBackupPoms=false
done
