#!/bin/bash

export EMETER_APP_DATABASE='mysql';
export EMETER_APP_ENVIRONMENT='local';
export EMETER_APP_URL='localhost:8080';
export EMETER_APP_USE_SSL=0;
export EMETER_APP_LOCALE='pt_BR'; # or en_US
export SENTRY_DSN='https://1b55d22eb65b4ec9959eaffe8e63dc11:ec0b6b50c51547fdaac1ddaac12b3f68@sentry.io/215551';

# Arquivo de log do sistema (rodar apenas uma vez)
# sudo touch /var/log/emeter_log.txt;
# sudo chmod 777 /var/log/emeter_log.txt;

# Caminhos do Tomcat
ORIGIN='/Users/rafagan/Desktop/Dropbox/Guizion_Dev/Vetorlog/new_econometer/src';
TOMCAT_CONF='/usr/local/Cellar/tomcat/8.5.20/libexec/conf';
MAVEN_CONF='/usr/local/Cellar/maven/3.5.0/libexec/conf';
WEBAPPS='/usr/local/Cellar/tomcat/8.5.20/libexec/webapps';

# Arquivos de configuração do Tomcat
cp -v ${ORIGIN}/resources/tomcat/tomcat-users.xml ${TOMCAT_CONF}/tomcat-users.xml;
cp -v ${ORIGIN}/resources/tomcat/manager-context.xml ${WEBAPPS}/manager/META-INF/context.xml;
cp -v ${ORIGIN}/resources/tomcat/manager-context.xml ${WEBAPPS}/host-manager/META-INF/context.xml;
cp -v ${ORIGIN}/resources/tomcat/context.xml ${TOMCAT_CONF}/;
cp -v ${ORIGIN}/resources/tomcat/server.xml ${TOMCAT_CONF}/;
cp -v ${ORIGIN}/resources/maven/settings.xml ${MAVEN_CONF}/;

# Dependências provided
cp -v -r ${ORIGIN}/tomcat-libs/* /usr/local/Cellar/tomcat/8.5.20/libexec/lib/;

# Comandos do Tomcat
alias tomcat_start="brew services start tomcat";
alias tomcat_stop="brew services stop tomcat";
alias tomcat_restart="brew services restart tomcat";
alias mysql_start="mysql.server start";
alias mysql_stop="mysql.server stop";

alias tomcat_deploy="mvn tomcat7:deploy";
alias tomcat_undeploy="mvn tomcat7:undeploy";
alias tomcat_redeploy="mvn tomcat7:redeploy";