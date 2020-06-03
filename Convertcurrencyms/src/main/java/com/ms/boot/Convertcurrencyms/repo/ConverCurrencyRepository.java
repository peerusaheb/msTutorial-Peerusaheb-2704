package com.ms.boot.Convertcurrencyms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.ms.boot.Convertcurrencyms.model.ConvertCurrency;


@Component
public interface ConverCurrencyRepository extends JpaRepository<ConvertCurrency, Integer> {
	
	@Query("select b from convertcurrency b where b.id.countryCode =:countryCode ")
	public ConvertCurrency findByCountryCode(@Param("countryCode") Integer countryCode);

}
