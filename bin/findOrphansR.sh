#!/bin/bash
#

find /home/hliu/git-repo/gerrit/gerrit_testsite/git/ -type d -name "pack"  | while read line; do
     /home/hliu/git-repo/test/mytool/build/distributions/mytool-0.0.3-SNAPSHOT/bin/start_my_tool.sh git -action findOrphans -folder ${line}
done
