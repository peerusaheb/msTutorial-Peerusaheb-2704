package com.ms.boot.Managecurrencyms.currency;

import javax.transaction.Transactional;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.data.jpa.repository.JpaRepository;

@Component
public interface ManageCurrencyJPARepository extends JpaRepository<ManageCurrency, Integer> {

	@Query("select b from managecurrency b where b.id.countryCode =:countryCode ")
	public ManageCurrency findByCountryCode(@Param("countryCode") Integer countryCode);
}
