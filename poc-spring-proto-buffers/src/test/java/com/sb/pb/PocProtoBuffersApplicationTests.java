package com.sb.pb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import com.sb.pb.dto.ProductDto;
import com.sb.pb.dto.ProductTypeDto;
import com.sb.pb.proto.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootTest
class PocProtoBuffersApplicationTests {

	@Test
	void createObject() {
		Product tv = Product.newBuilder()
				.setName("TV")
				.setDescription("TV 42 Sony")
				.setAmount(10)
				.setActive(true)
				.setProductType(ProductType.ELECTRONICS)
				.build();

		System.out.println("Info product: "+tv.toString());
	}

	@Test
	void shouldReturnTrueInTheComparison() {
		Product tv1 = createBaseProduct();

		Product tv2 = Product.newBuilder()
				.setName("TV")
				.setDescription("TV 42 Sony")
				.setAmount(10)
				.setActive(true)
				.setProductType(ProductType.ELECTRONICS)
				.build();

		Assertions.assertTrue(tv1.equals(tv2));
	}

	@Test
	void shouldReturnFalseInTheComparison() {
		Product tv1 = createBaseProduct();

		Product tv2 = Product.newBuilder()
				.setName("TV")
				.setDescription("TV 42 Sony")
				.setAmount(10)
				.setActive(false)
				.setProductType(ProductType.ELECTRONICS)
				.build();

		Assertions.assertFalse(tv1.equals(tv2));
	}

	@Test
	void shouldSerializeAndDeserialize() throws IOException {
		Product product = createBaseProduct();

		Path path = Paths.get("src/test/resources/product.ser");
		Files.write(path, product.toByteArray());

		byte[] bytes = Files.readAllBytes(path);
		Product newProduct = Product.parseFrom(bytes);

		Assertions.assertTrue(product.equals(newProduct));
	}

	@Test
	void shouldProtoSerializeAndDeserializeFaster() {
		//json
		ProductDto baseProductDto = createBaseProductDto();
		ObjectMapper objectMapper = new ObjectMapper();

		Runnable runnableJson = () -> {

			try {
				byte[] bytes = objectMapper.writeValueAsBytes(baseProductDto);
				ProductDto newProductDto = objectMapper.readValue(bytes, ProductDto.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		};

		Long effortJson = performTest(runnableJson);

		//proto
		Product product = createBaseProduct();
		Runnable runnableProto=() -> {

			try {
				byte[] bytes = product.toByteArray();
				Product newProduct= Product.parseFrom(bytes);
			} catch (InvalidProtocolBufferException e) {
				e.printStackTrace();
			}
		};

		Long effortProto = performTest(runnableProto);

		System.out.println("Json: "+effortJson);
		System.out.println("Proto: "+effortProto);
		Assertions.assertTrue(effortProto < effortJson);

	}

	@Test
	void shouldSerializeAndDeserializeObjectComplex() throws InvalidProtocolBufferException {
		final SalesOrder salesOrder = createSalesOrder();
		byte[] bytes = salesOrder.toByteArray();
		final SalesOrder newSalesOrder = SalesOrder.parseFrom(bytes);

		Assertions.assertTrue(salesOrder.equals(newSalesOrder));
	}

	private Long performTest(final Runnable runnable){
		long begin = System.currentTimeMillis();

		for (int i = 0; i < 5_000_000; i++){
			runnable.run();
		}

		long end = System.currentTimeMillis();

		return end-begin;
	}

	private Product createBaseProduct(){
		return Product.newBuilder()
				.setName("TV")
				.setDescription("TV 42 Sony")
				.setAmount(10)
				.setActive(true)
				.setProductType(ProductType.ELECTRONICS)
				.build();
	}

	private ProductDto createBaseProductDto(){
		ProductDto productDto = new ProductDto();
		productDto.setName("TV");
		productDto.setDescription("TV 42 Sony");
		productDto.setAmount(10);
		productDto.setActive(true);
		productDto.setProductType(ProductTypeDto.ELECTRONICS);

		return productDto;
	}

	private Seller createSeller(){
		return Seller.newBuilder()
				.setName("Polo")
				.build();
	}

	private OrderedItem createOrderedItem(final int amount){
		return OrderedItem.newBuilder()
				.setAmount(amount)
				.setIdSalesOrder(1)
				.build();
	}

	private SalesOrder createSalesOrder(){
		return SalesOrder.newBuilder()
				.setNumOrder(1)
				.setSeller(createSeller())
				.addOrderedItem(createOrderedItem(10))
				.addOrderedItem(createOrderedItem(15))
				.build();
	}

}
