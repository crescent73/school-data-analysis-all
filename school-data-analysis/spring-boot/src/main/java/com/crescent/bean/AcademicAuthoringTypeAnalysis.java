package com.crescent.bean;

import javax.lang.model.element.NestingKind;

public class AcademicAuthoringTypeAnalysis {
    private String authoringType;
    private Long count;
    private Double percentage;

    public String getAuthoringType() {
        return authoringType;
    }

    public void setAuthoringType(String authoringType) {
        this.authoringType = authoringType;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}
