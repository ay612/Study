package kr.submit.submit.domain.user.service;

import kr.submit.submit.domain.user.fcade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryMyCommentPagesService {
    private final UserFacade userFacade;
    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public class QueryMyCommentPagesService {

        private final UserFacade userFacade;
        private final CommentRepository commentRepository;

        @Transactional(readOnly = true)
        public QueryMyCommentPagesResponse execute(Integer pageId) {

            User user = userFacade.getCurrentUser();

            Slice<CommentFeedIdVO> commentList = commentRepository.queryCommentFeedIdPagesByOffset(
                    CommentConditionVO.builder()
                            .pageId(pageId)
                            .userId(user.getId())
                            .build(),
                    PagingSupportUtil.applyPageSize()
            );

            List<QueryMyCommentDetailsResponse> queryMyCommentDetailsResponseList = new ArrayList<>();

            for (CommentFeedIdVO commentFeedIdVO : commentList) {
                queryMyCommentDetailsResponseList.add(
                        QueryMyCommentDetailsResponse.builder()
                                .category(
                                        CategoryResponse.builder()
                                                .categoryId(commentFeedIdVO.getCategoryVO().getCategoryId())
                                                .categoryName(commentFeedIdVO.getCategoryVO().getCategoryName())
                                                .build()
                                )
                                .feedId(commentFeedIdVO.getFeedId())
                                .feedContent(commentFeedIdVO.getFeedContent())
                                .comment(
                                        commentRepository.findCommentByFeedIdAndUserId(commentFeedIdVO.getFeedId(), user.getId()).stream()
                                                .map(comment -> CommentSimpleResponse.builder()
                                                        .commentId(comment.getId())
                                                        .content(comment.getContent())
                                                        .build())
                                                .collect(Collectors.toList())
                                )
                                .build()
                );
            }

            return new QueryMyCommentPagesResponse(queryMyCommentDetailsResponseList, commentList.hasNext(), pageId);
        }
}
