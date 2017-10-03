#!/bin/bash

# Variáveis de ambiente
export EMETER_APP_DATABASE='mysql';
export EMETER_APP_ENVIRONMENT='local';
export EMETER_APP_URL='localhost:8080';
export EMETER_APP_USE_SSL=0;
export EMETER_APP_LOCALE='pt_BR'; # or en_US
export SENTRY_DSN='https://1b55d22eb65b4ec9959eaffe8e63dc11:ec0b6b50c51547fdaac1ddaac12b3f68@sentry.io/215551';

# Arquivo de log do sistema (rodar apenas uma vez)
# sudo touch /var/log/emeter_log.txt;
# sudo chmod 777 /var/log/emeter_log.txt;

# Caminhos
PROJECT='/Users/rafagan/Desktop/Dropbox/Guizion_Dev/Vetorlog/new_econometer/src';
TOMCAT='/usr/local/Cellar/tomcat/8.5.20/libexec'
MAVEN='/usr/local/Cellar/maven/3.5.0/libexec/conf';
APACHE='/private/etc/apache2'

# Arquivos de configuração
cp -v ${PROJECT}/resources/tomcat/tomcat-users.xml ${TOMCAT}/conf/;
cp -v ${PROJECT}/resources/tomcat/manager-context.xml ${TOMCAT}/webapps/manager/META-INF/context.xml;
cp -v ${PROJECT}/resources/tomcat/manager-context.xml ${TOMCAT}/webapps/host-manager/META-INF/context.xml;
cp -v ${PROJECT}/resources/tomcat/context.xml ${TOMCAT}/conf/;
cp -v ${PROJECT}/resources/tomcat/server.xml ${TOMCAT}/conf/;
cp -v ${PROJECT}/resources/tomcat/web.xml ${TOMCAT}/conf/;
cp -v ${PROJECT}/resources/maven/settings.xml ${MAVEN}/;
cp -v ${PROJECT}/resources/apache/httpd.conf ${APACHE}/;
cp -v ${PROJECT}/resources/apache/httpd-vhosts.conf ${APACHE}/extra/;

# Dependências provided
cp -v -r ${PROJECT}/tomcat-libs/* ${TOMCAT}/lib/;

# Comandos
alias tomcat_start='brew services start tomcat';
alias tomcat_stop='brew services stop tomcat';
alias tomcat_restart='brew services restart tomcat';
alias mysql_start='mysql.server start';
alias mysql_stop='mysql.server stop';
alias apache_start='sudo apachectl start';
alias apache_stop='sudo apachectl stop';
alias apache_restart='sudo apachectl restart';
alias apache_log='tail -n 1000 /private/var/log/apache2/error_log';
alias tomcat_deploy='mvn tomcat7:deploy';
alias tomcat_undeploy='mvn tomcat7:undeploy';
alias tomcat_redeploy='mvn tomcat7:redeploy';