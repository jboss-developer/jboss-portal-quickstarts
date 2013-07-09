#!/bin/bash

set -e
#set -x

mvnSettings="$1"
upstreamVersion="$2"

upstreamTag="gatein-quickstart-${upstreamVersion}"
prodVersion="${upstreamVersion}-prod-1"
prodVersionNext="${upstreamVersion}-prod-2-SNAPSHOT"
prodTag="gatein-quickstart-${prodVersion}"

git fetch upstream
git checkout -b "${upstreamTag}" "${upstreamTag}"

mvn clean install -P generate-readmes,product --settings "$mvnSettings"
./src/build/sh/md2html.sh
git commit -a -m "Productization - the engineering part"
./src/build/sh/mvn-tag.sh "$mvnSettings" "${prodVersion}" "${prodVersionNext}"
echo "You might want to push now:\ngit push pupstream master && git push pupstream ${prodTag}"