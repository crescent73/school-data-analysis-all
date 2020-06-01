package com.crescent.bean;

public class SocialPracticeTypeAnalysis {
    private String socialPracticeLevel;
    private String awardType;
    private Long count;
    private Double percentage;

    public String getSocialPracticeLevel() {
        return socialPracticeLevel;
    }

    public void setSocialPracticeLevel(String socialPracticeLevel) {
        this.socialPracticeLevel = socialPracticeLevel;
    }

    public String getAwardType() {
        return awardType;
    }

    public void setAwardType(String awardType) {
        this.awardType = awardType;
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
