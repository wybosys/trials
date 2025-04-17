#!/usr/bin/env bash

protoc -I=. --cpp_out=out.cpp --go_out=out.go --plugin=${GOPATH}/bin/protoc-gen-go test.proto
