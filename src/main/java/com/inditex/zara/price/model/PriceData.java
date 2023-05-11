package com.inditex.zara.price.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceData {

	private Long productId;
	private Long brandId;
	private BigDecimal price;
	private String currency;
	private Integer priceList;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
}
