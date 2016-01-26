package com.melon.helloes.util;

import java.util.HashMap;
import java.util.Map;

import com.melon.helloes.domain.Synonym;

public class SynonymDicUtil {
	private static Map<String, Synonym> synonymDic = new HashMap<String, Synonym>();

	static {
		// test용 동의어 등록함.
		Synonym synonym1 = new Synonym("아이유", "iu");
		Synonym synonym2 = new Synonym("ikon", "아이콘");
		Synonym synonym3 = new Synonym("bigbang", "빅뱅");

		synonymDic.put(synonym1.getSynonym(), synonym1);
		synonymDic.put(synonym2.getSynonym(), synonym2);
		synonymDic.put(synonym3.getSynonym(), synonym3);
	}

	public static boolean hasSynonym(String term) {
		if (synonymDic.containsKey(term.toLowerCase())) {
			return true;
		}

		return false;
	}

	public static String getTerm(String term) {
		if (hasSynonym(term)) {
			return synonymDic.get(term.toLowerCase()).getTerm();
		}

		return "";
	}

}
