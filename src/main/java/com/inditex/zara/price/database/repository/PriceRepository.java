package com.inditex.zara.price.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.inditex.zara.price.database.entity.Price;
import java.time.LocalDateTime;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
	
    Price findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
            Long brandId, 
            Long productId, 
            LocalDateTime startDate, 
            LocalDateTime endDate);
}