package com.liufu.store.controller;

import com.liufu.store.controller.BaseHandler;
import com.liufu.store.controller.ex.AddressOverFlowException;
import com.liufu.store.pojo.Address;
import com.liufu.store.service.AddressService;
import com.liufu.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/address")
public class AddressController extends BaseHandler {
    @Autowired
    private AddressService addressService;

    @PostMapping("/add")
    public JsonResult<Void> addAddress(HttpSession session, Address address){
        int uid = getUidFromSession(session);
        int count = addressService.countByUid(uid);
        if(count > 20){
            throw new AddressOverFlowException("收货地址已达到20条，不能再添加收获地址");
        }

        addressService.addAddress(address,uid);
        return new JsonResult<>(OK,"添加地址成功");
    }


}
