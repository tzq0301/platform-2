#!/usr/bin/env bash

set -euo pipefail

# ------------------------------------------------------------
# 部署 Server
# ------------------------------------------------------------

echo "action=upload file=server.tar.gz"

server_task_id=$(http --body -f POST localhost:9200/deploy/upload projectId=1 serviceName=udp-server dependentTaskIds= machineId=1 file@packages/server.tar.gz | yq ".data.taskId")

echo "install server_task_id=$server_task_id"

http --body POST localhost:9200/deploy/install taskId="$server_task_id" &> /dev/null

echo

# ------------------------------------------------------------
# 部署 Client
# ------------------------------------------------------------

echo "action=upload file=client.tar.gz"

client_task_id=$(http --body -f POST localhost:9200/deploy/upload projectId=1 serviceName=udp-client dependentTaskIds="$server_task_id" machineId=1 file@packages/client.tar.gz | yq ".data.taskId")

echo "install client_task_id=$client_task_id"

http --body POST localhost:9200/deploy/install "taskId=$client_task_id" &> /dev/null

echo

# ------------------------------------------------------------
# 查看项目详情
# ------------------------------------------------------------

http --body 127.0.0.1:9200/project/projects

# ------------------------------------------------------------
# 卸载 Client
# ------------------------------------------------------------

http --body POST localhost:9200/deploy/uninstall "taskId=$client_task_id"

# ------------------------------------------------------------
# 卸载 Server
# ------------------------------------------------------------

http --body POST localhost:9200/deploy/uninstall "taskId=$server_task_id"

# ------------------------------------------------------------
# 查看项目详情
# ------------------------------------------------------------

http --body 127.0.0.1:9200/project/projects
