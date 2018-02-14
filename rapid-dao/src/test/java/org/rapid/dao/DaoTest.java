package org.rapid.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.rapid.core.Rapid;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoTestConfig.class})
public class DaoTest {
	
	@Resource
	protected Rapid rapid;

	@Test
	public void test() {
	}
}
