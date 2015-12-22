<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

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
			검색하기 <small>검색의 기본</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="fa fa-dashboard"></i> 검색엔진아란?</a></li>
			<li class="active">검색하기</li>
		</ol>
	</section>

	<!-- Main content -->
	<section class="content">
		<div class="row">
			<div class="col-md-6">
				<div>
					<input type="text" class="form-control" id="search-input" placeholder="검색어를 입력하세요">
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
			</div>
			<div class="col-md-6">
				<div id="info-artist-detail-area" class="row" style="display: none;">
					<div class="col-md-12">
						<div class="box box-widget widget-user">
							<!-- Add the bg color to the header using any of the bg-* classes -->
							<div id="info-artist-bg-img" class="widget-user-header bg-black" style="background: url('${pageContext.servletContext.contextPath}/resources/helloes/image/artist_global_bg.png') center center;">
								<h3 id="info-artist-name" class="widget-user-username">bigbang</h3>
								<h5 id="info-label-name" class="widget-user-desc">(주)YG엔터테인먼트</h5>
							</div>
							<div class="widget-user-image">
								<img id="info-artist-img" class="img-circle" src="http://cdnimg.melon.co.kr/cm/artistcrop/images/001/98/094/198094_300.jpg">
							</div>
							<div class="box-footer">
								<div class="row">
									<div class="col-sm-4 border-right">
										<div class="description-block">
											<h5 id="info-act-type" class="description-header">그룹</h5>
										</div>
										<!-- /.description-block -->
									</div>
									<!-- /.col -->
									<div class="col-sm-4 border-right">
										<div class="description-block">
										</div>
										<!-- /.description-block -->
									</div>
									<!-- /.col -->
									<div class="col-sm-4">
										<div class="description-block">
											<h5 id="info-artist-song-count" class="description-header">35</h5>
											<span class="description-text">곡</span>
										</div>
										<!-- /.description-block -->
									</div>
									<!-- /.col -->
								</div>
								<!-- /.row -->
							</div>
						</div>
						<!-- /.widget-user -->
					</div>
					<div class="col-md-6">
						<div class="box box-default">
							<div class="box-header with-border">
								<h3 class="box-title">장르</h3>
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
									<div class="col-md-7">
										<div class="chart-responsive">
											<canvas id="pie-chart-genres" height="150"></canvas>
										</div>
										<!-- ./chart-responsive -->
									</div>
									<!-- /.col -->
									<div class="col-md-5">
										<ul id="pie-chart-genres-legend" class="chart-legend clearfix">
											<li><i class="fa fa-circle-o text-red"></i> Chrome</li>
										</ul>
									</div>
									<!-- /.col -->
								</div>
								<!-- /.row -->
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
					<div class="col-md-6">
						<div class="box box-default">
							<div class="box-header with-border">
								<h3 class="box-title">작곡가</h3>
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
									<div class="col-md-7">
										<div class="chart-responsive">
											<canvas id="pie-chart-composers" height="150"></canvas>
										</div>
										<!-- ./chart-responsive -->
									</div>
									<!-- /.col -->
									<div class="col-md-5">
										<ul id="pie-chart-composers-legend" class="chart-legend clearfix">
											<li><i class="fa fa-circle-o text-red"></i> Chrome</li>
										</ul>
									</div>
									<!-- /.col -->
								</div>
								<!-- /.row -->
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
					<div class="col-md-6">
						<div class="box box-default">
							<div class="box-header with-border">
								<h3 class="box-title">작사가</h3>
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
									<div class="col-md-7">
										<div class="chart-responsive">
											<canvas id="pie-chart-lyricists" height="150"></canvas>
										</div>
										<!-- ./chart-responsive -->
									</div>
									<!-- /.col -->
									<div class="col-md-5">
										<ul id="pie-chart-lyricists-legend" class="chart-legend clearfix">
											<li><i class="fa fa-circle-o text-red"></i> Chrome</li>
										</ul>
									</div>
									<!-- /.col -->
								</div>
								<!-- /.row -->
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
					<div class="col-md-6">
						<div class="box box-default">
							<div class="box-header with-border">
								<h3 class="box-title">편곡자</h3>
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
									<div class="col-md-7">
										<div class="chart-responsive">
											<canvas id="pie-chart-arrangers" height="150"></canvas>
										</div>
										<!-- ./chart-responsive -->
									</div>
									<!-- /.col -->
									<div class="col-md-5">
										<ul id="pie-chart-arrangers-legend" class="chart-legend clearfix">
											<li><i class="fa fa-circle-o text-red"></i> Chrome</li>
										</ul>
									</div>
									<!-- /.col -->
								</div>
								<!-- /.row -->
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
					<div class="col-md-12">
						<div class="box box-success">
							<div class="box-header with-border">
								<h3 class="box-title">년도별 음원 발매 건수</h3>
								<div class="box-tools pull-right">
									<button class="btn btn-box-tool" data-widget="collapse">
										<i class="fa fa-minus"></i>
									</button>
									<button class="btn btn-box-tool" data-widget="remove">
										<i class="fa fa-times"></i>
									</button>
								</div>
							</div>
							<div class="box-body">
								<div class="chart">
									<canvas id="barChart" style="height: 200px"></canvas>
								</div>
							</div>
							<!-- /.box-body -->
						</div>
						<!-- /.box -->
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-12">
						<div class="box box-danger collapsed-box">
							<div class="box-header with-border">
								<h3 class="box-title">DSL) Bulk Indexing Dev</h3>
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
									<jsp:include page="../component/shell/bulk_indexing.jsp"></jsp:include>
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
								<h3 class="box-title">DSL) 자동완성 for Dev</h3>
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
									<jsp:include page="../component/dsl/overview_prefix_artist_aggs.jsp"></jsp:include>
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
								<h3 class="box-title">DSL) 아티스트 상세 Aggregations for Dev</h3>
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
									<jsp:include page="../component/dsl/overview_artist_detail_aggs.jsp"></jsp:include>
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
var searchArtistDetails = function(artistId) {
	$.ajax({
		type : "POST",
		url : "${pageContext.servletContext.contextPath}/overview/search/artist_detail_aggs?artistId=" + artistId,
		success : function(data) {
			var artistInfo = data.info.hits.hits[0]._source;
			var detailInfo = data.aggs;
			
			var bgKey = parseInt(artistInfo.artist_id) % 4 + 1;
			
			$('#info-artist-name').text(artistInfo.artist_name_analz);
			$('#info-label-name').text(artistInfo.label_name_analz);
			$('#info-act-type').text(artistInfo.act_type);
			$('#info-artist-song-count').text(data.aggs.hits.total);
			$('#info-artist-img').attr('src', 'http://cdnimg.melon.co.kr' + artistInfo.artist_image_path);
			$('#info-artist-bg-img').attr('style', "background: url('${pageContext.servletContext.contextPath}/resources/helloes/image/artist_global_bg" + bgKey + ".png') center center;");
			
			$('#info-artist-detail-area').show();
			
			// artist detail ui design prototype
			var pieOptions = {
				//Boolean - Whether we should show a stroke on each segment
				segmentShowStroke : true,
				//String - The colour of each segment stroke
				segmentStrokeColor : "#fff",
				//Number - The width of each segment stroke
				segmentStrokeWidth : 1,
				//Number - The percentage of the chart that we cut out of the middle
				percentageInnerCutout : 50, // This is 0 for Pie charts
				//Number - Amount of animation steps
				animationSteps : 100,
				//String - Animation easing effect
				animationEasing : "easeOutBounce",
				//Boolean - Whether we animate the rotation of the Doughnut
				animateRotate : true,
				//Boolean - Whether we animate scaling the Doughnut from the centre
				animateScale : false,
				//Boolean - whether to make the chart responsive to window resizing
				responsive : true,
				// Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
				maintainAspectRatio : false
			}
			
			
			drawPieChart(pieOptions, "pie-chart-genres", detailInfo.aggregations.genres.buckets);
			drawPieChart(pieOptions, "pie-chart-composers", detailInfo.aggregations.composers.buckets);
			drawPieChart(pieOptions, "pie-chart-lyricists", detailInfo.aggregations.lyricists.buckets);
			drawPieChart(pieOptions, "pie-chart-arrangers", detailInfo.aggregations.arrangers.buckets);
			
			
			drawBarChart(detailInfo.aggregations.issue_date.buckets);
		},
		dataType : "json"
	});
};
</script>

<script>
var drawPieChart = function(pieOptions, chartId, dataArr) {
	var colorArr = ["#f56954", "#00a65a", "#f39c12", "#00c0ef", "#3c8dbc"];
	var legendColorArr = ["red", "green", "yellow", "aqua", "light-blue"];

	var pieChartCanvas = $("#" + chartId).get(0).getContext("2d");
	var pieChart = new Chart(pieChartCanvas);
	
	
	var pieData = [];
	var legendHtml = "";
	for ( var i = 0; i < dataArr.length; i++ ) {
		pieData.push({
			value: dataArr[i].doc_count,
			label: dataArr[i].key,
			color: colorArr[i],
			highlight: colorArr[i]
		});
		
		legendHtml = legendHtml + '<li><i class="fa fa-circle-o text-' + legendColorArr[i] + '"></i> ' + dataArr[i].doc_count + ' ' + dataArr[i].key + '</li>';
	}
	
	pieChart.Doughnut(pieData, pieOptions);
	
	$('#' + chartId + "-legend").html(legendHtml);
	
};

var drawBarChart = function(dataArr) {
	var barX = [];
	var barData = [];
	for ( var i = 0; i < dataArr.length; i++ ) {
		barX.push(dataArr[i].key_as_string);
		barData.push(dataArr[i].doc_count);
	}
	
	var areaChartData = {
		labels : barX,
		datasets : [ {
              label: "Digital Goods",
              fillColor: "rgba(60,141,188,0.9)",
              strokeColor: "rgba(60,141,188,0.8)",
              pointColor: "#3b8bba",
              pointStrokeColor: "rgba(60,141,188,1)",
              pointHighlightFill: "#fff",
              pointHighlightStroke: "rgba(60,141,188,1)",
              data: barData
            } ]
	};

	var barChartCanvas = $("#barChart").get(0).getContext("2d");
	var barChart = new Chart(barChartCanvas);
	var barChartData = areaChartData;
	var barChartOptions = {
		//Boolean - Whether the scale should start at zero, or an order of magnitude down from the lowest value
		scaleBeginAtZero : true,
		//Boolean - Whether grid lines are shown across the chart
		scaleShowGridLines : true,
		//String - Colour of the grid lines
		scaleGridLineColor : "rgba(0,0,0,.05)",
		//Number - Width of the grid lines
		scaleGridLineWidth : 1,
		//Boolean - Whether to show horizontal lines (except X axis)
		scaleShowHorizontalLines : true,
		//Boolean - Whether to show vertical lines (except Y axis)
		scaleShowVerticalLines : true,
		//Boolean - If there is a stroke on each bar
		barShowStroke : true,
		//Number - Pixel width of the bar stroke
		barStrokeWidth : 2,
		//Number - Spacing between each of the X value sets
		barValueSpacing : 5,
		//Number - Spacing between data sets within X values
		barDatasetSpacing : 1,
		//String - A legend template
		responsive : true,
		maintainAspectRatio : true
	};

	barChartOptions.datasetFill = false;
	barChart.Bar(barChartData, barChartOptions);
};
</script>
