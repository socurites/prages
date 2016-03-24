package com.prages.util;

import java.net.MalformedURLException;

import org.junit.Test;

import com.prages.common.util.ResourceFileReadUtil;

public class ResourceFileUtilTest {

	@Test
	public void testGetFileContent() throws MalformedURLException {
		String content = ResourceFileReadUtil.getFileContent("korean.txt");
        System.out.println("content = " + content);
    }

}
