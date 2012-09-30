package com.twoclams.hww.server.model;

import java.io.Serializable;

public class Husband implements Serializable {

    private static final long serialVersionUID = 1319386899213068721L;
    private String papayaUserId;
    private String name;
    private Integer occupation;
    private Integer careerLevel;
    private Integer stressLevel;
    private Integer loveTank;
    private Integer totalVisits;

    public Husband () {
    }

    public Husband(String id, Integer occupation, String name, Integer careerLevel,
            Integer stressLevel, Integer loveTank, Integer totalVisits) {
        this.papayaUserId = id;
        this.occupation = occupation;
        this.name = name;
        this.careerLevel = careerLevel;
        this.stressLevel = stressLevel;
        this.loveTank = loveTank;
        this.totalVisits = totalVisits;
    }

    public String getPapayaUserId() {
        return papayaUserId;
    }

    public void setPapayaUserId(String papayaUserId) {
        this.papayaUserId = papayaUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOccupation() {
        return occupation;
    }

    public void setOccupation(Integer occupation) {
        this.occupation = occupation;
    }

    public Integer getCareerLevel() {
        return careerLevel;
    }

    public void setCareerLevel(Integer careerLevel) {
        this.careerLevel = careerLevel;
    }

    public String getId() {
        return this.papayaUserId;
    }

    @Override
    public String toString() {
        return "Husband [papayaUserId=" + papayaUserId + ", name=" + name + ", occupation="
                + occupation + ", careerLevel=" + careerLevel + "]";
    }

    public Integer getStressLevel() {
        return stressLevel;
    }

    public void setStressLevel(Integer stressLevel) {
        this.stressLevel = stressLevel;
    }

    public Integer getLoveTank() {
        return loveTank;
    }

    public void setLoveTank(Integer loveTank) {
        this.loveTank = loveTank;
    }

    public Integer getTotalVisits() {
        return totalVisits;
    }

    public void setTotalVisits(Integer totalVisits) {
        this.totalVisits = totalVisits;
    }

}
