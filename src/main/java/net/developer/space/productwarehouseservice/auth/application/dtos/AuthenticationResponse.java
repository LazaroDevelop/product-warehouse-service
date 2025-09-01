package net.developer.space.productwarehouseservice.auth.application.dtos;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {

    private String accessToken;

    @Builder.Default
    private String tokenType = "Bearer ";

    @Override
    public String toString() {
        return new StringBuilder()
                .append(tokenType)
                .append(accessToken)
                .toString();
    }
}
