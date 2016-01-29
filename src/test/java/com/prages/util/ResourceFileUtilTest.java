package com.prages.util;

import java.net.MalformedURLException;

import org.junit.Test;

public class ResourceFileUtilTest {

    ResourceFileReadUtil resourceFileReadUtil = new ResourceFileReadUtil();

	@Test
	public void testGetFileContent() throws MalformedURLException {
        String content = resourceFileReadUtil.getFileContent("korean.txt");
        System.out.println("content = " + content);
    }

}
