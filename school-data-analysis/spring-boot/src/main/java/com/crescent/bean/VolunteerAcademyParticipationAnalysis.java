package com.crescent.bean;

public class VolunteerAcademyParticipationAnalysis {
    private String academy;
    private Long count;
    private Long countAll;
    private Double percentage;

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getCount_all() {
        return countAll;
    }

    public void setCount_all(Long count_all) {
        this.countAll = countAll;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}
