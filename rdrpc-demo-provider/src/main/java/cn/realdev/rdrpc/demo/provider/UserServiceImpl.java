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

    @Override
    public int getId(int id) {
        return id;
    }

    @Override
    public String getName(String name) {
        return name;
    }
}
