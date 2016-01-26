package com.melon.helloes.util;

import java.util.Comparator;
import java.util.Map;

public class ValueComparator implements Comparator {
	private Map<String, Integer> base;

	public ValueComparator(Map<String, Integer> base) {
		this.base = base;
	}

	@Override
	public int compare(Object o1, Object o2) {
		if (base.get((String) o1) >= base.get((String) o2)) {
			return -1;
		} else {
			return 1;
		}
	}
}