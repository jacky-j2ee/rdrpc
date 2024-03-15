package cn.realdev.rdrpc.demo.provider;

import cn.realdev.rdrpc.core.annotation.RdProvider;
import org.springframework.stereotype.Component;

@Component
@RdProvider
public class UserServiceImpl {

    // @Override
    // public User findById(int id) {
    //     return new User(id, "rd-" + System.currentTimeMillis());
    // }


    public UserServiceImpl() {

    }
}
