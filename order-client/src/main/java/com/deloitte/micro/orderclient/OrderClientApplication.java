package com.deloitte.micro.orderclient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableZuulProxy
@EnableHystrix
public class OrderClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderClientApplication.class, args);
	}



	@FeignClient("order-service")
	interface OrderFetcher {

		@GetMapping("/orders")
		List<Orders> fetchOrders() ;


		@GetMapping("/greet")
		String fetchGreeting() ;


	}


	@Service
	class OrderFetcherService {


		@Autowired
		private OrderFetcher orderFetcher;



		List<Orders> serviceDownFallBack(){
			List<Orders> emptyOrderList = new ArrayList<Orders>();

			return emptyOrderList;
		}

		String greetingFallBack(){
			return "System is down, please try again later ! ";
		}



		@HystrixCommand(fallbackMethod = "serviceDownFallBack")
		List<Orders> fetchAllOrders(){

			return orderFetcher.fetchOrders();
		}


		@HystrixCommand(fallbackMethod = "greetingFallBack")
		String fetchGreeting(){

			return orderFetcher.fetchGreeting();
		}


	}



	@RestController
	public class ClientController {

		@Autowired
		private OrderFetcherService orderFetcherService;



		@GetMapping("/printOrders")
		List<Orders> printAllOrders() {

			return orderFetcherService.fetchAllOrders();

		}


		@GetMapping("/showGreeting")
		String showGreeting() {
			return orderFetcherService.fetchGreeting();
		}



	}

}
