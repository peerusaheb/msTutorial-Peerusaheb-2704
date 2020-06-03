package com.ms.boot.Convertcurrencyms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.ms.boot.Convertcurrencyms.repo.ConverCurrencyRepository;
import com.ms.boot.Convertcurrencyms.model.ConvertCurrency;





@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
@EnableCircuitBreaker
@EnableHystrix
@EnableHystrixDashboard
@EnableFeignClients
public class Application {

	
	@Autowired
	private ConverCurrencyRepository repo;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner inspect(ApplicationContext ctx) {
		return (r) -> {
			int beanCount = ctx.getBeanDefinitionCount();
			String[] beans = ctx.getBeanDefinitionNames();
			System.out.println("Bean Count = " + beanCount);
			for (int i = 0; i < beanCount; i++) {
				// System.out.println(beans[i]);
			}

			List<ConvertCurrency> convertcurrencies = new ArrayList<ConvertCurrency>();
			repo.save(new ConvertCurrency(91,73.2,1000));
			repo.save(new ConvertCurrency(39, 74.3,1000));
			repo.save(new ConvertCurrency(61, 74.3,1000));
			repo.save(new ConvertCurrency(81,74.3,1000));
			repo.save(new ConvertCurrency( 1,74.3,1000));
			
			/*
			 * products.stream().map((p) -> (repo.save(p))
			 * 
			 * 
			 * 
			 * );
			 */

			System.out.println("Saved " + convertcurrencies.size());

		};
   }
	
}
