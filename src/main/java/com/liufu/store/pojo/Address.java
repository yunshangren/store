package com.liufu.store.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address extends Base{
    private int aid;
    private int uid;
    private String name; //收货人姓名
    private String provinceName;
    private String cityName;
    private String areaName;
    private String zip;
    private String address; //详细地址
    private String phone;
    private String tel;
    private String tag;
    private int isDefault; // 是否为默认地址，0-非默认地址 1-默认地址
}
