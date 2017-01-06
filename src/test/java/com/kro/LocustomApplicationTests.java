package com.kro;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
public class LocustomApplicationTests {

	@Autowired
	WebApplicationContext wac;

	@Test
	public void contextLoads() {
		Assert.assertNotNull(wac);
	}

}
