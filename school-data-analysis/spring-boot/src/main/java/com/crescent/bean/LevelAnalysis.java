package com.crescent.bean;

public class LevelAnalysis {
    private String grade;
    private String academy;
    private Double researchInnovationAvg;
    private Double professionalSkillAvg;
    private Double stylisticSpecialtiesAvg;
    private Double groupWorkAvg;
    private Double socialPracticeAvg;
    private Double total;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public Double getResearchInnovationAvg() {
        return researchInnovationAvg;
    }

    public void setResearchInnovationAvg(Double researchInnovationAvg) {
        this.researchInnovationAvg = researchInnovationAvg;
    }

    public Double getProfessionalSkillAvg() {
        return professionalSkillAvg;
    }

    public void setProfessionalSkillAvg(Double professionalSkillAvg) {
        this.professionalSkillAvg = professionalSkillAvg;
    }

    public Double getStylisticSpecialtiesAvg() {
        return stylisticSpecialtiesAvg;
    }

    public void setStylisticSpecialtiesAvg(Double stylisticSpecialtiesAvg) {
        this.stylisticSpecialtiesAvg = stylisticSpecialtiesAvg;
    }

    public Double getGroupWorkAvg() {
        return groupWorkAvg;
    }

    public void setGroupWorkAvg(Double groupWorkAvg) {
        this.groupWorkAvg = groupWorkAvg;
    }

    public Double getSocialPracticeAvg() {
        return socialPracticeAvg;
    }

    public void setSocialPracticeAvg(Double socialPracticeAvg) {
        this.socialPracticeAvg = socialPracticeAvg;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "LevelAnalysis{" +
                "grade='" + grade + '\'' +
                ", academy='" + academy + '\'' +
                ", researchInnovationAvg=" + researchInnovationAvg +
                ", professionalSkillAvg=" + professionalSkillAvg +
                ", stylisticSpecialtiesAvg=" + stylisticSpecialtiesAvg +
                ", groupWorkAvg=" + groupWorkAvg +
                ", socialPracticeAvg=" + socialPracticeAvg +
                ", total=" + total +
                '}';
    }
}
