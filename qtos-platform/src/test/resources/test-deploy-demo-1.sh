#!/usr/bin/env bash

set -euo pipefail

WORKDIR=$PWD
DATADIR=$WORKDIR/data
SOURCE_DIRNAME=demo-1
TARFILE=$DATADIR/$SOURCE_DIRNAME.tar.gz

rm -f $TARFILE

cd $DATADIR/$SOURCE_DIRNAME

tar -zcf $TARFILE *

cd $WORKDIR

# ------------------------------------------------------------

task_id=$(http --body -f POST localhost:9200/deploy/upload projectId=1 serviceName=demo-1 dependentTaskIds= machineId=1 file@$TARFILE | yq ".taskId")

echo $task_id

# ------------------------------------------------------------

http POST localhost:9200/deploy/install taskId=$task_id

# ------------------------------------------------------------

bash test-projects.sh
