package com.example.demo.unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void homeResponse() {
		String body = this.restTemplate.getForObject("/", String.class);
		assertThat(body).isEqualTo("Spring is here!");
	}

	@Test
	public void helloWorldResponse() {
		String body = this.restTemplate.getForObject("/hello", String.class);
		assertThat(body).isEqualTo("Hello World!");
	}

	@Test
	public void seeYaResponse() {
		String body = this.restTemplate.getForObject("/bye", String.class);
		assertThat(body).isEqualTo("See Ya!");
	}
}
