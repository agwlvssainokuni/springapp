#!/bin/bash

curdir=$(cd $(dirname ${BASH_SOURCE[0]}); pwd)
cd ${curdir}

exec mvn clean process-resources flyway:migrate querydsl:export
