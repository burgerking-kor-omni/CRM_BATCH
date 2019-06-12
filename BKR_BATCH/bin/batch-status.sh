#!/bin/bash

PID=`ps -ef|grep java|grep UB-Batch|awk '{print $2}'`

if [ "x$PID" == "x" ]; then
  echo "not running"
else
  echo "running"
fi