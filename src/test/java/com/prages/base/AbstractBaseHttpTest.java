package com.prages.base;

import java.net.URL;

import org.junit.Before;

import com.prages.common.env.PragEsConstants;
import com.prages.common.util.ResourceFileReadUtil;
import com.prages.common.util.SimpleHttpUtil;

/**
 * Created by hydra01 on 16. 2. 1.
 */
public class AbstractBaseHttpTest {
	private static final String BASE_URL = "http://" + PragEsConstants.ES_HOST + ":" + PragEsConstants.ES_HTTP_PORT;
	protected SimpleHttpUtil simpleHttpUtil;
	protected ResourceFileReadUtil resourceFileReadUtil = new ResourceFileReadUtil();

	@Before
	public void setUp() throws Exception {
		URL url = new URL(AbstractBaseHttpTest.BASE_URL);
		this.simpleHttpUtil = new SimpleHttpUtil(url);
	}
}
