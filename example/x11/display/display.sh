#!/usr/bin/env bash

set -euo pipefail

sshpass -p debian ssh -X debian@192.168.64.3 "java HelloWorldSwing"
