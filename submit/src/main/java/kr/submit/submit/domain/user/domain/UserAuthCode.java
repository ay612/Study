package kr.submit.submit.domain.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAuthCode {
    @Id
    private String phoneNuber;
    private String code;

    @TimeToLive
    private Long expireAt;

    @Builder
    public UserAuthCode(String phoneNuber, String code){
        this.phoneNuber = phoneNuber;
        this.code = code;
        this.expireAt = 180L;
    }
}
