#!/bin/bash
#
# controlling script for my-tool

readonly APP_HOME=$(dirname $(dirname $(readlink -f ${0})))
readonly APP_NAME=mytool
readonly APP_JAR="${APP_HOME}/libs/${APP_NAME}-*.jar"
readonly LOG_CONFIG="${APP_HOME}/conf/log4j2.xml"

java -Dlog4j.configurationFile=${LOG_CONFIG} -jar ${APP_JAR} $1 $2 $3 $4 $5 $6 $7 $8 $9