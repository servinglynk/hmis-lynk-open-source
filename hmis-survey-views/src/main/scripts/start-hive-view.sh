#!/usr/bin/env bash
get_abs_script_path() {
pushd . >/dev/null
cd $(dirname $0)
dir=$(pwd)
popd  >/dev/null
}

get_abs_script_path
parentdir="$(dirname "$dir")"
logsFolder=$parentdir/logs
if [ ! -d "$logsFolder" ]; then
    echo "Creating logs folder"$logsFolder
    mkdir $logsFolder
fi


export CLASSPATH_PREFIX=$parentdir"/conf"

echo "Start hive view process"

export JAVA_OPTS="-XX:MaxPermSize=1024m -Dlog4j.debug=true -Dlog4j.configuration=log4j-hive-view.properties"
$dir/main-hive-view.sh </dev/null > $logsFolder/hive-view.log 2>&1 &
