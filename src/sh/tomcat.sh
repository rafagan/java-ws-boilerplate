#!/bin/bash

ORIGIN='/Users/rafagan/Desktop/Dropbox/Guizion_Dev/Vetorlog/new-econometer/src'
CONF='/usr/local/Cellar/tomcat/8.5.20/libexec/conf'
WEBAPPS='/usr/local/Cellar/tomcat/8.5.20/libexec/webapps'

# Arquivos de configuração do Tomcat
cp -v ${ORIGIN}/resources/tomcat/tomcat-users.xml ${CONF}/tomcat-users.xml
cp -v ${ORIGIN}/resources/tomcat/manager-context.xml ${WEBAPPS}/manager/META-INF/context.xml
cp -v ${ORIGIN}/resources/tomcat/manager-context.xml ${WEBAPPS}/host-manager/META-INF/context.xml

# Dependências provided
cp -r ${ORIGIN}/tomcat-mvn-provided/* /usr/local/Cellar/tomcat/8.5.20/libexec/lib/