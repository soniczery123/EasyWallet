package com.example.easywallet;

/**
 * Created by MSI-GL62 on 10/12/2560.
 */

public class detailItem {
    int id;
    int picture ;
    String detail;
    int money;
    String type;

    public detailItem(int id,int picture, String detail, int money,String type) {
        this.id = id;
        this.picture = picture;
        this.detail = detail;
        this.money = money;
        this.type = type;
    }
}
