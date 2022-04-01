package com.liufu.store.service;

import com.liufu.store.mapper.AddressMapper;
import com.liufu.store.pojo.Address;
import com.liufu.store.service.ex.AddAddressException;
import com.liufu.store.controller.ex.AddressOverFlowException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AddressService {
    @Autowired
    private AddressMapper addressMapper;

    public List<Address> findAllByUid(int uid){
        return addressMapper.findAllByUid(uid);
    }

    public void addAddress(Address address, int uid){
        address.setUid(uid);
        int count = addressMapper.countByUid(uid);
        if(count == 0){
            address.setIsDefault(1);
        }else{
            address.setIsDefault(0);
        }
      /*  address.setCreatedUser(username);
        address.setModifiedUser(username);
        Date date = new Date();
        address.setCreatedTime(date);
        address.setModifiedTime(date);*/
        int row = addressMapper.addAddress(address);
        if(row != 1){
            throw new AddAddressException("新增地址产生异常");
        }
    }

    public int countByUid(int uid) {
        return addressMapper.countByUid(uid);
    }
}
