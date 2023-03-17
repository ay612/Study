package kr.submit.submit.domain.user.domain.repository.VO;

import lombok.Getter;

@Getter
public class AuthorVO {

    private final Long userId;
    private final String nickname;
    private final String birth;
    private final String profileImageUrl;
    private final String location;

    @QueryProjection
    public AuthorVO(Long userId, String nickname, String birth, String profileImageUrl, String location) {
        this.userId = userId;
        this.nickname = nickname;
        this.birth = birth;
        this.profileImageUrl = profileImageUrl;
        this.location = location;
    }
}
