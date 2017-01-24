#!/bin/bash
#
# controlling script for my-tool

readonly SCRIPT=$(readlink -f "${0}")
readonly SCRIPT_PATH=$(dirname "${SCRIPT}")
readonly SERVICE_NAME=my-tool
readonly PATH_TO_JAR="${SCRIPT_PATH}/../libs/${SERVICE_NAME}-*.jar"
readonly PATH_TO_LOG_CONFIG="${SCRIPT_PATH}/../conf/log4j2.xml"

java -Dlog4j.configurationFile=${PATH_TO_LOG_CONFIG} -jar ${PATH_TO_JAR} $1 $2 $3 $4 $5 $6 $7 $8 $9