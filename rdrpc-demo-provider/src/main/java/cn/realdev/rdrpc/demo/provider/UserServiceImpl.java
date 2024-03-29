package cn.realdev.rdrpc.demo.provider;

import cn.realdev.rdrpc.core.annotation.RdProvider;
import cn.realdev.rdrpc.demo.User;
import cn.realdev.rdrpc.demo.UserService;
import org.springframework.stereotype.Component;

@Component
@RdProvider
public class UserServiceImpl implements UserService {

    @Override
    public User findById(int id) {
        return new User(id, "rd-" + System.currentTimeMillis());
    }
}
