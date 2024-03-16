package cn.realdev.rdrpc.demo.provider;

import cn.realdev.rdrpc.core.annotation.RdProvider;
import cn.realdev.rdrpc.demo.Order;
import cn.realdev.rdrpc.demo.OrderService;
import org.springframework.stereotype.Component;

/**
 * @Author Jacky
 * @Date 2024/3/16 16:17
 * @Description:
 */
@Component
@RdProvider
public class OrderServiceImpl implements OrderService {
    @Override
    public Order findById(Integer id) {
        return new Order(id, 15.6F);
    }
}
