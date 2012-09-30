package com.twoclams.hww.server.model;

import java.io.Serializable;
import java.util.Date;

public class DailyBonus implements Serializable {

    private static final long serialVersionUID = 8641599795434535708L;
    private Date lastLogin;
    private int count;

    public DailyBonus() {
        lastLogin = new Date();
        count = 1;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void increment() {
        this.count++;
    }

    public Reward getReward() {
        Reward reward = null;
        switch (this.count) {
        case 1:
            reward = Reward.DAY_1;
            break;
        case 2:
            reward = Reward.DAY_2;
            break;
        case 3:
            reward = Reward.DAY_3;
            break;
        case 4:
            reward = Reward.DAY_4;
            break;
        case 5:
            reward = Reward.DAY_5;
            break;
        default:
            reward = Reward.DAY_1;
        }
        this.lastLogin = new Date(); 
        return reward;
    }

    public enum Reward {
        DAY_1(1, "GameBucks", 1000), DAY_2(2, "GameBucks", 2000), DAY_3(3, "SocialStatusPoints",
                100), DAY_4(4, "Miles", 1000), DAY_5(5, "Diamonds", 5);

        private String currency;
        private long amount;
        private int count;

        private Reward(int count, String currency, long amount) {
            this.count = count;
            this.currency = currency;
            this.amount = amount;
        }

        public long getAmount() {
            return amount;
        }

        public String getCurrency() {
            return currency;
        }

        public int getCount() {
            return count;
        }
    }

    public void reset() {
        this.count = 1;
    }

}
