package com.melon.helloes.util;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;

public class MJsonUtil {
	public static List<String> getJsonArrayAsList(JsonArray jsonArray) {
		List<String> result = new ArrayList<String>();
		for ( int i = 0; i < jsonArray.size(); i++ ) {
			result.add(jsonArray.get(i).getAsString());
		}
		
		return result;
	}
}
