package cn.realdev.rdrpc.demo;

public interface UserService {

    User findById(int id);

    int getId(int id);

    String getName(String name);
}
