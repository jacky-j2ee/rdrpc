package cn.realdev.rdrpc.core.provider;

import cn.realdev.rdrpc.core.annotation.RdProvider;
import cn.realdev.rdrpc.core.api.RpcRequest;
import cn.realdev.rdrpc.core.api.RpcResponse;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Jacky
 * @Date 2024/3/16 15:42
 * @Description:
 */
@Data
public class ProviderBootstrap implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private Map<String, Object> skeleton = new HashMap<>();

    @PostConstruct // init-method
    public void buildProviders() {
        Map<String, Object> providers = applicationContext.getBeansWithAnnotation(RdProvider.class);
        providers.forEach((k, v) -> {
            System.out.println(k);
        });
        providers.values().forEach(i -> getInterface(i));
    }

    private void getInterface(Object i) {
        Class<?> intf = i.getClass().getInterfaces()[0];
        skeleton.put(intf.getCanonicalName(), i);
    }

    public RpcResponse invoke(RpcRequest request) {
        RpcResponse rpcResponse = new RpcResponse();
        Object bean = skeleton.get(request.getService());
        try {
            Method method = this.findMethod(bean.getClass(), request.getMethod());
            Object result = method.invoke(bean, request.getArgs());
            rpcResponse.setStatus(true);
            rpcResponse.setData(result);
            return rpcResponse;
        } catch (Exception e) {
            rpcResponse.setEx(new RuntimeException(e.getCause().getMessage()));
        }
        return rpcResponse;
    }

    private Method findMethod(Class<?> aClass, String methodName) {
        for (Method method : aClass.getMethods()) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        return null;
        // throw new NoSuchMethodException();
    }
}
