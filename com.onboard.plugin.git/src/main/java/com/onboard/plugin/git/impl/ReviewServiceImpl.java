package com.onboard.plugin.git.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onboard.domain.model.type.Identifiable;
import com.onboard.plugin.git.DiffLineType;
import com.onboard.plugin.git.PullRequestService;
import com.onboard.plugin.git.RepositoryService;
import com.onboard.plugin.git.ReviewService;
import com.onboard.plugin.git.mapper.ReviewExample;
import com.onboard.plugin.git.mapper.ReviewMapper;
import com.onboard.plugin.git.model.Diff;
import com.onboard.plugin.git.model.Diff.DiffLine;
import com.onboard.plugin.git.model.PullRequest;
import com.onboard.plugin.git.model.Review;
import com.onboard.service.collaboration.identifiable.DefaultIdentifiableService;
import com.onboard.service.collaboration.identifiable.IdentifiableManager;
import com.onboard.service.web.SessionService;

/**
 * {@link com.teamforge.service.collaboration.ReviewService} Service implementation
 * 
 * @generated_by_elevenframework
 */
@Transactional
@Service("reviewServiceBean")
public class ReviewServiceImpl extends DefaultIdentifiableService implements ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private SessionService session;

    @Autowired
    private PullRequestService pullRequestService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private IdentifiableManager identifiableManager;

    @Override
    public Review getReviewById(int id) {
        Review review = reviewMapper.selectByPrimaryKey(id);
        return review;
    }

    @Override
    public List<Review> getReviews(int start, int limit) {
        ReviewExample example = new ReviewExample(new Review());
        example.setLimit(start, limit);
        return reviewMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public List<Review> getReviewsByExample(Review item, int start, int limit) {
        ReviewExample example = new ReviewExample(item);
        example.setOrderByClause("created desc");
        example.setLimit(start, limit);
        return reviewMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public int countByExample(Review item) {
        ReviewExample example = new ReviewExample(item);
        return reviewMapper.countByExample(example);
    }

    @Override
    public Review createReview(Review item) {
        item.setCreated(new Date());
        item.setUpdated(item.getCreated());
        item.setCreatorId(session.getCurrentUser().getId());
        item.setCreatorName(session.getCurrentUser().getName());
        item.setDeleted(false);
        reviewMapper.insert(item);
        return item;
    }

    @Override
    public Review updateReview(Review item) {
        reviewMapper.updateByPrimaryKey(item);
        return item;
    }

    @Override
    public void deleteReview(int id) {
        reviewMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Review> getReviewsByPullRequestId(int pullRequestId) {
        Review review = new Review();
        review.setPullRequestId(pullRequestId);
        review.setDeleted(false);
        return this.getReviewsByExample(review, 0, -1);
    }

    /**
     * 判断该评论是否评论的是这段diff
     * 
     * @param review
     * @param diff
     * @return
     */
    private boolean isReviewForSpecailDiff(Review review, Diff diff) {
        // line-type为delete的review记录的line为左边的line-number
        if (review.getLine_type().equals(DiffLineType.DELETE)
                && !(diff.getF1Start() <= review.getLine() && diff.getF1Count() + diff.getF1Start() >= review.getLine())) {
            return false;
        }
        // line-type为add或context的review记录的line为右边的line-number
        else if (!review.getLine_type().equals(DiffLineType.DELETE)
                && !(diff.getF2Start() <= review.getLine() && diff.getF2Count() + diff.getF2Start() > review.getLine())) {
            return false;
        }
        return true;
    }

    /**
     * 找到review所在的diff
     * 
     * @param review
     * @return
     */
    private Diff getDiffForReview(Review review) {
        PullRequest pullRequest = pullRequestService.getPullRequestById(review.getPullRequestId());
        List<Diff> diffs = repositoryService.getDiffOfCommit(pullRequest.getRepositoryId(), review.getFile(), review.getSince(),
                review.getUntil());
        // 找到diff
        for (Diff diff : diffs) {
            if (isReviewForSpecailDiff(review, diff)) {
                return diff;
            }
        }
        return null;
    }

    /**
     * 判断review是否评论某一具体行
     * 
     * @param review
     * @param diff
     * @return
     */
    private boolean isReviewForSpecailDiffLine(Review review, DiffLine dl) {
        // line-type为delete的review记录的line为左边的line-number
        if (review.getLine_type().equals(DiffLineType.DELETE) && dl.getLeftLineNumber().length() > 0
                && review.getLine().equals(Integer.parseInt(dl.getLeftLineNumber()))) {
            return true;
        }
        // line-type为add或context的review记录的line为右边的line-number
        else if (!review.getLine_type().equals(DiffLineType.DELETE) && dl.getRightLineNumber().length() > 0
                && review.getLine().equals(Integer.parseInt(dl.getRightLineNumber()))) {
            return true;
        }
        return false;
    }

    private int fillReviewForDiff(Diff diff, Review review) {
        // 找到评论行所在的位置，并填充review
        int index = 0;
        List<Review> reviews = new ArrayList<Review>();
        reviews.add(review);
        for (DiffLine dl : diff.getLines()) {
            if (isReviewForSpecailDiffLine(review, dl)) {
                dl.setReviews(reviews);
                return index;
            }
            index++;
        }
        return index;
    }

    @Override
    public Diff getDiffWithReview(Review review) {
        Diff diffWithReview = getDiffForReview(review);
        if (diffWithReview == null) {
            return null;
        }
        int index = fillReviewForDiff(diffWithReview, review);
        // 未避免diff太长，将diff截成评论上下6行的长度
        int start = index - 6 < 0 ? 0 : index - 6;
        int end = index + 6 > diffWithReview.getLines().size() ? diffWithReview.getLines().size() : index + 6;
        diffWithReview.setLines(diffWithReview.getLines().subList(start, end));

        return diffWithReview;
    }

    @Override
    public Identifiable getIdentifiableById(Integer id) {
        return getReviewById(id);
    }

    @Override
    public Identifiable getIdentifiableWithDetailById(Integer id) {
        Review review = getReviewById(id);
        review.setDiff(getDiffWithReview(review));
        return review;
    }

    @Override
    public String modelType() {
        return (new Review()).getType();
    }

    @Override
    public String getIdentifiableURI(Identifiable identifiable) {
        Review review = (Review) identifiable;
        return identifiableManager.getIdentifiableURL(identifiableManager.getIdentifiableByTypeAndId(
                (new PullRequest()).getType(), review.getPullRequestId()));
    }

    @Override
    public void updateReviewAfterPush(PullRequest pullRequest, String since, String until) {
        List<Review> reviews = getReviewsByPullRequestId(pullRequest.getId());
        for (Review review : reviews) {
            review = new Review(review);
            review.setUntil(until);
            // review的line-type类型为delete则直接更新until
            if (review.getLine_type().equals(DiffLineType.DELETE)) {
                updateReview(review);
                continue;
            }
            List<Diff> diffs = repositoryService.getDiffOfCommit(pullRequest.getRepositoryId(), review.getFile(), since, until);
            for (Diff diff : diffs) {
                if (!(diff.getF1Start() <= review.getLine() && diff.getF1Count() + diff.getF1Start() > review.getLine())) {
                    continue;
                }
                for (DiffLine dl : diff.getLines()) {
                    // 找到review对应的行
                    if (dl.getLeftLineNumber().length() == 0
                            || !review.getLine().equals(Integer.parseInt(dl.getLeftLineNumber()))) {
                        continue;
                    }
                    // 更新的line-type为context,直接更新line-number
                    if (dl.getRightLineNumber().length() >= 0) {
                        review.setLine(Integer.parseInt(dl.getRightLineNumber()));
                        updateReview(review);
                    }
                    // 更新的line-type为delete
                    else {
                        if (review.getLine_type().equals(DiffLineType.CONTEXT)) {
                            DiffLine diffLine = this.getDiffLineWithContextReview(review);
                            if (diffLine == null) {
                                break;
                            }
                            review.setLine_type(DiffLineType.DELETE);
                            review.setLine(Integer.parseInt(diffLine.getLeftLineNumber()));
                            updateReview(review);
                        }
                    }
                    break;
                }
            }
        }
    }

    // 获取review所在的DiffLine
    private DiffLine getDiffLineWithContextReview(Review review) {
        PullRequest pullRequest = pullRequestService.getPullRequestById(review.getPullRequestId());
        List<Diff> diffs = repositoryService.getDiffOfCommit(pullRequest.getRepositoryId(), review.getFile(), review.getSince(),
                review.getUntil());
        for (Diff diff : diffs) {
            // context类型的reviewline为right-line number
            if (!(diff.getF2Start() <= review.getLine() && diff.getF2Count() + diff.getF2Start() > review.getLine())) {
                continue;
            }
            for (DiffLine dl : diff.getLines()) {
                if (review.getLine().equals(Integer.parseInt(dl.getRightLineNumber()))) {
                    return dl;
                }
            }
        }
        return null;
    }

}
