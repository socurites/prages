{
  "query": {
    "term": {
      "artist_name_token": {
        "value": "#term"
      }
    }
  },
  "aggs": {
    "list": {
      "terms": {
        "field": "composers",
        "size": "#size"
      }
    }
  }
}