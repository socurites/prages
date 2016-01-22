"sort": [
    "_score",
    {
      "artistPriority": {
        "order": "desc"
      }
    },
    {
      "sortHit": {
        "order": "desc"
      }
    },
    {
      "sortArtist": {
        "order": "asc"
      }
    }
  ]