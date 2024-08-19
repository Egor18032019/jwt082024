package t1.jwt.demo.model;

import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TokenRequest {
    private String token;
}
