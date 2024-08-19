package t1.jwt.demo.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import t1.jwt.demo.utils.EndPoint;

@Tag(name = "User", description = "Api for interacting with the User role")
@RequestMapping(value = EndPoint.USER)
public interface UserApi {
    @Operation(summary = "Доступен только авторизованным пользователям с ролью USER")
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<String> user();
}
