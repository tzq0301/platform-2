#!/usr/bin/env bash

set -euo pipefail

WORKDIR=$PWD
DATADIR=$WORKDIR/data
SOURCE_1_DIRNAME=demo-1
SOURCE_2_DIRNAME=demo-2
TARFILE_1=$DATADIR/$SOURCE_1_DIRNAME.tar.gz
TARFILE_2=$DATADIR/$SOURCE_2_DIRNAME.tar.gz

rm -f $TARFILE_1

cd $DATADIR/$SOURCE_1_DIRNAME
tar -zcf $TARFILE_1 *

cd $DATADIR/$SOURCE_2_DIRNAME
tar -zcf $TARFILE_2 *

cd $WORKDIR

# ------------------------------------------------------------

task_id=$(http --body -f POST localhost:9200/deploy/upload projectId=1 serviceName=demo-1 dependentTaskIds= machineId=1 file@$TARFILE_1 | yq ".taskId")

echo $task_id

# ------------------------------------------------------------

http POST localhost:9200/deploy/install taskId=$task_id

# ------------------------------------------------------------

task_id=$(http --body -f POST localhost:9200/deploy/upload projectId=1 serviceName=demo-2 dependentTaskIds=$task_id machineId=1 file@$TARFILE_2 | yq ".taskId")

echo $task_id

# ------------------------------------------------------------

http POST localhost:9200/deploy/install taskId=$task_id

# ------------------------------------------------------------

bash test-projects.sh
