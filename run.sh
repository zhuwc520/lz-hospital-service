#!/bin/bash -ex
cd /data
/usr/local/tomcat/bin/catalina.sh start
/usr/bin/supervisord -n -c  /etc/supervisor/supervisord.conf