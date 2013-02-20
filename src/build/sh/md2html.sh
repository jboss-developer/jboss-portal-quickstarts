#!/bin/bash

# prerequisistes:
#
# yum install -y ruby gcc ruby-devel libxml2 libxml2-devel libxslt libxslt-devel
# gem install redcarpet nokogiri pygments.rb 
# cd
# mkdir -p git
# cd git
# git clone https://github.com/jboss-jdf/jboss-as-quickstart.git


set -e
set -x

mdconv=~/git/jboss-as-quickstart/dist/github-flavored-markdown.rb

rm -Rf dist/target
mkdir -p dist/target
touch dist/target/toc.html

rootDir="$(pwd)"

#echo "rootDir=$rootDir"

# Loop through the sorted quickstart directories and process them
# Exclude the template directory since it's not a quickstart
subdirs=`find . -maxdepth 1 -type d ! -iname ".*" ! -iname "src" | sort`
for subdir in $subdirs
do
	readmes=`find $subdir -iname readme.md`
	for readme in $readmes
	do
		echo "Processing $readme"
		output_filename=${readme//.md/.html}
		output_filename=${output_filename//.MD/.html}
		"$mdconv" "$readme" > "$output_filename"
	done
done
# Now process the root readme
cd "$rootDir"
readme=README.md
echo "Processing $readme"
output_filename=${readme//.md/.html}
output_filename=${output_filename//.MD/.html}
"$mdconv" "$readme" > "$output_filename"

rm -Rf dist/target/toc.html
[ "$(ls -A dist/target)" ] && echo "dist/target will not be deleted as it is not empty." || rm -Rf dist/target
[ "$(ls -A dist)" ] && echo "dist will not be deleted as it is not empty." || rm -Rf dist
