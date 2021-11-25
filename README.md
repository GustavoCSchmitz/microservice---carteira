# microservice---carteira
Este projeto é um microservice desenvolvido com a tecnologia Spring Boot utilizando Maven e API Rest, onde o usuário consegue efetuar realizar requisições através de outros serviços que serão processadas nesta API.

Obs: Este projeto só faz sentido executando juntamente com os outros 4 serviços
   - [Saque, Depósito e Transferência](https://github.com/GustavoCSchmitz/microservice---saqueDeposito)
   - [Pagamentos](https://github.com/GustavoCSchmitz/microservice---pagamentos)
   - [Eureka Server](https://github.com/GustavoCSchmitz/microservice---eureka)
   - [Usuários](https://github.com/GustavoCSchmitz/microservice---usuarios)

### Ordem de execução
- 1º [Eureka Server](https://github.com/GustavoCSchmitz/microservice---eureka)
- 2º [Pagamentos](https://github.com/GustavoCSchmitz/microservice---pagamentos)
- 3º [Carteira](https://github.com/GustavoCSchmitz/microservice---carteira)
- 4º [Saque,depósito e transferência](https://github.com/GustavoCSchmitz/microservice---saqueDeposito)
- 5º [Usuários](https://github.com/GustavoCSchmitz/microservice---usuarios)


### Requisitos de API e instruções para execução
 - Java 8
 - Maven 3 para construir o aplicativo.
 - Banco de dados MariaDB
 
 - Instalar o [MariaDb](https://mariadb.org/download/)
 - Abrir na pasta raíz do projeto e executar o comando:
 
      `mvn clean install`
 - Após a instalação, subir o projeto com o comando:
       
      `mvn spring-boot:run`
      
### API endpoint
  - Os endpoints existentes neste projeto devem ser somente acessados pelos outros serviços.
 
 
