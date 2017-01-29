#!/bin/bash
#

#find /home/hliu/git-repo/gerrit/gerrit_testsite/git/ -type d -name "pack"  | while read line; do
#     /home/hliu/git-repo/test/mytool/build/distributions/mytool-0.0.3-SNAPSHOT/bin/start_my_tool.sh git -action findOrphans -folder ${line}
#done

readonly APP_HOME=$(dirname $(dirname $(readlink -f ${0})))
readonly GIT_FOLDER="/home/hliu/git-repo/gerrit/2.12.gerrit_testsite/git"

${APP_HOME}/bin/start_my_tool.sh git -action findOrphans \
  -folder ${GIT_FOLDER} -recursive