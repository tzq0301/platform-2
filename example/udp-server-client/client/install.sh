#!/usr/bin/env bash

set -euo pipefail

nohup java -jar client.jar > log 2>&1 &

echo $! > pid
