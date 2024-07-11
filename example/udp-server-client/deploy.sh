#!/usr/bin/env bash

set -euo pipefail

# ------------------------------------------------------------
# 部署 Server
# ------------------------------------------------------------

echo "upload  file=server.tar.gz"

task_id=$(http --check-status --body -f POST localhost:9200/deploy/upload projectId=1 serviceName=udp-server dependentTaskIds= machineId=1 file@packages/server.tar.gz | yq ".data.taskId")

echo "install task_id=$task_id"

http --check-status --body POST localhost:9200/deploy/install "taskId=$task_id" &> /dev/null

echo

# ------------------------------------------------------------
# 部署 Client
# ------------------------------------------------------------

echo "upload  file=client.tar.gz"

task_id=$(http --check-status --body -f POST localhost:9200/deploy/upload projectId=1 serviceName=udp-client dependentTaskIds="$task_id" machineId=1 file@packages/client.tar.gz | yq ".data.taskId")

echo "install task_id=$task_id"

http --check-status --body POST localhost:9200/deploy/install "taskId=$task_id" &> /dev/null

echo

# ------------------------------------------------------------
# 查看项目详情
# ------------------------------------------------------------

http --check-status --body 127.0.0.1:9200/project/projects
