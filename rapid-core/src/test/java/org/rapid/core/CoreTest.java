package org.rapid.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.rapid.core.bean.model.code.Code;
import org.rapid.core.bean.model.option.Option;
import org.rapid.core.bean.model.option.StrOption;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CoreTestConfig.class})
public class CoreTest {

	@Test
	public void test() {
		StrOption option = Option.option(Code.FORBID.key());
		System.out.println(option.getDefaultValue());
	}
}
