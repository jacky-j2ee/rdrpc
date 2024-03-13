package cn.realdev.rdrpc.core.api;

import lombok.Data;

@Data
public class RpcRequest {

    /**
     * 服务：cn.realdev.rdrpc.demo.UserService
     */
    private String service;

    /**
     * 方法：findById(int)
     */
    private String method;

    /**
     * 参数：199
     */
    private Object[] args;
}
