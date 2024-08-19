package t1.jwt.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import t1.jwt.demo.api.InfoApi;
import t1.jwt.demo.model.Info;

@RestController
public class InfoController implements InfoApi {

    @Override
    public ResponseEntity<Info> info() {
        Info info = new Info("Hello world");
        return ResponseEntity.ok(info);
    }
}
