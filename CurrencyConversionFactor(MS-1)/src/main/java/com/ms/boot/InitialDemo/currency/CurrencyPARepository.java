package com.ms.boot.InitialDemo.currency;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
@Transactional
public interface CurrencyPARepository extends JpaRepository<Currency, Integer> {

	@Query("select b from currency b where b.id.countryCode =:countryCode ")
	public Currency findByCountryCode(@Param("countryCode") Integer countryCode);
}
