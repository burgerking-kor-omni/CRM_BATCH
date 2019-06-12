#!/bin/bash
find /logs/tomcat_crm/*.log.* -mtime +90 -exec rm -f {} \;
find /logs/batch/*.log.* -mtime +90 -exec rm -f {} \;