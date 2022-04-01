package com.liufu.store.service;

import com.liufu.store.pojo.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTest {
    @Autowired
    private AddressService addressService;
    @Test
    public void findAllByUid(){
        List<Address> allByUid = addressService.findAllByUid(11);
        System.out.println(allByUid.size());
    }

    @Test
    public void addAddress(){
        Address address = new Address();
        address.setUid(11);
        address.setName("顶呱呱");
        address.setProvinceName("湖南省");
        address.setCityName("衡阳市");
        address.setAreaName("珠晖区");
        address.setZip("421105");
        address.setAddress("1");
        address.setPhone("5454646");
        addressService.addAddress(address,9);
    }

}
