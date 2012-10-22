package com.twoclams.hww.server.model;

import java.io.Serializable;

public class Husband implements Serializable {

    private static final int DEFAULT_TANK_VALUE = 10;
    private static final int DEFAULT_STRESS_COOLDOWN = 0;
    private static final int DEFAULT_IS_SHOPPING = 0;
    private static final int DEFAULT_LOCAL_VISITS = 0;
    private static final int DEFAULT_REQUIRED_VISITS = 1;
    private static final int DEFAULT_CITIES_VISITED = 0;
    private static final int DEFAULT_KISS_COST = 40;
    private static final int DEFAULT_WORK_HOURS = 60;
    private static final int DEFAULT_PLAY_VIDEO_COST = 80;
    private static final int DEFAULT_LOVE_COOLDOWN = 0;
    private static final int DEFAULT_STRESS_VALUE = 0;
    private static final int DEFAULT_BUFF_WORK_TIME = 0;
    private static final int DEFAULT_SALARY = 100;
    private static final int DEFAULT_IS_WORKING = 0;
    private static final int DEFAULT_WATCH_GAME_COST = 1;
    private static final int DEFAULT_WORK_STRESS_VALUE = 1;
    private static final int DEFAULT_SHOPPING_COUNT = 0;
    private static final int DEFAULT_SSP_WORK = 5;
    private static final int DEFAULT_SHOPPING_VALUE = 2;
    private static final int DEFAULT_DATE_COST = 1;
    private static final int DEFAULT_RARE_ITEM_THRESHOLD = 80;
    private static final int DEFAULT_SALARY_FACTOR = 1;
    private static final int DEFAULT_TOTAL_VISITS = 0;
    private static final int DEFAULT_STRESS_LEVEL = 0;
    private static final int DEFAULT_CARREER_LEVEL = 1;
    private static final int DEFAULT_OCCUPATION = 0;
    private static final long serialVersionUID = 1319386899213068721L;
    private String papayaUserId;
    private String name;
    private Integer occupation = DEFAULT_OCCUPATION;
    private Integer careerLevel = DEFAULT_CARREER_LEVEL;
    private Integer stressLevel = DEFAULT_STRESS_LEVEL;
    private Integer totalVisits = DEFAULT_TOTAL_VISITS;
    private Integer salaryFactor = DEFAULT_SALARY_FACTOR;
    private Integer rareItemThreshold = DEFAULT_RARE_ITEM_THRESHOLD;
    private Integer goOnADateCost = DEFAULT_DATE_COST;
    private Integer shoppingDreadValue = DEFAULT_SHOPPING_VALUE;
    private Integer workSSPReturn = DEFAULT_SSP_WORK;
    private Integer shoppingCounts = DEFAULT_SHOPPING_COUNT;
    private Integer workStressorValue = DEFAULT_WORK_STRESS_VALUE;
    private Integer watchTheGameCost = DEFAULT_WATCH_GAME_COST;
    private Integer outWorking = DEFAULT_IS_WORKING;
    private Integer salary = DEFAULT_SALARY;
    private Integer workBuffTime = DEFAULT_BUFF_WORK_TIME;
    private Integer stressMeterValue = DEFAULT_STRESS_VALUE;
    private Integer loveCooldown = DEFAULT_LOVE_COOLDOWN;
    private Integer playVideoGameCost = DEFAULT_PLAY_VIDEO_COST;
    private Integer workHours = DEFAULT_WORK_HOURS;
    private Integer kissCost = DEFAULT_KISS_COST;
    private Integer citiesVisited = DEFAULT_CITIES_VISITED;
    private Integer requiredVisits = DEFAULT_REQUIRED_VISITS;
    private Integer localVisits = DEFAULT_LOCAL_VISITS;
    private Integer outShopping = DEFAULT_IS_SHOPPING;
    private Integer stressCooldown = DEFAULT_STRESS_COOLDOWN;
    private Integer loveTankValue = DEFAULT_TANK_VALUE;

    public Husband() {
        update();
    }

    public Husband(Integer salaryFactor, Integer rareItemThreshold, Integer goOnADateCost, Integer shoppingDreadValue,
            Integer workSSPReturn, Integer shoppingCounts, Integer workStressorValue, Integer watchTheGameCost,
            Integer outWorking, String name, Integer careerLevel, Integer totalVisits, Integer salary,
            Integer workBuffTime, Integer stressMeterValue, Integer loveCooldown, Integer playVideoGameCost,
            Integer workHours, Integer kissCost, String papayaUserId, Integer citiesVisited, Integer localVisits,
            Integer requiredVisits, Integer outShopping, Integer stressCooldown, Integer loveTankValue,
            Integer occupation) {
        this.papayaUserId = papayaUserId;
        this.name = name;
        this.occupation = occupation;
        this.careerLevel = careerLevel;
        this.stressLevel = stressMeterValue;
        this.totalVisits = totalVisits;
        this.salaryFactor = salaryFactor;
        this.rareItemThreshold = rareItemThreshold;
        this.goOnADateCost = goOnADateCost;
        this.shoppingDreadValue = shoppingDreadValue;
        this.workSSPReturn = workSSPReturn;
        this.shoppingCounts = shoppingCounts;
        this.workStressorValue = workStressorValue;
        this.watchTheGameCost = watchTheGameCost;
        this.outWorking = outWorking;
        this.salary = salary;
        this.workBuffTime = workBuffTime;
        this.stressMeterValue = stressMeterValue;
        this.loveCooldown = loveCooldown;
        this.playVideoGameCost = playVideoGameCost;
        this.workHours = workHours;
        this.kissCost = kissCost;
        this.citiesVisited = citiesVisited;
        this.requiredVisits = requiredVisits;
        this.localVisits = localVisits;
        this.outShopping = outShopping;
        this.stressCooldown = stressCooldown;
        this.loveTankValue = loveTankValue;
        update();
    }

    public void update() {
        if (occupation == null) {
            this.occupation = DEFAULT_OCCUPATION;
        }
        if (careerLevel == null) {
            this.careerLevel = DEFAULT_CARREER_LEVEL;
        }
        if (stressMeterValue == null) {
            this.stressLevel = DEFAULT_STRESS_LEVEL;
        }
        if (totalVisits == null) {
            this.totalVisits = DEFAULT_TOTAL_VISITS;
        }
        if (salaryFactor == null) {
            this.salaryFactor = DEFAULT_SALARY_FACTOR;
        }
        if (rareItemThreshold == null) {
            this.rareItemThreshold = DEFAULT_RARE_ITEM_THRESHOLD;
        }
        if (goOnADateCost == null) {
            this.goOnADateCost = DEFAULT_DATE_COST;
        }
        if (shoppingDreadValue == null) {
            this.shoppingDreadValue = DEFAULT_SHOPPING_VALUE;
        }
        if (workSSPReturn == null) {
            this.workSSPReturn = DEFAULT_SSP_WORK;
        }
        if (shoppingCounts == null) {
            this.shoppingCounts = DEFAULT_SHOPPING_COUNT;
        }
        if (workStressorValue == null) {
            this.workStressorValue = DEFAULT_WORK_STRESS_VALUE;
        }
        if (watchTheGameCost == null) {
            this.watchTheGameCost = DEFAULT_WATCH_GAME_COST;
        }
        this.outWorking = DEFAULT_IS_WORKING;
        if (salary == null) {
            this.salary = DEFAULT_SALARY;
        }
        if (workBuffTime == null) {
            this.workBuffTime = DEFAULT_BUFF_WORK_TIME;
        }
        if (stressMeterValue == null) {
            this.stressMeterValue = DEFAULT_STRESS_VALUE;
        }
        if (loveCooldown == null) {
            this.loveCooldown = DEFAULT_LOVE_COOLDOWN;
        }
        if (playVideoGameCost == null) {
            this.playVideoGameCost = DEFAULT_PLAY_VIDEO_COST;
        }
        if (workHours == null) {
            this.workHours = DEFAULT_WORK_HOURS;
        }
        if (kissCost == null) {
            this.kissCost = DEFAULT_KISS_COST;
        }
        if (citiesVisited == null) {
            this.citiesVisited = DEFAULT_CITIES_VISITED;
        }
        if (requiredVisits == null) {
            this.requiredVisits = DEFAULT_REQUIRED_VISITS;
        }
        if (localVisits == null) {
            this.localVisits = DEFAULT_LOCAL_VISITS;
        }
        this.outShopping = DEFAULT_IS_SHOPPING;
        if (stressCooldown == null) {
            this.stressCooldown = DEFAULT_STRESS_COOLDOWN;
        }
        if (loveTankValue == null) {
            this.loveTankValue = DEFAULT_TANK_VALUE;
        }
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getPapayaUserId() {
        return papayaUserId;
    }

    public String getName() {
        return name;
    }

    public Integer getOccupation() {
        return occupation;
    }

    public Integer getCareerLevel() {
        return careerLevel;
    }

    public Integer getStressLevel() {
        return stressLevel;
    }

    public Integer getTotalVisits() {
        return totalVisits;
    }

    public Integer getSalaryFactor() {
        return salaryFactor;
    }

    public Integer getRareItemThreshold() {
        return rareItemThreshold;
    }

    public Integer getGoOnADateCost() {
        return goOnADateCost;
    }

    public Integer getShoppingDreadValue() {
        return shoppingDreadValue;
    }

    public Integer getWorkSSPReturn() {
        return workSSPReturn;
    }

    public Integer getShoppingCounts() {
        return shoppingCounts;
    }

    public Integer getWorkStressorValue() {
        return workStressorValue;
    }

    public Integer getWatchTheGameCost() {
        return watchTheGameCost;
    }

    public Integer getOutWorking() {
        return outWorking;
    }

    public Integer getSalary() {
        return salary;
    }

    public Integer getWorkBuffTime() {
        return workBuffTime;
    }

    public Integer getStressMeterValue() {
        return stressMeterValue;
    }

    public Integer getLoveCooldown() {
        return loveCooldown;
    }

    public Integer getPlayVideoGameCost() {
        return playVideoGameCost;
    }

    public Integer getWorkHours() {
        return workHours;
    }

    public Integer getKissCost() {
        return kissCost;
    }

    public Integer getCitiesVisited() {
        return citiesVisited;
    }

    public Integer getRequiredVisits() {
        return requiredVisits;
    }

    public Integer getLocalVisits() {
        return localVisits;
    }

    public Integer getOutShopping() {
        return outShopping;
    }

    public Integer getStressCooldown() {
        return stressCooldown;
    }

    public Integer getLoveTankValue() {
        return loveTankValue;
    }

    @Override
    public String toString() {
        return "Husband [papayaUserId=" + papayaUserId + ", name=" + name + ", occupation=" + occupation
                + ", careerLevel=" + careerLevel + ", stressLevel=" + stressLevel + ", totalVisits=" + totalVisits
                + ", salaryFactor=" + salaryFactor + ", rareItemThreshold=" + rareItemThreshold + ", goOnADateCost="
                + goOnADateCost + ", shoppingDreadValue=" + shoppingDreadValue + ", workSSPReturn=" + workSSPReturn
                + ", shoppingCounts=" + shoppingCounts + ", workStressorValue=" + workStressorValue
                + ", watchTheGameCost=" + watchTheGameCost + ", outWorking=" + outWorking + ", salary=" + salary
                + ", workBuffTime=" + workBuffTime + ", stressMeterValue=" + stressMeterValue + ", loveCooldown="
                + loveCooldown + ", playVideoGameCost=" + playVideoGameCost + ", workHours=" + workHours
                + ", kissCost=" + kissCost + ", citiesVisited=" + citiesVisited + ", requiredVisits=" + requiredVisits
                + ", localVisits=" + localVisits + ", outShopping=" + outShopping + ", stressCooldown="
                + stressCooldown + ", loveTankValue=" + loveTankValue + "]";
    }

}
