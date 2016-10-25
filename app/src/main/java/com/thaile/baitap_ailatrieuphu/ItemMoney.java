package com.thaile.baitap_ailatrieuphu;

/**
 * Created by Le on 8/24/2016.
 */
public class ItemMoney {
    private String money;
    private String stt;

    public ItemMoney(String stt, String money) {
        this.stt = stt;
        this.money = money;
    }

    public String getMoney() {
        return money;
    }

    public String getStt() {
        return stt;
    }
}
