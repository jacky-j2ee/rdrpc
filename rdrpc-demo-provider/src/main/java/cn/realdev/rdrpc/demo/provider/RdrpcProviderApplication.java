package cn.realdev.rdrpc.demo.provider;

import cn.realdev.rdrpc.core.annotation.RdProvider;
import cn.realdev.rdrpc.core.api.RpcRequest;
import cn.realdev.rdrpc.core.api.RpcResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@RestController
@SpringBootApplication
public class RdrpcProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(RdrpcProviderApplication.class, args);
    }

    // 使用http + json来时实现序列化和通信
    public RpcResponse invoke(RpcRequest request) {
        return invokeRequest(request);
    }

    private RpcResponse invokeRequest(RpcRequest request) {
        Object bean = skeleton.get(request.getService());
        try {
            Method method = bean.getClass().getMethod(request.getMethod());
            Object result = method.invoke(bean, request.getArgs());
            return new RpcResponse(true, result);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Autowired
    private ApplicationContext context;

    private Map<String, Object> skeleton = new HashMap<>();

    @PostConstruct
    public void buildProviders() {
        Map<String, Object> providers = context.getBeansWithAnnotation(RdProvider.class);
        providers.forEach((k, v) -> {
            System.out.println(k);
        });
        providers.values().forEach(i -> getInterface(i));
    }

    private void getInterface(Object i) {
        Class<?> intf = i.getClass().getInterfaces()[0];
        skeleton.put(intf.getCanonicalName(), i);
    }
}
