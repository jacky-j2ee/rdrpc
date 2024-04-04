package cn.realdev.rdrpc.demo.provider;

import cn.realdev.rdrpc.core.api.RpcRequest;
import cn.realdev.rdrpc.core.api.RpcResponse;
import cn.realdev.rdrpc.core.provider.ProviderBootstrap;
import cn.realdev.rdrpc.core.provider.ProviderConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Import({ProviderConfig.class})
public class RdrpcDemoProviderApplication {

    @Autowired
    private ProviderBootstrap providerBootstrap;

    public static void main(String[] args) {
        SpringApplication.run(RdrpcDemoProviderApplication.class, args);
    }

    // 使用http + json来时实现序列化和通信
    @RequestMapping("/")
    public RpcResponse invoke(@RequestBody RpcRequest request) {
        return providerBootstrap.invoke(request);
    }

    @Bean
    public ApplicationRunner runner() {
        return x -> {
            RpcRequest request = new RpcRequest();
            request.setService("cn.realdev.rdrpc.demo.UserService");
            request.setMethod("findById");
            request.setArgs(new Object[]{100});
            RpcResponse rpcResponse = this.invoke(request);
            System.out.println(rpcResponse);
        };
    }
}
