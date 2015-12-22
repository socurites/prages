{
  "query": {
    "prefix": {
      "artist_name_token_eng": "#term"
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