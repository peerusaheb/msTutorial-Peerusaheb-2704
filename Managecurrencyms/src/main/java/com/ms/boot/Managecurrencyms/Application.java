package com.ms.boot.Managecurrencyms;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.ms.boot.Managecurrencyms.currency.ManageCurrency;
import com.ms.boot.Managecurrencyms.currency.ManageCurrencyJPARepository;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class Application {
	
	@Autowired
	ManageCurrencyJPARepository repo;

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

			List<ManageCurrency> managecurrencies = new ArrayList<ManageCurrency>();
			repo.save(new ManageCurrency(91,73.2));
			repo.save(new ManageCurrency(39, 74.3));
			repo.save(new ManageCurrency(61, 74.3));
			repo.save(new ManageCurrency(81,74.3));
			repo.save(new ManageCurrency( 1,74.3));
			
			/*
			 * products.stream().map((p) -> (repo.save(p))
			 * 
			 * 
			 * 
			 * );
			 */

			System.out.println("Saved " + managecurrencies.size());

		};
	}
 
}
