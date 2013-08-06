#!/bin/bash

set -e
set -x

mvnSettings="$1"
pupstreamVersion="$2"
redhatBomVersion="$3"

pupstreamTag="gatein-quickstart-${pupstreamVersion}"
prodNo=$(echo "${pupstreamVersion}" | sed 's/.*-prod-\([0-9]*\)/\1/')
redhatVersion=$(echo "${pupstreamVersion}" | sed 's/-prod-\([0-9]*\)/-redhat-\1/')
redhatVersionNext=$(echo "${pupstreamVersion}" | sed "s/-prod-\([0-9]*\)/-redhat-$((prodNo + 1))-SNAPSHOT/")
redhatBranch="${pupstreamTag}-redhat"
redhatTag="gatein-quickstart-${redhatVersion}"

git fetch --tags pupstream
git checkout -b "${redhatBranch}" "${pupstreamTag}"

./src/build/sh/mvn-property-set.sh "${mvnSettings}" "version.jboss.gatein.bom" "${redhatBomVersion}"


./src/build/sh/mvn-tag.sh "$mvnSettings" "${redhatVersion}" "${redhatVersionNext}"
mvn clean install -P prepare-zips,product --settings "$mvnSettings"

echo -e "You might want to push now:\ngit push prod ${redhatBranch}\ngit push prod ${redhatTag}"
