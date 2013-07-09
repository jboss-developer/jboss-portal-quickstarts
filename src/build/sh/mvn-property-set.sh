#!/bin/bash

set -e
#set -x

mvnSettings="$1"
propertyName="$2"
propertyValue="$3"

rootDir="$(pwd)"

projectDir="${rootDir}"
sed "s|<${propertyName}>[^<]*</${propertyName}>|<${propertyName}>${propertyValue}</${propertyName}>|g" "${rootDir}/pom.xml" > "${rootDir}/pom.tmp.xml"
mv "${projectDir}/pom.tmp.xml" "${projectDir}/pom.xml"
for projectDir in $(find "$(pwd)" -maxdepth 1 -type d -not -name ".*" -not -name "target" -not -name "src"); do
    #echo "${projectDir}"
    cd "${projectDir}"
    sed "s|<${propertyName}>[^<]*</${propertyName}>|<${propertyName}>${propertyValue}</${propertyName}>|g" "${projectDir}/pom.xml" > "${projectDir}/pom.tmp.xml"
    mv "${projectDir}/pom.tmp.xml" "${projectDir}/pom.xml"
done
