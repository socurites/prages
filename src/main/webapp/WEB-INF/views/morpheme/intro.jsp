<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<style>
   .tag-cloud-container {
     width: 600px;
     height: 300px;
     border: none;
     margin: 20px;
   }
 </style>
  
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			한글 형태소 분석 <small>단어 구분하기</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="fa fa-dashboard"></i> 형태소 분석이란?</a></li>
			<li class="active">한글 형태소 분석</li>
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
								<h3 class="box-title">한글 형태소 분석</h3>
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
											src="${pageContext.servletContext.contextPath}/resources/helloes/image/slide/slide_morpheme_intro.png" class="helloes-slide-image" />
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
								<h3 class="box-title">Ex) 형태소 분석하기</h3>
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
									<input id="test-line" type="text" name="message" placeholder="너는 내 취향저격 내 취향저격 말하지 않아도 느낌이 와" class="form-control"> <span class="input-group-btn">
										<button type="button" class="btn btn-info btn-flat" onclick="doAnalyze();">분석</button>
									</span>
								</div>
							</div>
							<!-- /.box-body -->
							<div class="box-footer clearfix">
								<a href="http://www.melon.com/song/detail.htm?songId=7854179" target="_blank" class="btn btn-sm btn-default btn-flat pull-left">곡 상세</a>
							</div>
							<!-- /.box-footer -->
						</div>
						<!-- /.box -->
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-12">
						<div class="box box-info">
							<div class="box-header with-border">
								<h3 class="box-title">Ex) 형태소 분석 결과</h3>
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
								<div class="table-responsive">
									<table id="morpheme-analyze-result" class="table no-margin">
										<thead>
											<tr>
												<th>텀(term)</th>
												<th>형태소(POS)</th>
												<th>position</th>
												<th>startOffset</th>
												<th>endOffset</th>
												<th>length</th>
											</tr>
										</thead>
										<tbody>
										</tbody>
									</table>
								</div>
								<!-- /.table-responsive -->
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
				</div>
				
				<div>
					<input type="text" class="form-control" id="search-input" placeholder="나의 스타는...좋아해요">
					<div id="artist-search-result" class="box box-default" style="display: none"></div>
				</div>
				<div id="artist-search-list-area" style="display: none">
					<div class="box box-info">
						<div class="box-header">
							<h3 class="box-title">아티스트 검색 결과</h3>
						</div>
						<!-- /.box-header -->
						<div class="box-body no-padding">
							<table id="artist-search-list" class="table table-condensed table-hover">
								<tr><th style="width: 15x">#</th><th>아티스트</th><th>곡수</th><th>장르</th></tr>
								<tr><td>1</td><td>빅뱅</td><td>7</td><td>힙합: 6, 랩: 5, 댄스: 3</td>
								</tr>
							</table>
						</div>
						<!-- /.box-body -->
					</div>
					<!-- /.box -->
				</div>
				
				<div id="tag-cloud-div" class="row" style="display: none;">
					<div class="col-md-12">
						<div class="box box-info">
							<div class="box-header with-border">
								<h3 class="box-title">나의 스타는...좋아요</h3>
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
								<div id='demo' class="tag-cloud-container"></div>
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
			
			<div class="col-md-5">
				<div class="row">
					<div class="col-md-12">
						<div  id="div-collpase-create-index" class="box box-navy collapsed-box">
							<div class="box-header with-border">
								<h3 class="box-title">shell) 사전에 단어 추가하기</h3>
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
									<jsp:include page="../component/shell/add_word.jsp"></jsp:include>
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

<script>
$(document).ready(function() {
	$('#search-input').keyup(inputOnKeyUp);
});
</script>

<script>
// 중복 코드. 공통 도출 필요
var inputOnKeyUp = function(e) {
	var k = e.which;
	if (k == 13) {
		searchOnClick();
	} else if (k == 20 /* Caps lock */
			|| k == 16 /* Shift */
			|| k == 9 /* Tab */
			|| k == 27 /* Escape Key */
			|| k == 17 /* Control Key */
			|| k == 91 /* Windows Command Key */
			|| k == 19 /* Pause Break */
			|| k == 18 /* Alt Key */
			|| k == 93 /* Right Click Point Key */
			|| (k >= 35 && k <= 40) /* Home, End, Arrow Keys */
			|| k == 45 /* Insert Key */
			|| (k >= 33 && k <= 34) /*Page Down, Page Up */
			|| (k >= 112 && k <= 123) /* F1 - F12 */
			|| (k >= 144 && k <= 145)) { /* Num Lock, Scroll Lock */
		return false;
	} else {
		$('#artist-search-result').html('');
		if ($('#search-input').val().length > 0) {
			$.ajax({
				type : "POST",
				url : "${pageContext.servletContext.contextPath}/overview/search/prefix_artist_aggs?term=" + encodeURIComponent($('#search-input').val()) + "&size=3",
				success : function(data) {
					var artists = data.aggregations.artist.buckets;
					if (artists.length > 0) {
						var html = "";
						for (var i = 0; i < artists.length; i++) {
							html += '<div class="box-body">' + artists[i].key + '</div>';
						}

						$('#artist-search-result').html(html);
						$('#artist-search-result').show();
					} else {
						$('#artist-search-result').hide();
					}
				},
				dataType : "json"
			});
		}
	}
};

var searchOnClick = function() {
	$('#artist-search-result').hide();
	
	$.ajax({
		type : "POST",
		url : "${pageContext.servletContext.contextPath}/overview/search/prefix_artist_aggs?term="
				+ encodeURIComponent($('#search-input').val())
				+ "&size=20",
		success : function(data) {
			var artists = data.aggregations.artist.buckets;
			if (artists.length > 0) {
				var html = '<thead><tr><th style="width: 15x">#</th><th>아티스트</th><th>곡수</th><th>장르</th></tr></thead>';
				
				for (var i = 0; i < artists.length; i++) {
					var genres = artists[i].genres.buckets;
					var genresHtml = '';
					for (var j = 0; j < genres.length; j++) {
						genresHtml = genresHtml + '&nbsp;&nbsp;<span class="helloes-basic-number">' + genres[j].doc_count + '</span>&nbsp;' + genres[j].key;
					}
					
					html += '<tr style="cursor:pointer;" onclick="searchArtistDetails(\'' + data.aggregations.id.buckets[i].key + '\');"><td>' + (i+1) + '</td><td>' + artists[i].key + '</td><td>' + artists[i].doc_count + '</td><td>' + genresHtml + '</td>';
				}
				
				html = '<tbody>' + html + '</tbody>';
				$('#artist-search-list').html(html);
				$('#artist-search-list-area').show();
			} else {
				var message = $('#search-input').val() + ' 아티스트가 없습니다';
				var html = '<thead><tr><th>' + message + '</th></tr></thead>';
				$('#artist-search-list').html(html);
				$('#artist-search-list-area').show();
			}
		},
		dataType : "json"
	});
};
</script>

<script>
var doAnalyze = function() {
	$.ajax({
		type: "POST",
		url: "${pageContext.servletContext.contextPath}/morpheme/intro/analyze?line=" + encodeURIComponent($('#test-line').val()),
		success : function(data) {
			var html = "";
			
			for ( var i = 0; i < data.length; i++ ) {
				html += "<tr><td>" + data[i].term + "</td><td>" + data[i].pos + "</td>	<td>" + data[i].position + "</td><td>" + data[i].startOffset + "</td><td>" + data[i].endOffset + "</td><td>" + data[i].length + "</td></tr>";
			}
			
			$('#morpheme-analyze-result tbody').html(html);			
		},
		error: function(data) {
		},
		dataType : "json"
	});
};

var searchArtistDetails = function(artistId) {
	$('#artist-search-list-area').hide();
	
	$.ajax({
		type : "POST",
		url : "${pageContext.servletContext.contextPath}/morpheme/intro/analyze_artist?artistId=" + artistId,
		success : function(data) {
			console.debug(data);
			
			var words = [];
			for ( var i = 0; i < data.length; i++ ) {
				words.push({text: data[i].term, weight: data[i].count});
			}
			
           $('#tag-cloud-div').show();
           $('#demo').jQCloud(words);
           $('#demo').jQCloud('update', words);

		},
		dataType : "json"
	});
};


</script>