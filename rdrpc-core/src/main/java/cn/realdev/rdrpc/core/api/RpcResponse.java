package cn.realdev.rdrpc.core.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RpcResponse<T> {

    /**
     * 状态：true
     */
    boolean status;

    /**
     * new User
     */
    T data;

    Exception ex;
}
