package com.example.demo.web.api01;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Api01ServiceTest {
	@Autowired
	private Api01Service target;

	@Test
	public void test() {
		try {
			target.process(new Param());
		} catch (Exception e) {
			e.printStackTrace();
			fail("失敗");
		}
	}

}
