package com.inditex.zara.price.controller;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.inditex.zara.price.controller.PriceController;
import com.inditex.zara.price.model.PriceData;
import com.inditex.zara.price.service.PriceService;

@RequiredArgsConstructor
@RestController
public class PriceController{
	
    private final PriceService priceService;
    
    @GetMapping(value="price")
    @ResponseBody
    public PriceData getProductPrice(
    		@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) LocalDateTime date, 
    		@RequestParam Long productId, 
    		@RequestParam Long brandId) {
    	
        return priceService.getPrice(date, productId, brandId);
    }
}
