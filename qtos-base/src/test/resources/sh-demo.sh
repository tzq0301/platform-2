#!/usr/bin/env bash

set -euo pipefail

WORKDIR=$PWD
SOURCE=data/sh-demo
TARFILE=sh-demo.tar.gz

rm $TARFILE || true

cd $SOURCE
tar -zcf ../$TARFILE *
cd $WORKDIR

http -f POST localhost:9300/deploy/upload file@data/$TARFILE
