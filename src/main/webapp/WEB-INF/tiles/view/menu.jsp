<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
 
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				<img src="${pageContext.servletContext.contextPath}/resources/helloes/image/socurites.jpg" class="img-circle" alt="User Image">
			</div>
			<div class="pull-left info">
				<p>송준이 - socurites</p>
				<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
			</div>
		</div>
		<!-- search form -->
		<form action="#" method="get" class="sidebar-form">
			<div class="input-group">
				<input type="text" name="q" class="form-control"
					placeholder="Search..."> <span class="input-group-btn">
					<button type="submit" name="search" id="search-btn"
						class="btn btn-flat">
						<i class="fa fa-search"></i>
					</button>
				</span>
			</div>
		</form>
		<!-- /.search form -->
		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu">
			<li class="header">Table of Contents</li>
			
			<li class="treeview">
				<a href="#">
					<i class="fa fa-group"></i> <span>Developer Guide</span> <i class="fa fa-angle-left pull-right"></i>
				</a>
				<ul class="treeview-menu">
					<li class="active">
						<a href="${pageContext.servletContext.contextPath}/developer/install">
							<i class="fa fa-circle-o"></i> 설치하기
						</a>
					</li>
				</ul>
			</li>
			
			<li class="treeview">
				<a href="#">
					<i class="fa fa-reorder"></i> <span>검색엔진이란?</span> <i class="fa fa-angle-left pull-right"></i>
				</a>
				<ul class="treeview-menu">
					<li class="">
						<a href="${pageContext.servletContext.contextPath}/overview/intro">
							<i class="fa fa-circle-o"></i> 색인과 검색
						</a>
					</li>
					<li class="active">
						<a href="${pageContext.servletContext.contextPath}/overview/search">
							<i class="fa fa-circle-o"></i> 검색하기
						</a>
					</li>
				</ul>
			</li>
			
			<li class="treeview">
				<a href="#">
					<i class="fa fa-reorder"></i> <span>형태소 분석이란?</span> <i class="fa fa-angle-left pull-right"></i>
				</a>
				<ul class="treeview-menu">
					<li class="">
						<a href="${pageContext.servletContext.contextPath}/morpheme/intro">
							<i class="fa fa-circle-o"></i> 한글 형태소 분석
						</a>
					</li>
					<li class="">
						<a href="${pageContext.servletContext.contextPath}/morpheme/synonym">
							<i class="fa fa-circle-o"></i> 동의어 처리
						</a>
					</li>
				</ul>
			</li>
			
			<li class="treeview">
				<a href="#">
					<i class="fa fa-reorder"></i> <span>응용하기</span> <i class="fa fa-angle-left pull-right"></i>
				</a>
				<ul class="treeview-menu">
					<li class="">
						<a href="${pageContext.servletContext.contextPath}/apply/power_network">
							<i class="fa fa-circle-o"></i> 파워 네트워크
						</a>
					</li>
				</ul>
			</li>
			
			<li class="treeview">
				<a href="#">
					<i class="fa fa-gear"></i> <span>도구통</span> <i class="fa fa-angle-left pull-right"></i>
				</a>
				<ul class="treeview-menu">
					<li class="">
						<!-- a href="${pageContext.servletContext.contextPath}/tool/synonym" -->
						<a href="javascript:alert('not yet')">
							<i class="fa fa-circle-o"></i> 동의어 관리
						</a>
					</li>
					<li class="">
						<!-- a href="${pageContext.servletContext.contextPath}/tool/dict" -->
						<a href="javascript:alert('not yet')">
							<i class="fa fa-circle-o"></i> 커스텀 사전
						</a>
					</li>
				</ul>
			</li>
			
			<li>
				<!-- a href="documentation/index.html" -->
				<a href="javascript:alert('Hello Melon Es')">
					<i class="fa fa-book"></i><span>About Melon HelloEs</span>
				</a>
			</li>
			<li class="header">관련 자료</li>
			<li><a href="https://www.elastic.co/guide/en/elasticsearch/guide/current/index.html"><i class="fa fa-search text-red"></i> <span>Elasticsearch Guide</span></a></li>
			<li><a href="http://eunjeon.blogspot.kr/"><i class="fa fa-external-link-square text-red"></i> <span>은전한닢</span></a></li>
			<li><a href="http://www.slideshare.net/JunyiSong1/elasticsearch-45936425"><i class="fa fa-slideshare text-red"></i> <span>Elasticsearch 적용 및 활용</span></a></li>
			<li><a href="http://www.slideshare.net/JunyiSong1/20151022-elasticsearch-sds"><i class="fa fa-slideshare text-red"></i> <span>Elasticsearch 발표자료</span></a></li>
		</ul>
	</section>
	<!-- /.sidebar -->
</aside>