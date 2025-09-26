#!/bin/bash

# Simple launcher for the demo

set -e

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$SCRIPT_DIR"

# Ensure local jax-rs-pac4j SNAPSHOT is installed
mvn -q -f ../jax-rs-pac4j/pom.xml -DskipTests install || true

mvn -q clean package
java -jar target/jax-rs-pac4j-demo-1.0.0-SNAPSHOT.jar
