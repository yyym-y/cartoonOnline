package org.yyym.back;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.yyym.back.serve.DataService;

@SpringBootTest
class BackApplicationTests {
	@Autowired
	private DataService dataService;

	@Test
	void testDataGetYear() {
		System.out.println(dataService.getYearPlayData("2023"));
	}

}
