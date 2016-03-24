package com.prages.base;

import org.elasticsearch.test.ESIntegTestCase;

/**
 * ES JAVA 클라이언트 테스트를 위한 베이스 클래스. 각 유닛 테스트의 독립성을 위해 클러스터를 공유하지 않고 실행
 *
 * @author lks21c
 */
@ESIntegTestCase.ClusterScope(scope = ESIntegTestCase.Scope.TEST)
public class AbstractBaseClientTest extends ESIntegTestCase {
	protected static final String INDEX_NAME = "product";
	protected static final String INDEX_TYPE_NAME = "info";
}
