<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			검색엔진이란? <small>색인하고 검색하기</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="fa fa-dashboard"></i> 검색엔진아란?</a></li>
			<li class="active">색인과 검색</li>
		</ol>
	</section>

	<!-- Main content -->
	<section class="content">
		<div class="row">
			<div class="col-md-7">
				<div class="row">
					<div class="col-md-12">
						<div class="box box-default">
							<div class="box-header with-border">
								<h3 class="box-title">색인과 검색</h3>
								<div class="box-tools pull-right">
									<button class="btn btn-box-tool" data-widget="collapse">
										<i class="fa fa-minus"></i>
									</button>
									<button class="btn btn-box-tool" data-widget="remove">
										<i class="fa fa-times"></i>
									</button>
								</div>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<div class="row">
									<div class="col-md-12">
										<img
											src="${pageContext.servletContext.contextPath}/resources/helloes/image/slide/slie_overview_intro.png" class="helloes-slide-image" />
									</div>
									<!-- /.col -->
								</div>
								<!-- /.row -->
							</div>
							<!-- /.box-body -->
						</div>
					</div>
				</div>
		
				<div class="row">
					<div class="col-md-12">
						<div class="box box-info">
							<div class="box-header with-border">
								<h3 class="box-title">Ex) 색인하기</h3>
								<div class="box-tools pull-right">
									<button class="btn btn-box-tool" data-widget="collapse">
										<i class="fa fa-minus"></i>
									</button>
									<button class="btn btn-box-tool" data-widget="remove">
										<i class="fa fa-times"></i>
									</button>
								</div>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<div class="input-group">
									<input type="text" name="message" placeholder="http://www.melon.com/song/detail.htm?songId=7854179" class="form-control"> <span class="input-group-btn">
										<button type="button" class="btn btn-info btn-flat" onclick="toggleCollapse('div-collpase-index-document')">색인</button>
									</span>
								</div>
							</div>
							<!-- /.box-body -->
							<div class="box-footer clearfix">
								<a href="http://www.melon.com/song/detail.htm?songId=7854179" target="_blank" class="btn btn-sm btn-default btn-flat pull-left">곡 상세</a>
								<a href="javascript:toggleModal('index-schema-modal', 'show')" class="btn btn-sm btn-default btn-flat pull-left">인덱스 스키마</a>
								<a href="javascript:toggleCollapse('div-collpase-create-index')" class="btn btn-sm btn-default btn-flat pull-left">인덱스 생성</a>
								<a href="javascript:toggleCollapse('div-collpase-index-document')" class="btn btn-sm btn-default btn-flat pull-left">문서 색인</a>
							</div>
							<!-- /.box-footer -->
						</div>
						<!-- /.box -->
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-12">
						<div class="box box-warning">
							<div class="box-header with-border">
								<h3 class="box-title">Ex) 검색하기</h3>
								<div class="box-tools pull-right">
									<button class="btn btn-box-tool" data-widget="collapse">
										<i class="fa fa-minus"></i>
									</button>
									<button class="btn btn-box-tool" data-widget="remove">
										<i class="fa fa-times"></i>
									</button>
								</div>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<div class="input-group">
									<input type="text" id="search-term" name="search-term" placeholder="검색어를 입력하세요" class="form-control"> <span class="input-group-btn">
										<button type="button" class="btn btn-warning btn-flat" onclick="doSearch();">검색</button>
									</span>
								</div>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-12">
						<div class="box box-warning">
							<div class="box-header with-border">
								<h3 class="box-title">Ex) 검색 결과</h3>
								<div class="box-tools pull-right">
									<button class="btn btn-box-tool" data-widget="collapse">
										<i class="fa fa-minus"></i>
									</button>
									<button class="btn btn-box-tool" data-widget="remove">
										<i class="fa fa-times"></i>
									</button>
								</div>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<div id="div-search-result">
								---
								</div>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
				</div>
			</div>
				

		
			<div class="col-md-5">
				<div class="row">
					<div class="col-md-12">
						<div  id="div-collpase-create-index" class="box box-navy collapsed-box">
							<div class="box-header with-border">
								<h3 class="box-title">DSL) 인덱스 생성하기</h3>
								<div class="box-tools pull-right">
									<button class="btn btn-box-tool" data-widget="collapse">
										<i class="fa fa-plus"></i>
									</button>
									<button class="btn btn-box-tool" data-widget="remove">
										<i class="fa fa-times"></i>
									</button>
								</div>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<pre>
									<jsp:include page="../component/dsl/create_index.jsp"></jsp:include>
								</pre>
							</div>
							<!-- /.box-body -->
							<div class="box-footer clearfix">
							</div>
							<!-- /.box-footer -->
						</div>
						<!-- /.box -->
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-12">
						<div  id="div-collpase-index-document" class="box box-navy collapsed-box">
							<div class="box-header with-border">
								<h3 class="box-title">DSL) 문서 색인하기</h3>
								<div class="box-tools pull-right">
									<button class="btn btn-box-tool" data-widget="collapse">
										<i class="fa fa-plus"></i>
									</button>
									<button class="btn btn-box-tool" data-widget="remove">
										<i class="fa fa-times"></i>
									</button>
								</div>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<pre>
									<jsp:include page="../component/dsl/index_document.jsp"></jsp:include>
								</pre>
							</div>
							<!-- /.box-body -->
							<div class="box-footer clearfix">
							</div>
							<!-- /.box-footer -->
						</div>
						<!-- /.box -->
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-12">
						<div class="box box-danger collapsed-box">
							<div class="box-header with-border">
								<h3 class="box-title">DSL) 검색하기 for Dev</h3>
								<div class="box-tools pull-right">
									<button class="btn btn-box-tool" data-widget="collapse">
										<i class="fa fa-plus"></i>
									</button>
									<button class="btn btn-box-tool" data-widget="remove">
										<i class="fa fa-times"></i>
									</button>
								</div>
							</div>
							<!-- /.box-header -->
							<div class="box-body">
								<pre>
									<jsp:include page="../component/dsl/overview_intro.jsp"></jsp:include>
								</pre>
							</div>
							<!-- /.box-body -->
							<div class="box-footer clearfix">
							</div>
							<!-- /.box-footer -->
						</div>
						<!-- /.box -->
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->

<div id="index-schema-modal" class="modal">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">곡 상세 인덱스 스키마(song/detail)</h4>
			</div>
			<div class="modal-body">
				<div class="table-responsive">
					<table class="table no-margin">
						<thead>
							<tr>
								<th>Field</th>
								<th>Description</th>
								<th>data type</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>song_id</td>
								<td>곡 ID</td>
								<td>string</td>
							</tr>
							<tr>
								<td>title</td>
								<td>곡 타이틀</td>
								<td>string</td>
							</tr>
							<tr>
								<td>artist_id</td>
								<td>아티스트 ID</td>
								<td>string</td>
							</tr>
							<tr>
								<td>artist_name</td>
								<td>아티스트 명</td>
								<td>string</td>
							</tr>
							<tr>
								<td>act_type</td>
								<td>활동 유형</td>
								<td>string</td>
							</tr>
							<tr>
								<td>label_name</td>
								<td>소속사 명</td>
								<td>string</td>
							</tr>
							<tr>
								<td>albume_name</td>
								<td>앨범 명</td>
								<td>string</td>
							</tr>
							<tr>
								<td>issue_date</td>
								<td>발매일</td>
								<td>date</td>
							</tr>
							<tr>
								<td>album_image_path</td>
								<td>앨범 이미지 URL</td>
								<td>string</td>
							</tr>
							<tr>
								<td>artist_image_path</td>
								<td>앨범 이미지 URL</td>
								<td>string</td>
							</tr>
							<tr>
								<td>genres</td>
								<td>장르</td>
								<td>list</td>
							</tr>
							<tr>
								<td>lyricists</td>
								<td>작사가</td>
								<td>list</td>
							</tr>
							<tr>
								<td>composers</td>
								<td>작곡가</td>
								<td>list</td>
							</tr>
							<tr>
								<td>arrangers</td>
								<td>편곡자</td>
								<td>list</td>
							</tr>
							<tr>
								<td>lyric</td>
								<td>가사</td>
								<td>long string</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<script>
$(document).ready(function() {
});

var toggleModal = function(id, show) {
	$('#' + id).modal(show);
};

var toggleCollapse = function(id) {
	if ( $('#' + id).hasClass('collapsed-box') ) {
		$('#' + id).removeClass('collapsed-box');
		$('#' + id + " > .box-body").show();
	} else {
		$('#' + id).addClass('collapsed-box');
		$('#' + id + " > .box-body").hide();
	}
};

var doSearch = function() {
	$.ajax({
		type: "POST",
		url: "${pageContext.servletContext.contextPath}/overview//intro/search?term=" + encodeURIComponent($('#search-term').val()),
		success : function(data) {
			var hitsCount = data.hits.total;
			
			if ( hitsCount > 0 ) {
				var highlight = JSON.stringify(data.hits.hits[0].highlight).replace(/,/g, "<br>");
				$('#div-search-result').html(highlight);
			} else {
				$('#div-search-result').html("검색 결과가 없습니다");
			}
			
		},
		dataType : "json"
	});
};
</script>
