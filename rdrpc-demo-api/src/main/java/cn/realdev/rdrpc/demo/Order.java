package cn.realdev.rdrpc.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Jacky
 * @Date 2024/3/16 16:16
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    Integer id;

    Float amount;
}
