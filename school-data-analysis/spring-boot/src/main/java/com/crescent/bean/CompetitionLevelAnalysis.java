package com.crescent.bean;

public class CompetitionLevelAnalysis {
    private String type;
    private String cLevel;
    private Long count;
    private Double competitionLevelPerc;
    private Double typePerc;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getcLevel() {
        return cLevel;
    }

    public void setcLevel(String cLevel) {
        this.cLevel = cLevel;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Double getCompetitionLevelPerc() {
        return competitionLevelPerc;
    }

    public void setCompetitionLevelPerc(Double competitionLevelPerc) {
        this.competitionLevelPerc = competitionLevelPerc;
    }

    public Double getTypePerc() {
        return typePerc;
    }

    public void setTypePerc(Double typePerc) {
        this.typePerc = typePerc;
    }
}
