#!/usr/bin/env bash

mvn clean install
jar cmf ./META-INF/MANIFEST.FM scoped-values.jar -C ./target/classes/ .
