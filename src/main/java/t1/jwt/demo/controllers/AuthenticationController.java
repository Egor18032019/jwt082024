package t1.jwt.demo.controllers;


import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import t1.jwt.demo.api.AuthApi;
import t1.jwt.demo.model.JwtAuthenticationResponse;
import t1.jwt.demo.model.SignInRequest;
import t1.jwt.demo.model.SignUpRequest;
import t1.jwt.demo.model.TokenRequest;
import t1.jwt.demo.service.AuthenticationService;
import t1.jwt.demo.service.JwtTokenService;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController implements AuthApi {
    AuthenticationService authenticationService;
    JwtTokenService jwtService;

    @Override
    public JwtAuthenticationResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return authenticationService.signUp(request);
    }

    @Override
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request);
    }

    @Override
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody TokenRequest request) {
        // Проверка на валидность токена уже должны быть сделана
        return ResponseEntity.ok(jwtService.refreshToken(request.getToken()));
    }


}
