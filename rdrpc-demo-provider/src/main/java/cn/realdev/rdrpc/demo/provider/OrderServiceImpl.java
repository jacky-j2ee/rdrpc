package cn.realdev.rdrpc.demo.provider;

import cn.realdev.rdrpc.core.annotation.RdProvider;
import cn.realdev.rdrpc.demo.Order;
import cn.realdev.rdrpc.demo.OrderService;
import org.springframework.stereotype.Component;

import java.util.Objects;

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

        if (Objects.equals(id, 404)) {
            throw new RuntimeException("404 exception");
        }

        return new Order(id, 15.6F);
    }
}
