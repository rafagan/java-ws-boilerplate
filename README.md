# README #

Informações gerais a respeito do projeto. 
NÃO COLOCAR este arquivo em repositório público.
Ele serve apenas para consulta na fase de desenvolvimento.

### Base de programas necessários ###

* Apache HTTP Server 2.4.27
* Tomcat 8.5.20
* Maven 3.5.0
* MySQL 5.7.19
* IntelliJ IDEA 2017.2.5 (Fortemente recomendado)
* NodeJS 5.4.2 (se vai trabalhar também no front end Angular 4)

### Para começar o desenvolvimento ###

* Instalar dependêcias de Sistema Operacional acima descritas
* Configurar e rodar script .sh em `src/sh` 
  - Garanta que os nomes de arquivos e diretórios estão corretos
  - Normalmente, cada desenvolvedor deve escrever seu próprio script
  - Certifique-se de que as variáveis de ambiente estejam apropriadamente criadas quando sua IDE é iniciada
* Instalar dependências do maven, aplicando o comando na raiz do projeto: mvn clean package install
* Instalar banco de dados (sessão abaixo)
* Caso esteja rodando também o front end, rodar na raiz do projeto front end o comando `npm install`
  - Caso esteja realizando alterações no front end e deseje subir essas alterações, rodar `npm run start`
  - Caso deseje executar o deploy do front end em uma pasta mapeada pelo apache (pasta sugerida no `local.sh`: `/var/www/econometer.com/public_html`), rodar `npm run build` e mover a pasta `dist` para este destino

### Configuração de banco de dados ###

* Rodar `mysql_secure_installation`
    * Login: `vetorlog`
    * Senha: `abcd@1234`
* Rodar `users.sql` para configurar usuário `vetorlog` do banco de dados e crar banco de dados, caso ainda não exista

### URLs de acesso ###

* index.html do tomcat: `http://<url>/:8080` ou `http://<url>/java` (via apache)
* Manager Tomcat 8: `http://<url>/:8080/manager`
  * Login: `vetorlog`
  * Senha: `abcd@1234`
* index.html do projeto Angular 4 (comando `npm run start`): `http://<url>/:4200`
* index.html do front implantado (comando `npm run build` e mover para pasta mapeada do Apache): `http://<url>/`
* Para fazer requisições, toda URL começa com `<domain>/api/`
* Swagger (visualizar requests e Data Transfer Objects – DTOs): `<domain>/swagger` ou `<domain>/api/swagger`  
* Sentry (visualizar erros e logs do sistema): `https://sentry.io/vetorlog/`
  * Login: `admin@vetorlog.com.br`
  * Senha: `abcd@1234`

### Arquivo *.sh ###

O script é dividido em 6 partes:

* Variáveis de ambiente:
  - `EMETER_APP_DATABASE`: Banco de dados a ser utilizado. Esse nome deve prefixar o ambiente no `persistence.xml` (ex: `<persistence-unit name="mysql_staging" transaction-type="RESOURCE_LOCAL">`)
  - `EMETER_APP_ENVIRONMENT`: Ambiente da aplicação. É o sufixo do nome do persistence unit no `persistence.xml`
  - `EMETER_APP_URL`: URL da aplicação. Interessante para o servidor executar redirects e montar conteúdos que necessitem da URL da aplicação
  - `EMETER_APP_USE_SSL`: HTTP (0) ou HTTPS (1)
  - `EMETER_APP_LOCALE`: Idioma da apliação. Atualmente suportados apenas `pt_BR` ou `en_US`
  - `SENTRY_DSN`: Link para projeto do Sentry aonde ocorrerá os logs

* Caminhos:
  - `PROJECT`: Raiz do projeto do servidor
  - `WEBDIST`: Pasta configurada para receber o `dist` do Angular 4
  - `TOMCAT`: Diretório aonde o Tomcat foi instalado
  - `MAVEN`: Diretório aonde o Maven foi instalado
  - `APACHE`: Diretório aonde o Apache foi instalado

* Arquivos de configuração:

Move os arquivos de configuração para as pastas correspondentes. Após alterações, as vezes é necessário reiniciar o serviço, como é o caso do Tomcat e Apache
Os arquivos de configuração copiados (na maioria da pasta `src/conf` do projeto) são:

  - `tomcat-users.xml`: Usuários e permissões de acesso ao Manager do Tomcat
  - `context.xml`: Pool de conexões
  - `manager-context.xml`: Quais URLs são permitidas a acessar o Manager do Tomcat (atualmente, qualquer uma)
  - `server.xml`: Configurações do Tomcat 8
  - `web.xml`: A aplicação não trabalha com xml para configuração da camada web. Ao invés disso, utiliza as anotações do Jersey 2. 
               Jamais insira o web.xml na aplicação, mas caso seja muito necessário, edite este arquivo direto do Tomcat. 
               Deve-se ter total precaução na edição, pois as alterações podem entrar em conflito com o Jersey 2
  - `settings.xml`: Configurações do Maven
  - `httpd.conf`: Configurações do Apache
  - `httpd-vhosts.conf`: Portas e caminhos para aonde o Apache irá rotear
  - Pasta angular: Contém html das páginas de erro e configuração de rewire do Apache (`.htaccess`), customizável sem a necessidade de reiniciá-lo. 
      Caso deseje configurar o rewire do Tomcat, efetuar configuração do rewrite.config (`src/resources/rewrite.config`) 

* Dependências provided:

Move os .jar extras para a pasta do Tomcat necessários para ele executar. Caso haja alguma dependência provided do Maven, este é o ponto aonde elas devem ser inseridas.
A pasta do projeto com tais .jar é a `src/tomcat-libs`.

* Aliases e Funções:

Comandos para inicialização do Tomcat, MySQL e Apache. Também possui os comandos de deploy no Tomcat pelo Maven e visualização dos logs do Apache.

* Arquivos, diretórios e permissões:

Rodar os comandos aqui presentes apenas uma vez.

  - Arquivo de log do projeto (utilizado em `src/resources/log4j2.xml`). É opcional
  - Diretório aonde ficará o deploy do `dist` do front end. É opcional

### Organização do projeto ###

A estrutura do projeto é organizada da seguinte forma (a partir da pasta src):

* conf: Arquivos de configuração que serão copiados para suas pastas corretas. Atualmente, existem configurações para angular, apache, maven e tomcat
* java: Packages e código fonte
  - api: Mapeamentos com o Jersey 2 dos resources e interceptors
  - conf: Classes de cofiguração em Java da aplicação
  - controller: Classes injetadas nos resources. São responsáveis por montar a resposta a partir dos dados de entrada da requisição
  - dto: São os Data Transfer Objects, ou seja, os models serializáveis com Jackson que trafegarão na rede
  - manager: Gerenciadores de contexto de leitura e escrita
  - serializer: Mapeadores de Model para DTO e de DTO para Model
  - util: Diversos, como, por exemplo, enums da aplicação
* resources: Arquivos de configuração do projeto que deverão estar contidos no classpath
* sh: Scripts de configuração e deploy
* sql: Scripts SQL
* test: Classes para testes
  - java/vetorlog: Testes unitários (rodarão sempre que o Maven for executado). Somente os testes desse package devem ser executados constantemente 
  - .../mvnignore: Testes que devem ser ignorados pelo maven
  - .../insertion: Inserção de dados aleatórios no banco de dados. Também é ignorado pelo maven
  - .../prototype: Heranças
* tomcat-libs: .jar que devem ser inseridos na pasta libs do Tomcat
* webapp: Código fonte do front end não angular (como o Swagger) e compilados do Java que devem ir para o WAR

### Depoendências ###

Todas as dependências do projeto são citadas no arquivo do Maven `pom.xml`. São elas:

* Maven: compiler (compilação), surefile (testes), war (web) e tomcat (deploy)
* Java EE: servlets, JaxRS e JPA
* Apache: Proxy e mapeamento de portas
* JUnit 5: Testes unitários e execução de snippets
* Log4J: Logs
* Jersey: Mapeamento de requisições e interceptors
* Hibernate: Implementação do JPA e JPQL
* Lombok: Boilerplates
* MySQL: Driver de banco de dados
* Jackson: Serialização em JSON e XML
* Sentry: Logs externos
* JSON Web Token: Token de autenticação
* Reflections
* Tomcat