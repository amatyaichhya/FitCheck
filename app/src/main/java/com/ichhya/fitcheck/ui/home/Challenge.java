package com.ichhya.fitcheck.ui.home;

import java.util.Date;

public class Challenge {

    private String challengeId;
    private String challengeName;
    private String challengeBy;
    private Integer challengeDays;
    private Date challengeDate;

    public Challenge(){

    }

    public Challenge(String challengeId, String challengeName, String challengeBy, Integer challengeDays, Date challengeDate) {
        this.challengeId = challengeId;
        this.challengeName = challengeName;
        this.challengeBy = challengeBy;
        this.challengeDays = challengeDays;
        this.challengeDate = challengeDate;
    }

    public String getChallengeId() {
        return challengeId;
    }

    public String getChallengeName() {
        return challengeName;
    }

    public String getChallengeBy() {
        return challengeBy;
    }

    public Integer getChallengeDays() {
        return challengeDays;
    }

    public Date getChallengeDate() {
        return challengeDate;
    }

    public void setChallengeId(String challengeId) {
        this.challengeId = challengeId;
    }

    public void setChallengeName(String challengeName) {
        this.challengeName = challengeName;
    }

    public void setChallengeBy(String challengeBy) {
        this.challengeBy = challengeBy;
    }

    public void setChallengeDays(Integer challengeDays) {
        this.challengeDays = challengeDays;
    }

    public void setChallengeDate(Date challengeDate) {
        this.challengeDate = challengeDate;
    }
}
