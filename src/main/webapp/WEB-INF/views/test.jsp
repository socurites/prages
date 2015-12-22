<style>
.link {
	stroke-width: 4px;
}

.node {
	stroke: #fff;
	stroke-width: 2px;
}

.textClass {
	stroke: #23232;
	font-family: "Arial", "Liberation Sans", Arial, Helvetica, sans-serif;
	font-weight: bold;
	stroke-width: 3;
	font-size: 20px;
}

#licensing {
	fill: green;
}

.link.licensing {
	stroke: green;
}

#chart, .ui-widget {
	border-style: solid;
	border-width: 2px;
	border-color: white;
}
</style>


<!-- /.box -->

<script>
	$("#demo").awesome
	Cloud({
		"size" : {
			"factor" : 0, // font resize factor, 0 means automatic
			"normalize" : false
		// reduces outliers for more attractive output
		},
		"color" : {
			"background" : "rgba(255,255,255,0)", // background color, transparent by default
			"start" : "#20f", // color of the smallest font, if options.color = "gradient""
			"end" : "rgb(200,0,0)" // color of the largest font, if options.color = "gradient"
		},
		"options" : {
			"color" : "random-dark", // random-light, random-dark, gradient
			"rotationRatio" : 0.35, // 0 is all horizontal, 1 is all vertical
			"printMultiplier" : 3, // set to 3 for nice printer output; higher numbers take longer
			"sort" : "random" // highest, lowest or random
		},
		"font" : "'Times New Roman', Times, serif", //  the CSS font-family string
		"shape" : "square" // circle, square, star or a theta function describing a shape
	});
