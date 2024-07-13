#!/usr/bin/env bash

set -euo pipefail

# 需要在两台机器上都部署 qtos-base 底座

# ------------------------------------------------------------
# 部署 UI
# ------------------------------------------------------------

echo "upload ui.tar.gz"

ui_task_id=$(http --body -f POST localhost:9200/deploy/upload projectId=1 serviceName=simcity-ui dependentTaskIds= machineId=2 file@packages/ui.tar.gz | yq ".data.taskId")

echo "install ui_task_id=$ui_task_id"

http --body POST localhost:9200/deploy/install taskId="$ui_task_id" &> /dev/null

echo

# ------------------------------------------------------------
# 部署 DISPLAY
# ------------------------------------------------------------

echo "upload display.tar.gz"

display_task_id=$(http --body -f POST localhost:9200/deploy/upload projectId=1 serviceName=simcity-display dependentTaskIds="$ui_task_id" machineId=1 file@packages/display.tar.gz | yq ".data.taskId")

echo "install display_task_id=$display_task_id"

http --body POST localhost:9200/deploy/install taskId="$display_task_id" &> /dev/null
