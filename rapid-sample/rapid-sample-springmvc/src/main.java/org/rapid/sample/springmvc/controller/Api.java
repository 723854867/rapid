package org.rapid.sample.springmvc.controller;

import javax.validation.Valid;

import org.rapid.sample.soa.api.SoaApi;
import org.rapid.sample.springmvc.bean.HelloParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

@Controller
public class Api {
	
	@Reference
	private SoaApi soaApi;
	
	@ResponseBody
	@RequestMapping("hello")
	public Object recharge(@RequestBody @Valid HelloParam param) {
		return soaApi.say();
	}
}
