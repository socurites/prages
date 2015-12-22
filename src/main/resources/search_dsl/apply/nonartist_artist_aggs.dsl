{
  "query": {
    "bool": {
      "should": [
        {
          "term": {
            "composers": {
              "value": "#term"
            }
          }
        },
        {
          "term": {
            "lyricists": {
              "value": "#term"
            }
          }
        },
        {
          "term": {
            "arrangers": {
              "value": "#term"
            }
          }
        }
      ]
    }
  },
  "aggs": {
    "list": {
      "terms": {
        "field": "artist_name_token",
        "size": "#size"
      }
    }
  }
}