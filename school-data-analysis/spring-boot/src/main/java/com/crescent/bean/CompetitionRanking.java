package com.crescent.bean;

public class CompetitionRanking {
    private String type;
    private String awardName;
    private String competitionLevel;
    private Long count;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public String getCompetitionLevel() {
        return competitionLevel;
    }

    public void setCompetitionLevel(String competitionLevel) {
        this.competitionLevel = competitionLevel;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
