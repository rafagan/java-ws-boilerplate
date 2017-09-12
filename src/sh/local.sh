#!/bin/bash

export EMETER_APP_DATABASE='mysql';
export EMETER_APP_ENVIRONMENT='local';
export EMETER_APP_URL='localhost:8080';
export EMETER_APP_USE_SSL=0;
export SENTRY_DSN='https://1b55d22eb65b4ec9959eaffe8e63dc11:ec0b6b50c51547fdaac1ddaac12b3f68@sentry.io/215551';

# Arquivo de log do sistema (rodar apenas uma vez)
#sudo touch /var/log/emeter_log.txt;
#sudo chmod 777 /var/log/emeter_log.txt;

# Caminhos do Tomcat
ORIGIN='/Users/rafagan/Desktop/Dropbox/Guizion_Dev/Vetorlog/new-econometer/src';
CONF='/usr/local/Cellar/tomcat/8.5.20/libexec/conf';
WEBAPPS='/usr/local/Cellar/tomcat/8.5.20/libexec/webapps';

# Arquivos de configuração do Tomcat
cp -v ${ORIGIN}/resources/tomcat/tomcat-users.xml ${CONF}/tomcat-users.xml;
cp -v ${ORIGIN}/resources/tomcat/manager-context.xml ${WEBAPPS}/manager/META-INF/context.xml;
cp -v ${ORIGIN}/resources/tomcat/manager-context.xml ${WEBAPPS}/host-manager/META-INF/context.xml;
cp -v ${ORIGIN}/resources/tomcat/context.xml ${CONF}/context.xml;

# Dependências provided
cp -r ${ORIGIN}/tomcat-libs/* /usr/local/Cellar/tomcat/8.5.20/libexec/lib/;