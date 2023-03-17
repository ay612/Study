package kr.submit.submit.domain.user.service;

import kr.submit.submit.domain.user.fcade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class QueryMyFeedPagesService {
    private final FeedRepository feedRepository;
    private final FeedImageRepository feedImageRepository;
    private final FeedViewCountRepository feedViewCountRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public QueryMyFeedpagesResponse execute(Long cursorId) {
        User user = userFacade.getCurrentUser();

        Slice<FeedDetailsVO> feedList = feedRepository.queryFeedPagesByCusor(
                FeedConditionVO.builder()
                        .userId(user.getId())
                        .cursorId(PagingSupportUtil.applyCursorId(cursorId))
                        .findUserId(user.getId())
                        .build(),
                PagingSupportUtil.applyPageSize()
        );
        List<QueryMyFeedDetailsResponse> queryMyFeedDetailsResponseList = new ArrayList<>();

        for (FeedDetailsVO feedDetailsVO : feedList) {
            List<String> imageUrl = feedImageRepository.findByFeedId(feedDetailsVO.getFeedId())
                    .stream()
                    .map(FeedImage::getImageUrl)
                    .collect(Collectors.toList());

            FeedViewCount feedViewCount = feedViewCountRepository.findById(feedDetailsVO.getFeedId())
                    .orElseThrow(() -> FeedViewCountNotFoundException.EXCEPTION);

            queryMyFeedDetailsResponseList.add(
                    buildQueryMyFeedDetailsResponse(feedDetailsVO, imageUrl, feedViewCount.getViewCount())
            );
        }

        return new QueryMyFeedPagesResponse(queryMyFeedDetailsResponseList, feedList.hasNext(), queryMyFeedDetailsResponseList.size());
    }

    private CategoryResponse buildCategoryResponse(CategoryVO categoryVO) {
        return CategoryResponse.builder()
                .categoryId(categoryVO.getCategoryId())
                .categoryName(categoryVO.getCategoryName())
                .build();
    }

    private QueryMyFeedDetailsResponse buildQueryMyFeedDetailsResponse(FeedDetailsVO feedDetailsVO, List<String> imageUrl, Long feedViewCount) {
        return QueryMyFeedDetailsResponse.builder()
                .category(buildCategoryResponse(feedDetailsVO.getCategoryVO()))
                .feedId(feedDetailsVO.getFeedId())
                .imageUrl(imageUrl)
                .createdAt(feedDetailsVO.getCreatedAt())
                .content(feedDetailsVO.getContent())
                .isLike(feedDetailsVO.getIsLike())
                .isBookmark(feedDetailsVO.getIsBookmark())
                .likeCount(feedDetailsVO.getLikeCount())
                .commentCount(feedDetailsVO.getCommentCount())
                .bookmarkCount(feedDetailsVO.getBookmarkCount())
                .viewCount(feedViewCount)
                .build();
    }
    }

}
