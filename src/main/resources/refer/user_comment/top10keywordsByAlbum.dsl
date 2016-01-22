{
  "query": {
    "filtered": {
      "query": {
        "match_all": {}
      },
      "filter": {
        "term": {
          "contsId": "#contsId"
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