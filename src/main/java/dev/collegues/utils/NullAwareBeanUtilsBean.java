package dev.collegues.utils;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.context.annotation.Bean;

public class NullAwareBeanUtilsBean extends BeanUtilsBean {

	@Override
	public void copyProperty(Object dest, String name, Object value)
			throws IllegalAccessException, InvocationTargetException {
		if (value == null) {
			return;
		}
		super.copyProperty(dest, name, value);
	}

	@Bean
	public NullAwareBeanUtilsBean nullAwareBeanUtilsBean() {
		return new NullAwareBeanUtilsBean();
	}
}
