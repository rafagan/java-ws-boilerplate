# README #

Informações gerais a respeito do projeto. 
NÃO COLOCAR este arquivo em repositório público.
Ele serve apenas para consulta na fase de desenvolvimento.

### URLs de acesso ###

* Acesso ao index.html: `http://<url>/:8080`
* Acesso ao manager Tomcat 8: `http://<url>/:8080/manager`
  * Login: `vetorlog`
  * Senha: `abcd@1234`


### Configuração de banco de dados ###
* Rodar `mysql_secure_installation`
    * Login: `vetorlog`
    * Senha: `abcd@1234`
* Rodar `users.sql` para configurar usuário `vetorlog` do banco de dados e crar banco de dados, caso ainda não existam

### Configuração do servlet container Tomcat 8 ###
 * Rodar `tomcat_manager.sh` para configurar usuário do manager
 * Rodar `tomcat_db_pool.sh` para configurar pool de conexões