<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
{
  "query": {
    "bool": {
      "should": [
        {
          "prefix": {
            "artist_name_token_eng": "#term"
          }
        },
        {
          "prefix": {
            "artist_name_token_eng": "#synonym"
          }
        }
      ]
    }
  },
  "aggs": {
    "artist": {
      "terms": {
        "field": "artist_name_token",
        "size": "#size"
      },
      "aggs": {
        "genres": {
          "terms": {
            "field": "genres",
            "size": 3
          }
        }
      }
    },
    "id" : {
      "terms": {
        "field": "artist_id",
        "size": "#size"
      }
    }
  }
}