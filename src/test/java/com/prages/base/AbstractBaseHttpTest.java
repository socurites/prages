package com.prages.base;

import com.prages.util.ResourceFileReadUtil;
import com.prages.util.SimpleHttpUtil;
import org.junit.Before;

/**
 * Created by hydra01 on 16. 2. 1.
 */
public class AbstractBaseHttpTest {
    protected SimpleHttpUtil simpleHttpUtil;
    protected ResourceFileReadUtil resourceFileReadUtil = new ResourceFileReadUtil();

    @Before
    public void setUp() throws Exception {
        simpleHttpUtil = new SimpleHttpUtil();
    }
}
