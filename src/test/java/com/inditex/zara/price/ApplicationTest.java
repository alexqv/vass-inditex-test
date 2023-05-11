package com.inditex.zara.price;

import lombok.Builder;
import lombok.Data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.inditex.zara.price.database.repository.PriceRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private PriceRepository priceRepository;
		
	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE_TIME;

	private final Long givenBrandId = 1L;
	private final Long givenProductId = 35455L;

	@Test
	@DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
	void shouldReturnProperPriceData_case1() throws Exception {
		// Given
		final LocalDateTime givenDate = LocalDateTime.of(2020, 6, 14, 10, 0);

		final PriceResponse expectedResponse = PriceResponse.builder()
				.productId("35455")
				.brandId("1")
				.price("35.50")
				.currency("EUR")
				.priceList("1")
				.startDate("2020-06-14T00:00:00")
				.endDate("2020-12-31T23:59:59")
				.build();

		// When
		final PriceResponse actualResponse = callGetEndpoint(givenDate);

		// Then
		assertEquals(expectedResponse, actualResponse);
	}

	private PriceResponse callGetEndpoint(final LocalDateTime givenDate) {
		final StringBuffer url = new StringBuffer()
			.append("http://localhost:")
			.append(port)
			.append("/price?date=")
			.append(givenDate.format(dateFormatter))
			.append("&productId=")
			.append(givenProductId)
			.append("&brandId=")
			.append(givenBrandId);
		
		return restTemplate.getForObject(url.toString(), PriceResponse.class);
	}
	
	private void assertEquals(PriceResponse expectedResponse, PriceResponse actualResponse) {
		Assertions.assertEquals(expectedResponse.getProductId(), actualResponse.getProductId());
		Assertions.assertEquals(expectedResponse.getBrandId(), actualResponse.getBrandId());
		Assertions.assertEquals(expectedResponse.getPrice(), actualResponse.getPrice());
		Assertions.assertEquals(expectedResponse.getCurrency(), actualResponse.getCurrency());
		Assertions.assertEquals(expectedResponse.getPriceList(), actualResponse.getPriceList());
		Assertions.assertEquals(expectedResponse.getStartDate(), actualResponse.getStartDate());
		Assertions.assertEquals(expectedResponse.getEndDate(), actualResponse.getEndDate());
	}

	@Test
	@DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
	void shouldReturnProperPriceData_case2() throws Exception {
		// Given
		final LocalDateTime givenDate = LocalDateTime.of(2020, 6, 14, 16, 0);

		final PriceResponse expectedResponse = PriceResponse.builder()
				.productId("35455")
				.brandId("1")
				.price("25.45")
				.currency("EUR")
				.priceList("2")
				.startDate("2020-06-14T15:00:00")
				.endDate("2020-06-14T18:30:00")
				.build();

		//When
		final PriceResponse actualResponse = callGetEndpoint(givenDate);

		// Then
		assertEquals(expectedResponse, actualResponse);
	}

	@Test
	@DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
	void shouldReturnProperPriceData_case3() throws Exception {
		// Given
		final LocalDateTime givenDate = LocalDateTime.of(2020, 6, 14, 21, 0);

		final PriceResponse expectedResponse = PriceResponse.builder()
				.productId("35455")
				.brandId("1")
				.price("35.50")
				.currency("EUR")
				.priceList("1")
				.startDate("2020-06-14T00:00:00")
				.endDate("2020-12-31T23:59:59")
				.build();

		//When
		final PriceResponse actualResponse = callGetEndpoint(givenDate);

		// Then
		assertEquals(expectedResponse, actualResponse);
	}

	@Test
	@DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)")
	void shouldReturnProperPriceData_case4() throws Exception {
		// Given
		final LocalDateTime givenDate = LocalDateTime.of(2020, 6, 15, 10, 0);

		final PriceResponse expectedResponse = PriceResponse.builder()
				.productId("35455")
				.brandId("1")
				.price("30.50")
				.currency("EUR")
				.priceList("3")
				.startDate("2020-06-15T00:00:00")
				.endDate("2020-06-15T11:00:00")
				.build();

		//When
		final PriceResponse actualResponse = callGetEndpoint(givenDate);

		// Then
		assertEquals(expectedResponse, actualResponse);
	}

	@Test
	@DisplayName("Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)")
	void shouldReturnProperPriceData_case5() throws Exception {
		// Given
		final LocalDateTime givenDate = LocalDateTime.of(2020, 6, 16, 21, 0);

		final PriceResponse expectedResponse = PriceResponse.builder()
				.productId("35455")
				.brandId("1")
				.price("38.95")
				.currency("EUR")
				.priceList("4")
				.startDate("2020-06-15T16:00:00")
				.endDate("2020-12-31T23:59:59")
				.build();

		//When
		final PriceResponse actualResponse = callGetEndpoint(givenDate);

		// Then
		assertEquals(expectedResponse, actualResponse);
	}
	
	@Data
	@Builder
	public static class PriceResponse {

		private String productId;
		private String brandId;
		private String price;
		private String currency;
		private String priceList;
		private String startDate;
		private String endDate;
	}
}