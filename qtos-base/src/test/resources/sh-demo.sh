#!/usr/bin/env bash

set -euo pipefail

WORKDIR=$PWD
SOURCE=data/sh-demo
TARFILE=sh-demo.tar.gz

rm $TARFILE || true

cd $SOURCE
tar -zcf ../$TARFILE *
cd $WORKDIR

task_id=$(http --body -f POST localhost:9300/deploy/upload file@data/$TARFILE | yq '.taskId')
echo $task_id

http POST localhost:9300/deploy/install taskId="$task_id"
