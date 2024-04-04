package cn.realdev.rdrpc.demo.consumer;

import cn.realdev.rdrpc.core.annotation.RdConsumer;
import cn.realdev.rdrpc.core.consumer.ConsumerConfig;
import cn.realdev.rdrpc.demo.Order;
import cn.realdev.rdrpc.demo.OrderService;
import cn.realdev.rdrpc.demo.User;
import cn.realdev.rdrpc.demo.UserService;
import lombok.Data;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({ConsumerConfig.class})
public class RdrpcDemoConsumerApplication {

	@RdConsumer
	private UserService userService;

	@RdConsumer
	private OrderService orderService;

	public static void main(String[] args) {
		SpringApplication.run(RdrpcDemoConsumerApplication.class, args);
	}

	@Bean
	public ApplicationRunner consumerRunner() {
		return x -> {
			// User user = userService.findById(1);
			// System.out.println("RPC result userService.findById(1) = " + user);
			//
			// System.out.println(userService.toString());
			//
			// System.out.println(userService.getId(100));

			System.out.println(userService.getName("abc"));
			// Order order = orderService.findById(2);
			// System.out.println("RPC result orderService.findById(2) = " + order);

			// Order order404 = orderService.findById(404);
			// System.out.println("RPC result orderService.findById(404) = " + order404);
		};
	}
}
