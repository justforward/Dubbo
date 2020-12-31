package com.study.sdk.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author wangtan
 * @Date 2020/12/29
 */
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -6358275277756933200L;
    private T data;
    private Boolean success;
    private String msg;
}
