package t1.jwt.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import t1.jwt.demo.api.ManagerApi;

@RestController
public class ManagerController implements ManagerApi {

    @Override
    public ResponseEntity<String> manager() {
        return ResponseEntity.ok("YOU MANAGER !");
    }
}
