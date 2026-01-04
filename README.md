
# Principal Project

Uma aplicação full-stack com componentes frontend e backend separados.

## Project Structure

- **principal-frn**: Frontend application
- **principal**: Backend API

## Getting Started

### Backend (principal)

- **Java 17**
- **Spring Boot 3.4.4**
  - Spring Data JPA
  - Spring Web
  - Spring DevTools
- **Thymeleaf** - Motor de templates
- **MySQL 8.0** - Banco de dados
- **Hibernate** - ORM


### Execução

#### Windows (usando Maven Wrapper):
```powershell
mvnw.cmd spring-boot:run
```

#### Linux/Mac (usando Maven Wrapper):
```bash
./mvnw spring-boot:run
```

#### Com Maven instalado:
```bash
mvn spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080` (endereço dos endpoints)

### Frontend (principal-frn)

1. Navegar para o diretório frontend:
  ```bash
  cd principal-frn
  ```

2. Instalar dependencias:
  ```bash
  npm install
  ```

3. Configurar os endpoints API em suas variáveis de ambiente

4. Iniciar o servidor de desenvolvimento:
  ```bash
  npm start
  ```

A API estará disponível em `http://localhost:4200` (ou sua porta configurada)

## Technologies

- **Backend**: Java (Spring Boot)
- **Frontend**: TypeScript (Angular)

## 👨‍💻 Desenvolvedor

**Lucas Leocadio de Souza**
- GitHub: [LucasLeocadiodeSouza](https://github.com/LucasLeocadiodeSouza)
