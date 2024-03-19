package com.minsait;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootTest
@EnableFeignClients
class MicroserviceShopApplicationTests {

	@Test
	public void testMainMethod() {
		MicroserviceShopApplication.main(new String[]{});
	}

}
