package kr.submit.submit.domain.user.service;

import kr.submit.submit.domain.user.domain.User;
import kr.submit.submit.domain.user.fcade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryMyProfileService {
    private final FeedBookmarkRepository feedBookmarkRepository;
    private final MeetingRepository meetingRepository;
    private final CommentRepository commentRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public QueryMyProfileResponse.execute(){
        User user = userFcade.getCurrentUser();

        return QueryMyProfileResponse.bulider()
                .userId(user.getId())
                .nickname(user.getNickname())
                .birth(user.getBirth())
                .profileImageUrl(user.getProfileImageUrl())
                .location(user.getLocation())
                .feedCount(user.getFeedCount())
                .bookmarkCount(feedBookmarkRepository.countByUser(user))
                .meetingCount(meetingRepository.countByUser(user))
                .commentCount(commentRepository.countByUser(user))
                .build();
    }
}
