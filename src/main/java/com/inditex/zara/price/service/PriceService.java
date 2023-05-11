package com.inditex.zara.price.service;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import com.inditex.zara.price.database.entity.Price;
import com.inditex.zara.price.database.repository.PriceRepository;
import com.inditex.zara.price.model.PriceData;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PriceService {
	
	private final PriceRepository priceRepository;
	
	public PriceData getPrice(LocalDateTime date, Long productId, Long brandId) {
    	final Price price = retreivePriceFromRepository(date, productId, brandId);
		return mapPriceData(price);
    }

	private Price retreivePriceFromRepository(LocalDateTime date, Long productId, Long brandId) {
		return priceRepository.findFirstByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
    			brandId, 
    			productId, 
    			date, 
    			date);
	}

	private PriceData mapPriceData(final Price price) {
		return PriceData.builder()
				.productId(price.getProductId())
				.brandId(price.getBrand().getId())
				.price(price.getPrice())
				.currency(price.getCurrency())
				.priceList(price.getPriceList())
				.startDate(price.getStartDate())
				.endDate(price.getEndDate())
				.build();
	}
}
