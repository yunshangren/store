package com.liufu.store.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonResult<E> implements Serializable {
    private int status;
    private String message;
    private E data;

    public JsonResult(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public JsonResult(int status, E data) {
        this.status = status;
        this.data = data;
    }
}
