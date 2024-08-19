package t1.jwt.demo.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import t1.jwt.demo.model.JwtAuthenticationResponse;
import t1.jwt.demo.model.SignInRequest;
import t1.jwt.demo.model.SignUpRequest;
import t1.jwt.demo.model.TokenRequest;
import t1.jwt.demo.utils.EndPoint;

@RequestMapping(value = EndPoint.API + EndPoint.AUTH)
@Tag(name = "Authentication.", description = "Контроллер для аутентификации и регистрации пользователей.")
public interface AuthApi {

    @Operation(summary = "Регистрация пользователя")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Return JwtAuthenticationResponse",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = JwtAuthenticationResponse.class)))
            ),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    }
    )
    @RequestMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE,
            method = RequestMethod.POST,
            value = EndPoint.REGISTER
    )
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    JwtAuthenticationResponse signUp(@Valid @RequestBody SignUpRequest request);

    @Operation(summary = "Авторизация пользователя.")
    @RequestMapping(
            method = RequestMethod.POST,
            value = EndPoint.LOGIN
    )
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    JwtAuthenticationResponse signIn(
            @Parameter(name = "Jwt Request object", required = true)
            @Valid @RequestBody SignInRequest request
    );

    @Operation(summary = "Обновить JWT токен.")
    @RequestMapping(
            method = RequestMethod.POST,
            value = EndPoint.REFRESH
    )
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    ResponseEntity<JwtAuthenticationResponse> refresh(
            @Parameter(name = "Jwt Request object", required = true)
            @Valid @RequestBody TokenRequest request
    );

}
