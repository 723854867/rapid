package org.rapid.sample.soa.service;

import org.rapid.sample.soa.api.SoaApi;

import com.alibaba.dubbo.config.annotation.Service;

@Service
public class SoaService implements SoaApi {

	@Override
	public String say() {
		return "what are tou 弄啥嘞！";
	}
}
