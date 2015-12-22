<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<link href="${pageContext.servletContext.contextPath}/resources/helloes/css/helloes-dev.css" rel="stylesheet">

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			설치하기 <small>A to Z</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="fa fa-dashboard"></i> Developer Guide</a></li>
			<li class="active">설치하기</li>
		</ol>
	</section>

	<div class="content body">
		<!-- Main content -->
		<section class="content">
			<h2 class="page-header">설치하기 전에...</h2>
			<p class="lead">
				이 사이트의 예제에서는 아래의 elasticsearch 관련 SW를 설치한다.
			</p>
			<ul>
				<li>
					<b>elasticsearch 1.7.2.</b> 현재 최신 버전의 elasticsearch
				</li>
				<li>
					<b>elasticsearch-head 플러그인.</b> es 인덱스 관리
				</li>
				<li>
					<b>marvel 플러그인.</b> 색인 및 검색 테스트를 위한 Sense 모듈 활용
				</li>
			</ul>
			<p class="lead">
				한글 형태소 분석에는 은전한닢을 분석기를 사용한다.
			</p>
			<ul>
				<li>
					<b>mecab-ko.</b> 한국어의 특성에 맞게 기능이 추가된 MeCab의 fork 프로젝트(mecab-0.996-ko-0.9.2)
				</li>
				<li>
					<b>mecab-ko-dic.</b> 한국어 형태소 분석을 위한 사전(mecab-ko-dic-2.0.1-20150920)
				</li>
				<li>
					<b>mecab-java.</b> mecab-ko 형태소 분석기 엔진에 대한 자바 클라이언트 라이브러리(mecab-java-0.996)
				</li>
				<li>
					<b>mecab-loader.</b> mecab-ko 시스템 라이브러리 로더(0.17.0-SNAPSHOT)
				</li>
				<li>
					<b>mecab-ko-lucene-analyzer.</b> lucene용 한글 형태소 분석기(0.17.0-SNAPSHOT)
				</li>
				<li>
					<b>elasticsearch-analysis-mecab-ko.</b> elasticsearch용 한글 형태소 분석기 플러그인(0.17.0-SNAPSHOT)
				</li>
		</section>
		
		<section class="content">
			<h2 class="page-header">elasticsearch 설치하기</h2>
			<p class="lead">
				elasticsearch와 관련 모듈을 설치한다.
			</p>
			<pre class="hierarchy bring-up"><code class="language-bash" data-lang="bash">$ wget https://download.elastic.co/elasticsearch/elasticsearch/elasticsearch-1.7.2.tar.gz

$ tar xvfz elasticsearch-1.7.2.tar.gz
$ ln -s elasticsearch-1.7.2 elasticsearch
$ cd elasticsearch

$ bin/elasticsearch

$ curl -XGET 'http://localhost:9200'
{
  "status" : 200,
  "name" : "Infectia",
  "cluster_name" : "elasticsearch",
  "version" : {
    "number" : "1.7.2",
    "build_hash" : "e43676b1385b8125d647f593f7202acbd816e8ec",
    "build_timestamp" : "2015-09-14T09:49:53Z",
    "build_snapshot" : false,
    "lucene_version" : "4.10.4"
  },
  "tagline" : "You Know, for Search"
}</code></pre>

			<pre class="hierarchy bring-up"><code class="language-bash" data-lang="bash">$ bin/plugin -install mobz/elasticsearch-head
$ bin/plugin -i elasticsearch/marvel/latest</code></pre>

			<p>
				elasticsearch-head는 http://localhost:9200/_plugin/head/, marvel의 Sense는 http://localhost:9200/_plugin/marvel/sense/index.html로 접속한다.
			</p>
		</section>
		
		<section class="content">
			<h2 class="page-header">은전한닢 설치하기</h2>
			<p class="lead">
				mecab와 은전한닢 관련 모듈을 설치한다.
			</p>
			
			<h3>mecab-ko 설치하기</h3>
			<pre class="hierarchy bring-up"><code class="language-bash" data-lang="bash">$ wget https://bitbucket.org/eunjeon/mecab-ko/downloads/mecab-0.996-ko-0.9.2.tar.gz
$ cd mecab-0.996-ko-0.9.2
$ ./configure
$ make
$ sudo make install</code></pre>
			
			<h3>mecab-ko-dic 설치하기</h3>
			<pre class="hierarchy bring-up"><code class="language-bash" data-lang="bash">$ wget https://bitbucket.org/eunjeon/mecab-ko-dic/downloads/mecab-ko-dic-2.0.1-20150920.tar.gz
$ cd mecab-ko-dic-2.0.1-20150920
$ ./configure
$ make
$ sudo make install</code></pre>

			<p>
				사전까지 설치가 끝나면, 한글 형태소 분석기를 아래와 같이 테스트할 수 있다.
			</p>
			<pre class="hierarchy bring-up"><code class="language-bash" data-lang="bash">$ mecab -d /usr/local/lib/mecab/dic/mecab-ko-dic
아이유를 사랑했다
아이유	NNG,*,F,아이유,*,*,*,*
를	JKO,*,T,를,*,*,*,*
사랑	NNG,*,T,사랑,*,*,*,*
했	XSV+EP,*,T,했,Inflect,XSV,EP,하/XSV/*+았/EP/*
다	EC,*,F,다,*,*,*,*
EOS</code></pre>
			
			<h3>mecab-java 설치하기</h3>
			<pre class="hierarchy bring-up"><code class="language-bash" data-lang="bash">$ wget https://bitbucket.org/eunjeon/mecab-java/downloads/mecab-java-0.996.tar.gz
$ cd mecab-java-0.996
$ vi Makefile
...
#INCLUDE=/usr/lib/jvm/java-6-openjdk/include
INCLUDE=/usr/lib/jvm/jdk1.7.0_75/include

PACKAGE=org/chasen/mecab

LIBS=`mecab-config --libs`
INC=`mecab-config --cflags` -I$(INCLUDE) -I$(INCLUDE)/linux

all:
  $(CXX) -O1 -c -fpic $(TARGET)_wrap.cxx  $(INC)
  $(CXX) -shared  $(TARGET)_wrap.o -o lib$(TARGET).so $(LIBS)
  $(JAVAC) $(PACKAGE)/*.java
  #$(JAVAC) test.java
  $(JAR) cfv $(TARGET).jar $(PACKAGE)/*.class
...</code></pre>

			<p>
				Makefile을 열어서, Java 경로를 시스템 경로에 맞게 변경한다. 컴파일 옵션을 O3에서 O1으로 변경한다. 테스트케이스 실행은 주석처리한다.
			</p>
			
			<pre class="hierarchy bring-up"><code class="language-bash" data-lang="bash">$ make
$ sudo cp libMeCab.so /usr/local/lib</code></pre>
			
			<h3>elasticsearch 은전한닢 플러그인 설치하기</h3>
			<pre class="hierarchy bring-up"><code class="language-bash" data-lang="bash">$ git clone https://bitbucket.org/eunjeon/mecab-ko-lucene-analyzer.git
$ mvn clean test</code></pre>

			<p>
				메인븐에서 한글 인코딩 깨지지 않도록 컴파일 옵션을 변경한다.
			</p>
			<pre class="hierarchy bring-up"><code class="language-bash" data-lang="bash">$ cd mecab-ko-lucene-analyzer
$ vi pom.xml
...
	&lt;plugin&gt;
    	&lt;groupId&gt;org.apache.maven.plugins&lt;/groupId&gt;
    	&lt;artifactId&gt;maven-compiler-plugin&lt;/artifactId&gt;
    	&lt;configuration&gt;
    		&lt;encoding&gt;UTF-8&lt;/encoding&gt;              
    	&lt;/configuration&gt;
	&lt;/plugin&gt;
...

$ mvn clean install</code></pre>

			<p>
				Jar 패키지가 생성되면 elasticsearch 플러그인 디렉토리로 복사한다.
			</p>
			<pre class="hierarchy bring-up"><code class="language-bash" data-lang="bash">mkdir ${$ES_HOME}/plugins/mecab
cp MeCab.jar ${$ES_HOME}/plugins/mecab/
cp mecab-ko-mecab-loader-0.17.0-SNAPSHOT.jar ${$ES_HOME}/plugins/mecab/
cp mecab-ko-lucene-analyzer-0.17.0-SNAPSHOT.jar ${$ES_HOME}/plugins/mecab/
cp  elasticsearch-analysis-mecab-ko-0.17.0-SNAPSHOT.jar ${$ES_HOME}/plugins/mecab/</code></pre>

			<p>
				LD_LIBRARY_PATH를 설정한다.
			</p>
			<pre class="hierarchy bring-up"><code class="language-bash" data-lang="bash">export LD_LIBRARY_PATH=/usr/local/lib</code></pre>
			
			<p>
				elasticsearh를 재기동 후, 아래의 스크립트를 실행하여 테스트한다.
			</p>
			<pre class="hierarchy bring-up"><code class="language-bash" data-lang="bash">#!/bin/bash
ES='http://localhost:9200'
ESIDX='eunjeon'

curl -XDELETE $ES/$ESIDX

curl -XPUT $ES/$ESIDX/ -d '{
  "settings" : {
    "index":{
      "analysis":{
        "analyzer":{
          "korean":{
            "type":"custom",
            "tokenizer":"mecab_ko_standard_tokenizer"
          }
        }
      }
    }
  }
}'

curl -XGET $ES/$ESIDX/_analyze?analyzer=korean\&pretty=true -d '나는 아이유를 사랑했다'</code></pre>
		</section>
		
		
		<!-- /.content -->
	</div>
</div>
<!-- /.content-wrapper -->