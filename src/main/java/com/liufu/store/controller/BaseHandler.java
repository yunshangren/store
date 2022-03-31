package com.liufu.store.controller;

import com.liufu.store.controller.ex.*;
import com.liufu.store.service.ex.*;
import com.liufu.store.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

public class BaseHandler {
    public static final int OK = 200;

    /**
     * 当前项目产生异常，被统一拦截到此方法中，这就是统一处理项目中异常的方法
     * 请求处理方法，这个方法的返回值类型就是传给前端的数据类型
     * 自动将异常对象传递到此方法的参数列表
     * @param e
     * @return
     */
    @ExceptionHandler({ServiceException.class, FileUploadException.class})
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result = new JsonResult<>();
        if(e instanceof UsernameDuplicatedException){
            result.setStatus(1000);
        }
        else if(e instanceof InsertException){
            result.setStatus(1001);
        }
        else if(e instanceof UsernameOrPasswordException){
            result.setStatus(1002);
        }
        else if(e instanceof WrongPasswordException){
            result.setStatus(1003);
        }
        else if(e instanceof UpdatePassWordException){
            result.setStatus(1004);
        }
        else if(e instanceof UpdateUserDataException){
            result.setStatus(1005);
        }
        else if(e instanceof UpdateAvatarException){
            result.setStatus(1006);
        }
        else if (e instanceof FileEmptyException) {
            result.setStatus(6000);
        }
        else if (e instanceof FileSizeException) {
            result.setStatus(6001);
        }
        else if (e instanceof FileTypeException) {
            result.setStatus(6002);
        }
        else if (e instanceof FileStateException) {
            result.setStatus(6003);
        }
        result.setMessage(e.getMessage());
        return result;
    }



    /**
     * 获取session对象中的uid
     * @param session
     * @return 当前用户的uid
     */
    protected final int  getUidFromSession(HttpSession session){
        return Integer.parseInt(session.getAttribute("uid").toString());
    }

    /**
     * 获取session对象中的username
     * @param session
     * @return 当前用户的username
     */
    protected final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }
}
