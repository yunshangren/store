package com.liufu.store.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult<E> implements Serializable {
    private Integer status;
    private String message;
    private E data;

    public JsonResult(Integer status, String message) {
        this.status = status;
        this.message = message;
    }


}
