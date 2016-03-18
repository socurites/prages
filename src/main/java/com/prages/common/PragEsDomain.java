package com.prages.common;

import java.lang.reflect.Field;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * 도메인 상위 클래스.
 * 도메인 유틸리티 메서드 구현.
 * 
 * @author socurites
 *
 */
public class PragEsDomain {
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return (new ReflectionToStringBuilder(this) {
	         protected boolean accept(Field f) {
	             return super.accept(f) && !f.getName().equals("password");
	         }
	     }).toString();
	}
}
