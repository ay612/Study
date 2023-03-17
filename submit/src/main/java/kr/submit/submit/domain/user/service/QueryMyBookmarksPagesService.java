package kr.submit.submit.domain.user.service;

import kr.submit.submit.domain.user.domain.User;
import kr.submit.submit.domain.user.fcade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryMyBookmarksPagesService {
    private final FeedRepository feedRepository;
    private final FeedImageRepository feedImageRepository;
    private final FeedViewCountRepository feedViewCountRepository;
    private final UserFacade userFacade;
    private final static String ORDER = "BOOKMARK";

    @Transactional(readOnly = true)
    public QueryMyBookmarksPagesResponse execute(Integer pageId, Long categoryId) {
        User user = userFacade.getCurrentUser();

        Slice<FeedDetailsVO> feedList = feedRepository.queryFeedPagesByOffset(
                FeedConditionVO.builder()
                        .userId(user.getId())
                        .pageId(pageId)
                        .categoryId(categoryId)
                        .isBookmark(true)
                        .orders(ORDER)
                        .build(),
                PagingSupportUtil.applyPageSize()
        );

        List<QueryMyBookmarksDetailsResponse> queryMyBookmarksDetailsResponseList = new ArrayList<>();

        for (FeedDetailsVO feedDetailsVO : feedList) {
            List<String> imageUrl = feedImageRepository.findByFeedId(feedDetailsVO.getFeedId())
                    .stream()
                    .map(FeedImage::getImageUrl)
                    .collect(Collectors.toList());

            FeedViewCount feedViewCount = feedViewCountRepository.findById(feedDetailsVO.getFeedId())
                    .orElseThrow(() -> FeedViewCountNotFoundException.EXCEPTION);

            queryMyBookmarksDetailsResponseList.add(
                    buildQueryMyBookmarkDetailsResponse(feedDetailsVO, imageUrl, feedViewCount.getViewCount())
            );
        }

        return new QueryMyBookmarksPagesResponse(queryMyBookmarksDetailsResponseList, feedList.hasNext(), queryMyBookmarksDetailsResponseList.size(), pageId);
    }

    private CategoryResponse buildCategoryResponse(CategoryVO categoryVO) {
        return CategoryResponse.builder()
                .categoryId(categoryVO.getCategoryId())
                .categoryName(categoryVO.getCategoryName())
                .build();
    }

    private QueryMyBookmarksDetailsResponse buildQueryMyBookmarkDetailsResponse(FeedDetailsVO feedDetailsVO, List<String> imageUrl, Long feedViewCount) {
        return QueryMyBookmarksDetailsResponse.builder()
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
