<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>


<link href="${pageContext.servletContext.contextPath}/resources/ext/d3-process-map/print.css" rel="stylesheet">
<link href="${pageContext.servletContext.contextPath}/resources/ext/d3-process-map/style.css" rel="stylesheet">
<link href="${pageContext.servletContext.contextPath}/resources/ext/d3-process-map/svg.css" rel="stylesheet">

<style>
.helloes-basic-number {
	font-style: italic;
	font-size: 14pt;
	font-weight: bold;
}
</style>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			파워 네트워크 <small>아티스트의 영향력 보기</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="fa fa-dashboard"></i> 응용하기</a></li>
			<li class="active">파워 네트워크</li>
		</ol>
	</section>

	<!-- Main content -->
	<section class="content">
		<div class="row">
			<div class="col-md-6">
				<div>
					<input type="text" class="form-control" id="search-input" placeholder="너랑 나랑은...">
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
				
				<div id="split-container">
					<div id="graph-container">
						<div id="graph"></div>
					</div>
					<div id="docs-container">
						<a id="docs-close" href="#">&times;</a>
						<div id="docs" class="docs"></div>
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
				url : "${pageContext.servletContext.contextPath}/morpheme/search/prefix_artist_aggs?term=" + encodeURIComponent($('#search-input').val()) + "&size=3",
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
		url : "${pageContext.servletContext.contextPath}/morpheme/search/prefix_artist_aggs?term="
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
					
					html += '<tr style="cursor:pointer;" onclick="searchArtistDetails(\'' + artists[i].key + '\');"><td>' + (i+1) + '</td><td>' + artists[i].key + '</td><td>' + artists[i].doc_count + '</td><td>' + genresHtml + '</td>';
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
var searchArtistDetails = function(artistName) {
	$.ajax({
		type : "POST",
		url : "${pageContext.servletContext.contextPath}/apply/power_network/search?artistName=" + encodeURIComponent(artistName) + "&size=5",
		success : function(data) {
			console.debug(data);
			
			resize();

		    graph.data = data;
		    drawGraph();

		    $('#docs-close').on('click', function() {
		        deselectObject();
		        return false;
		    });

		    $(document).on('click', '.select-object', function() {
		        var obj = graph.data[$(this).data('name')];
		        if (obj) {
		            selectObject(obj);
		        }
		        return false;
		    });
		},
		dataType : "json"
	});
};
</script>

<script	src="${pageContext.servletContext.contextPath}/resources/ext/d3-process-map/d3/d3.v3.min.js"></script>
<script	src="${pageContext.servletContext.contextPath}/resources/ext/d3-process-map/colorbrewer.js"></script>
<script	src="${pageContext.servletContext.contextPath}/resources/ext/d3-process-map/geometry.js"></script>
<script	src="${pageContext.servletContext.contextPath}/resources/helloes/js/helloes.network.js"></script>