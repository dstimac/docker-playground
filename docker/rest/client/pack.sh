#!/bin/bash

rm -rf build/*

pushd ../../../rest-client/
sbt clean pack
cp target/pack/* ../docker/rest/client/build/ -r
popd
