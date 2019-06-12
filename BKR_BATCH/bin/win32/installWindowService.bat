@echo off
set APP_JAVA="C:\Program Files (x86)\Java\jdk1.6.0_45\bin\java.exe"
set APP_DIR="D:\project\SUHYANG\workspace\ub_basebatch_2.x\bin"
set APP_PARAM="-jar ub-batch.jar"

nssm install ub-batch %APP_JAVA%
nssm set ub-batch AppDirectory %APP_DIR%
nssm set ub-batch AppParameters %APP_PARAM%