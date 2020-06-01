package com.crescent.bean;

public class VolunteerGradeParticipationAnalysis {
    private String grade;
    private Long count;
    private Long countAll;
    private Double percentage;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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

    public void setCount_all(Long countAll) {
        this.countAll = countAll;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}
