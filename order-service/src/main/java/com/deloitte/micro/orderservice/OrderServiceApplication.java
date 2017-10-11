package com.deloitte.micro.orderservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EnableEurekaClient
public class OrderServiceApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}


	@Autowired
	private OrderRepository orderRepository;


	@Override
	public void run(String... strings) throws Exception {

		Stream.Builder<Orders> ordersBuilder = Stream.builder();

		ordersBuilder.accept(new Orders("Pizza", "Mohamed Taraouat"));
		ordersBuilder.accept(new Orders("Lasagna", "Stephanie Miller"));
		ordersBuilder.accept(new Orders("Steak & Cheese", "Jane Smith"));
		ordersBuilder.accept(new Orders("Cheeseburger", "Jamal Davis"));

		ordersBuilder.build().collect(Collectors.toList()).forEach(o->{
			orderRepository.save(o);
		});

	}


	@RestController
	@RefreshScope
	public class OrderServiceController {


		@Value("${greeting.message}")
		private String greeting;


        @Value("${server.port}")
        private String sourceServer;


        @GetMapping("/greet")
		public String SayGreeting(){

			return this.greeting + "from " + sourceServer;
		}




		@GetMapping("/orders")
		public List<Orders> printOrders(){

			return orderRepository.findAll();
		}



	}




}
