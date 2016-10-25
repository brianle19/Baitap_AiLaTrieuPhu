package com.thaile.baitap_ailatrieuphu;

/**
 * Created by Le on 8/25/2016.
 */
public class UserObject {
    private String userName;
    private String score;

    public UserObject(String userName, String score) {
        this.userName = userName;
        this.score = score;
    }

    public String getUserName() {
        return userName;
    }

    public String getScore() {
        return score;
    }
}
