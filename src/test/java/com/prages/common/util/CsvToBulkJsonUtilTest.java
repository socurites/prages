package com.prages.common.util;

import org.junit.Test;

/**
 * Created by lks21c on 16. 3. 22.
 */
public class CsvToBulkJsonUtilTest {

	private CsvToBulkJsonUtil csvToBulkJsonUtil = new CsvToBulkJsonUtil();

	@Test
	public void testRun() throws Exception {
		csvToBulkJsonUtil.run("prages/publicdata/", "EUC-KR", "2015-10-01");
	}
}
