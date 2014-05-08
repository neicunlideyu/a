package com.onboard.plugin.git.model;

import com.onboard.domain.model.type.Identifiable;
import com.onboard.plugin.git.mapper.ReviewObject;

/**
 * Domain model: Review
 * 
 * @generated_by_elevenframework
 * 
 */
public class Review extends ReviewObject implements Identifiable {

    private Diff diff;

    public Review() {
        super();
    }

    public Review(int id) {
        super(id);
    }

    public Review(ReviewObject obj) {
        super(obj);
    }

    @Override
    public String getType() {
        return "pullrequest-review";
    }

    @Override
    public Integer getCompanyId() {
        // TODO Auto-generated method stub
        return null;
    }

    public Diff getDiff() {
        return diff;
    }

    public void setDiff(Diff diff) {
        this.diff = diff;
    }

}
