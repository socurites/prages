{
  "query": {
    "filtered": {
      "filter": {
        "term": {
          "artist_id": "#artistId"
        }
      }
    }
  },
  "aggs": {
    "genres": {
      "terms": {
        "field": "genres",
        "size": 5
      }
    },
    "composers": {
      "terms": {
        "field": "composers",
        "size": 5
      }
    },
    "lyricists": {
      "terms": {
        "field": "lyricists",
        "size": 5
      }
    },
    "arrangers": {
      "terms": {
        "field": "arrangers",
        "size": 5
      }
    },
    "issue_date": {
      "date_histogram": {
        "field": "issue_date",
        "interval": "year",
        "format": "yyyy",
        "time_zone": "09:00"
      }
    }
  }
}