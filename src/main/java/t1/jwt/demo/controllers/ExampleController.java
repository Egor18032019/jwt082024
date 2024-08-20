package t1.jwt.demo.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import t1.jwt.demo.model.Info;
import t1.jwt.demo.utils.EndPoint;

@RestController
@RequestMapping(EndPoint.EXAMPLE)
@RequiredArgsConstructor
@Tag(name = "Общая информация для авторизированных пользователей.")
public class ExampleController {
    @GetMapping
    @Operation(summary = "Доступно ролям User и Admin.")
    public ResponseEntity<Info> example() {
        Info info = new Info("Hello world !!!!");
        return ResponseEntity.ok(info);
    }
}
