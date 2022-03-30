package com.liufu.store.controller;

import com.liufu.store.service.ex.ServiceException;
import com.liufu.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseHandler {
    public static final int OK = 200;

    /**
     * 当前项目产生异常，被统一拦截到此方法中，这就是统一处理项目中异常的方法
     * 请求处理方法，这个方法的返回值类型就是传给前端的数据类型
     * 自动将异常对象传递到此方法的参数列表
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result = new JsonResult<>();
        result.setStatus(400);
        result.setMessage(e.getMessage());
        return result;
    }

}
