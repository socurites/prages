{
  "query": {
    "filtered": {
      "query": {
        "match_all": {}
      },
      "filter": {
        "term": {
          "artistId": "#contsId"
        }
      }
    }
  },
  "aggs": {
    "keywords": {
      "terms": {
        "field": "s3Keywords",
        "size": "#size"
      }
    }
  }
}