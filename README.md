# jwt082024

Перед запуском приложения необходимо создать бд 'auth' -> а дальше как обычно
```shell
docker run --name auth_db -p 5432:5432 -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=auth -d postgres:11-alpine
```

##  Documentation
http://localhost:8080/swagger-ui/index.html

### Дополнения
По умолчанию пользователь создается с ролью User.
Поведение по умолчанию меняется в UserService в методе create.