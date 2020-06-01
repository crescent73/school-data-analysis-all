package com.crescent.bean;

public class NationAnalysis {

    private String nation;
    private Long count;
    private Double percentage;

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
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

    @Override
    public String toString() {
        return "NationAnalysis{" +
                "nation='" + nation + '\'' +
                ", count=" + count +
                ", percentage=" + percentage +
                '}';
    }
}
