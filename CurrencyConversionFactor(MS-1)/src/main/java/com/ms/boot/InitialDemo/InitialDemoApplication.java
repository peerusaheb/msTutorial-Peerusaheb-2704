package com.ms.boot.InitialDemo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.ms.boot.InitialDemo.currency.Currency;
import com.ms.boot.InitialDemo.currency.CurrencyPARepository;

@SpringBootApplication
public class InitialDemoApplication {

	@Autowired
	CurrencyPARepository repo;

	public static void main(String[] args) {
		SpringApplication.run(InitialDemoApplication.class, args);
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

			List<Currency> products = new ArrayList<Currency>();
			//repo.save(new Currency(91,73.2));
			//repo.save(new Currency(39, 74.3));
			//repo.save(new Currency(61, 74.3));
			//repo.save(new Currency(81,74.3));
			//repo.save(new Currency( 1,74.3));
			
			/*
			 * products.stream().map((p) -> (repo.save(p))
			 * 
			 * 
			 * 
			 * );
			 */

			System.out.println("Saved " + products.size());

		};
	}

}
