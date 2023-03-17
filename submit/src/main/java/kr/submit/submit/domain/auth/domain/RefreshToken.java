package kr.submit.submit.domain.auth.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Indexed;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@RedisHash
public class RefreshToken {
    @Id
    private String email;

    @Indexed
    private String token;

    @TimeToLive
    private Long timeToLive;

    public void updateToken(String token, Long timeToLive) {
        this.token = token;
        this.timeToLive = timeToLive;
    }

}
