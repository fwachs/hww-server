package com.twoclams.hww.server.model;

import java.io.Serializable;
import java.util.Arrays;

public class Passport implements Serializable {
    private static final long serialVersionUID = 2017756907516621740L;

    private String papayaUserId;
    private Integer[] buenosAiresSouvenirs;
    private Integer[] tokyoSouvenirs;
    private Integer[] sydneySouvenirs;
    private Integer[] londonSouvenirs;
    private Integer[] parisSouvenirs;
    private Integer[] sanFranciscoSouvenirs;
    private String[] datesCompleted;

    private Integer tokyoFirstVisit;
    private Integer parisFirstVisit;
    private Integer londonFirstVisit;
    private Integer sanFranciscoFirstVisit;
    private Integer sydneyFirstVisit;
    private Integer buenosAiresFirstVisit;
    private Integer citiesVisited;

    public Passport(String papayaUserId, Integer[] buenosAiresSouvenirs, Integer[] tokyoSouvenirs, Integer[] sydneySouvenirs,
            Integer[] londonSouvenirs, Integer[] parisSouvenirs, Integer[] sanFranciscoSouvenirs,
            String[] datesCompleted, Integer tokyoFirstVisit, Integer parisFirstVisit, Integer londonFirstVisit,
            Integer sanFranciscoFirstVisit, Integer sydneyFirstVisit, Integer buenosAiresFirstVisit,
            Integer citiesVisited) {
        this.papayaUserId = papayaUserId;
        this.buenosAiresSouvenirs = buenosAiresSouvenirs;
        this.tokyoSouvenirs = tokyoSouvenirs;
        this.sydneySouvenirs = sydneySouvenirs;
        this.londonSouvenirs = londonSouvenirs;
        this.parisSouvenirs = parisSouvenirs;
        this.sanFranciscoSouvenirs = sanFranciscoSouvenirs;
        this.datesCompleted = datesCompleted;
        this.tokyoFirstVisit = tokyoFirstVisit;
        this.parisFirstVisit = parisFirstVisit;
        this.londonFirstVisit = londonFirstVisit;
        this.sanFranciscoFirstVisit = sanFranciscoFirstVisit;
        this.sydneyFirstVisit = sydneyFirstVisit;
        this.buenosAiresFirstVisit = buenosAiresFirstVisit;
        this.citiesVisited = citiesVisited;
    }

    public Integer[] getBuenosAiresSouvenirs() {
        return buenosAiresSouvenirs;
    }

    public Integer[] getTokyoSouvenirs() {
        return tokyoSouvenirs;
    }

    public Integer[] getSydneySouvenirs() {
        return sydneySouvenirs;
    }

    public Integer[] getLondonSouvenirs() {
        return londonSouvenirs;
    }

    public Integer[] getParisSouvenirs() {
        return parisSouvenirs;
    }

    public Integer[] getSanFranciscoSouvenirs() {
        return sanFranciscoSouvenirs;
    }

    public String[] getDatesCompleted() {
        return datesCompleted;
    }

    public Integer getTokyoFirstVisit() {
        return tokyoFirstVisit;
    }

    public Integer getParisFirstVisit() {
        return parisFirstVisit;
    }

    public Integer getLondonFirstVisit() {
        return londonFirstVisit;
    }

    public Integer getSanFranciscoFirstVisit() {
        return sanFranciscoFirstVisit;
    }

    public Integer getSydneyFirstVisit() {
        return sydneyFirstVisit;
    }

    public Integer getBuenosAiresFirstVisit() {
        return buenosAiresFirstVisit;
    }

    public Integer getCitiesVisited() {
        return citiesVisited;
    }

    public String getPapayaUserId() {
        return papayaUserId;
    }

    @Override
    public String toString() {
        return "Passport [buenosAiresSouvenirs=" + Arrays.toString(buenosAiresSouvenirs) + ", tokyoSouvenirs="
                + Arrays.toString(tokyoSouvenirs) + ", sydneySouvenirs=" + Arrays.toString(sydneySouvenirs)
                + ", londonSouvenirs=" + Arrays.toString(londonSouvenirs) + ", parisSouvenirs="
                + Arrays.toString(parisSouvenirs) + ", sanFranciscoSouvenirs=" + Arrays.toString(sanFranciscoSouvenirs)
                + ", datesCompleted=" + Arrays.toString(datesCompleted) + ", tokyoFirstVisit=" + tokyoFirstVisit
                + ", parisFirstVisit=" + parisFirstVisit + ", londonFirstVisit=" + londonFirstVisit
                + ", sanFranciscoFirstVisit=" + sanFranciscoFirstVisit + ", sydneyFirstVisit=" + sydneyFirstVisit
                + ", buenosAiresFirstVisit=" + buenosAiresFirstVisit + ", citiesVisited=" + citiesVisited + "]";
    }

}
