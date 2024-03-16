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

    @PostConstruct
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
        Object bean = skeleton.get(request.getService());
        try {
            Method method = this.findMethod(bean.getClass(), request.getMethod());
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

    private Method findMethod(Class<?> aClass, String methodName) throws NoSuchMethodException {
        for (Method method : aClass.getMethods()) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        throw new NoSuchMethodException();
    }
}
