package com.liferay.arquillian.sample.service;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = SampleService.class)
public class SampleServiceImpl implements SampleService {

	@Override
	public long add(final int addend1, final int addend2) {
		return addend1 + addend2;
	}

}