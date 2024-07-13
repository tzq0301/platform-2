#!/usr/bin/env bash

set -euo pipefail

nohup bash display.sh > log 2>&1 &

echo $! > pid
