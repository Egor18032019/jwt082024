package t1.jwt.demo.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import t1.jwt.demo.api.AdminApi;


@RestController
// Работа с пользователями. Добавление удаление изменения роли
public class AdminController implements AdminApi {

    @Override
    public ResponseEntity<String> admin() {
        return ResponseEntity.ok("You Admin !");
    }
}
