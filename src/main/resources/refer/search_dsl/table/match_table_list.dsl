{
  "query": {
    "bool": {
      "should": [
        {
          "match": {
            "table_name_analz": "#term"
          }
        },
        {
          "match": {
            "kor_table_name_analz": "#term"
          }
        }
      ]
    }
  },
  "from": "#from",
  "size": "#size"
}