package com.footpath.inventory.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class CustomCategoryException extends RuntimeException{

    private Map<String, String> errorModel;

    public CustomCategoryException(String exceptionMsgEnum, String desc, Map<Object, Object> exceptionMap){
        super();
        if(exceptionMap==null) {
            errorModel = new HashMap<>();
            errorModel.put(exceptionMsgEnum, desc);
        }
    }

}
