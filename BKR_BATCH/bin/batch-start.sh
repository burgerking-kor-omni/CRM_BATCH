#!/bin/bash
JAVA_HOME=/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.181-8.b13.39.39.amzn1.x86_64/jre

# Check batch running ...
PID=`ps -ef|grep java|grep UB-Batch|awk '{print $2}'`

#echo $PID
if [ "x$PID" != "x" ]; then
  echo "UB-Batch application running..."
  exit 0
fi

$JAVA_HOME/bin/java -DUB-Batch -jar ub-batch.jar &