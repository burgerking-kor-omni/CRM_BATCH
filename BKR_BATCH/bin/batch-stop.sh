#!/bin/bash

PID=`ps -ef|grep java|grep UB-Batch|awk '{print $2}'`

if [ "x$PID" == "x" ]; then
  echo "UB-Batch application not running."
  exit 0
fi

echo "Stop UB-Batch application : $PID"
kill -9 $PID
