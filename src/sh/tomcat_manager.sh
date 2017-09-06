#!/bin/bash

ORIGIN='/Users/rafagan/Desktop/Dropbox/Guizion_Dev/Vetorlog/new-econometer/src/resources/tomcat'
DEST='/usr/local/Cellar/tomcat/8.5.20/libexec/conf'

cp ${ORIGIN}/tomcat-users.xml ${DEST}/tomcat-users.xml
cp ${ORIGIN}/context.xml /usr/local/Cellar/tomcat/8.5.20/libexec/webapps/manager/META-INF/context.xml
cp ${ORIGIN}/context2.xml /usr/local/Cellar/tomcat/8.5.20/libexec/webapps/host-manager/META-INF/context.xml