package com.sb.pb;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import com.sb.pb.dto.ProductDto;
import com.sb.pb.factory.ProductFactory;
import com.sb.pb.factory.SalesFactory;
import com.sb.pb.proto.Product;
import com.sb.pb.proto.ProductType;
import com.sb.pb.proto.SalesOrder;
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
		final Product tv = Product.newBuilder()
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
		final Product tv1 = ProductFactory.createProductProto();
		final Product tv2 = ProductFactory.createProductProto();

		Assertions.assertTrue(tv1.equals(tv2));
	}

	@Test
	void shouldReturnFalseInTheComparison() {
		final Product tv1 = ProductFactory.createProductProto();
		final Product tv2 = Product.newBuilder()
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
		final Product product = ProductFactory.createProductProto();

		final Path path = Paths.get("src/test/resources/product.ser");
		Files.write(path, product.toByteArray());

		final byte[] bytes = Files.readAllBytes(path);
		Files.delete(path);
		final Product newProduct = Product.parseFrom(bytes);

		Assertions.assertTrue(product.equals(newProduct));
	}

	@Test
	void shouldProtoSerializeAndDeserializeFaster() {
		//json
		final ProductDto baseProductDto = ProductFactory.createBaseProductDTO();
		final ObjectMapper objectMapper = new ObjectMapper();

		Runnable runnableJson = () -> {
			try {
				byte[] bytes = objectMapper.writeValueAsBytes(baseProductDto);
				ProductDto newProductDto = objectMapper.readValue(bytes, ProductDto.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		};

		final Long effortJson = performTest(runnableJson);

		//proto
		final Product product = ProductFactory.createProductProto();
		Runnable runnableProto=() -> {
			try {
				byte[] bytes = product.toByteArray();
				Product newProduct= Product.parseFrom(bytes);
			} catch (InvalidProtocolBufferException e) {
				e.printStackTrace();
			}
		};

		final Long effortProto = performTest(runnableProto);

		System.out.println("Json: "+effortJson);
		System.out.println("Proto: "+effortProto);
		Assertions.assertTrue(effortProto < effortJson);

	}

	@Test
	void shouldSerializeAndDeserializeObjectComplex() throws InvalidProtocolBufferException {
		final SalesOrder salesOrder = SalesFactory.createSalesOrder();
		byte[] bytes = salesOrder.toByteArray();
		final SalesOrder newSalesOrder = SalesOrder.parseFrom(bytes);

		Assertions.assertTrue(salesOrder.equals(newSalesOrder));
	}

	private Long performTest(final Runnable runnable){
		final long begin = System.currentTimeMillis();

		for (int i = 0; i < 5_000_000; i++){
			runnable.run();
		}

		final long end = System.currentTimeMillis();

		return end-begin;
	}
}
