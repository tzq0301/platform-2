#!/usr/bin/env bash

set -euo pipefail

# ------------------------------------------------------------
# 部署
# ------------------------------------------------------------

echo "action=upload file=py-scripts.tar.gz"

task_id=$(http --body -f POST localhost:9200/deploy/upload projectId=1 serviceName=py-scripts dependentTaskIds= machineId=1 file@py-scripts.tar.gz | yq ".data.taskId")

echo "install task_id=$task_id"

http --body POST localhost:9200/deploy/install taskId="$task_id" &> /dev/null

echo