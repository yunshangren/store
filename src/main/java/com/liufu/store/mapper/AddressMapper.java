package com.liufu.store.mapper;

import com.liufu.store.pojo.Address;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface AddressMapper {
    @Select("select * from t_address where uid = #{uid}")
    List<Address> findAllByUid(int uid);

    @Insert("insert into t_address (uid, name, province_name, city_name, area_name, zip, address, phone, tel, tag, is_default)\n" +
            "values (#{uid},#{name},#{provinceName},#{cityName},#{areaName},#{zip},#{address},#{phone}," +
            "#{tel},#{tag},#{isDefault})")
    int addAddress(Address address);

    @Select("select count(*) from t_address where uid = #{uid}")
    int countByUid(int uid);
}
