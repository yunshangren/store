package com.liufu.store.mapper;

import com.liufu.store.pojo.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressMapperTest {
    @Autowired
    private AddressMapper addressMapper;
    @Test
    public void addAddress(){
        Address address = new Address();
        address.setUid(11);
        address.setName("顶呱呱");
        address.setProvinceName("湖南省");
        address.setCityName("衡阳市");
        address.setAreaName("珠晖区");
        address.setZip("421105");
        address.setAddress("xihu小区222");
        address.setPhone("5454646");
        address.setIsDefault(0);

        addressMapper.addAddress(address);


    }
}
