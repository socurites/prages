package com.prages.base;

import com.prages.util.ResourceFileReadUtil;
import com.prages.util.SimpleHttpUtil;

import java.net.URL;

import org.junit.Before;

/**
 * Created by hydra01 on 16. 2. 1.
 */
public class AbstractBaseHttpTest {
	private static final String BASE_URL = "http://socurites.com:9200";
	protected SimpleHttpUtil simpleHttpUtil;
    protected ResourceFileReadUtil resourceFileReadUtil = new ResourceFileReadUtil();

    @Before
    public void setUp() throws Exception {
    	URL url = new URL(AbstractBaseHttpTest.BASE_URL);
        this.simpleHttpUtil = new SimpleHttpUtil(url);
    }
}
