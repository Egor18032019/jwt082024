package t1.jwt.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import t1.jwt.demo.api.UserApi;

@RestController
public class UserController implements UserApi {

    @Override
    public ResponseEntity<String> user() {
        return ResponseEntity.ok("YOU USER !");
    }
}
