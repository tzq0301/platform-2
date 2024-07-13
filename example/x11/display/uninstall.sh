#!/usr/bin/env bash

set -euo pipefail

pgrep "$(cat pid)" || exit

kill "$(cat pid)"
