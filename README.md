# javaCallR

assume you have 'R' and 'Maven'


Install Rserve in R
----
type command in R
> install.packages('Rserve')


Config R file path
----
edit ~/whylufun/src/main/resources/rScriptDef.properties as following format
> {NAME}={LOCATION}


Compile
----
> cd ~/whylufun
> mvn clean install


Put ~/target/whylufun.war into apache-server
----


Run Rserve
----
type command in R
> library(Rserve)
> Rserve()


Run apache-server
----


Try example
----
  To print r script file, goto
  http://localhost:8080/whylufun/callR/get/{NAME}
  ex: http://localhost:8080/whylufun/callR/get/testScript1

  To run r script file, goto
  http://localhost:8080/whylufun/callR/run/{NAME}
  ex: http://localhost:8080/whylufun/callR/run/testScript1

