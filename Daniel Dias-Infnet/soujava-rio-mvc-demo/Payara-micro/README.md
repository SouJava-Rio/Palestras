# Payara-Micro
Para executar :

mvn clean package payara-micro:start

Precisa do MongoDB instalado na Maquina ou Via Docker.

# Payara Micro URLs
http://localhost:8080/demo

# 'demo' REST Endpoints

```bash
GET	/demo/app/
GET	/demo/app/jug/editarRegistro/{id}
POST	/demo/app/jug/form
GET	/demo/app/jug/formulario
GET	/demo/app/jug/listar
GET	/demo/app/jug/mostrar
DELETE	/demo/app/jug/remover/{id}
POST	/demo/app/jug/update
```
