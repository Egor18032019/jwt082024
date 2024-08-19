package t1.jwt.demo.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import t1.jwt.demo.model.Info;
import t1.jwt.demo.utils.EndPoint;

@Tag(name = "Общая информация. ", description = "Available to all roles.")
@RequestMapping(value = EndPoint.INFO)
public interface InfoApi {
    @Operation(summary = "Отдает 'Hello World!' и время создания.")
    @GetMapping
    ResponseEntity<Info> info();
}
