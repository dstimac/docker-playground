#!/bin/bash

rm -rf build/*

pushd ../../../rest/
sbt clean pack
cp target/pack/* ../docker/rest/server/build/ -r
popd
