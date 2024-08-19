package t1.jwt.demo.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import t1.jwt.demo.utils.EndPoint;

@Tag(name = "User", description = "Api for interacting with the Admin role")
@RequestMapping(value = EndPoint.ADMIN)
public interface AdminApi {
    @Operation(summary = "Доступен только авторизованным пользователям с ролью ADMIN")
    @GetMapping
    @PreAuthorize("hasRole('ADMIN")
    ResponseEntity<String> admin();
}
