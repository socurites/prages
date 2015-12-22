<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
{
  "query": {
    "bool": {
      "should": [
        {
          "match": {
            "title_analz": "#term"
          }
        },
        {
          "match": {
            "album_name_analz": "#term"
          }
        },
        {
          "match": {
            "artist_name_analz": "#term"
          }
        },
        {
          "match": {
            "label_name_analz": "#term"
          }
        },
        {
          "match": {
            "genres": "#term"
          }
        },
        {
          "match": {
            "lyricists": "#term"
          }
        },
        {
          "match": {
            "composers": "#term"
          }
        },
        {
          "match": {
            "arrangers": "#term"
          }
        },
        {
          "match": {
            "lyric_analz": "#term"
          }
        }
      ]
    }
  },
  "highlight" : {
  	"pre_tags": ["<span class='hightlight-field'>"], 
	"post_tags": ["</span>"],
    "fields" : {
        "title_analz" : {"force_source" : true},
        "album_name_analz" : {"force_source" : true},
        "artist_name_analz" : {"force_source" : true},
        "label_name_analz" : {"force_source" : true},
        "genres" : {"force_source" : true},
        "lyricists" : {"force_source" : true},
        "composers" : {"force_source" : true},
        "arrangers" : {"force_source" : true},
        "lyric_analz" : {"force_source" : true}
    }
    }
}