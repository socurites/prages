curl -XDELETE 'http://localhost:9200/table?pretty=true'

curl -XPUT 'http://localhost:9200/table?pretty=true' -d '
{
   "number_of_shards": 1,
   "number_of_replicas": 0,
   "refresh_interval": "1s",
   "translog.flush_threshold_ops": 500,
   "_source" : {
     "enabled" : "true"
    },
     "_all" : {
     "enabled" : "false"
    },
     "index" : {
         "analysis" : {
             "analyzer" : {
                 "melon_korean_analyzer" : {
                    "type": "custom",
                    "tokenizer": "korean_tokenizer",
                    "filter": ["lowercase"]
                 },
                 "melon_keyword_analyzer" : {
                    "tokenizer" : "keyword",
                    "filter" : ["lowercase"]
                 }
             },
             "tokenizer" : {
                 "korean_tokenizer" : {
                     "type": "mecab_ko_standard_tokenizer",
                     "mecab_dic_dir" : "/home/elasticsearch/mecab/lib/mecab/dic/mecab-ko-dic"
                 }
             }
         }
     }
 }
'

curl -XPUT 'http://localhost:9200/table/_mappings/detail?pretty=true' -d '
{
    "detail" : {
        "_id" : {
            "index" : "not_analyzed",
            "path" : "table_name_hash"
        },
        "_source" : {
            "enabled" : "true"
        },
        "_all" : {
            "enabled" : "false"
        },
        "search_analyzer": "standard",
        "properties" : {
            "table_name_hash" : {"type" : "string", "store" : "yes", "index" : "not_analyzed"},
            "table_name" : {"type" : "string", "store" : "yes", "index" : "not_analyzed"},
            "table_name_analz" : {"type" : "string", "store" : "yes", "index" : "analyzed", "norms": {"enabled": "false"}, index_analyzer: "melon_korean_analyzer", search_analyzer: "melon_korean_analyzer"},
            "domain_area" : {"type" : "string", "store" : "yes", "index" : "not_analyzed"},
            "domain_area_analz" : {"type" : "string", "store" : "yes", "index" : "analyzed", "norms": {"enabled": "false"}, index_analyzer: "melon_korean_analyzer", search_analyzer: "melon_korean_analyzer"},
            "date_create" : {"type" : "date", "store" : "yes", "index" : "not_analyzed", "format": "yyyy-MM-dd"},
            "feature" : {"type" : "string", "store" : "yes", "index" : "not_analyzed"},
            "cycle_store" : {"type" : "string", "store" : "yes", "index" : "not_analyzed"},
            "summary" : {"type" : "string", "store" : "yes", "index" : "analyzed", "norms": {"enabled": "false"}, index_analyzer: "melon_korean_analyzer", search_analyzer: "melon_korean_analyzer"},
            "kor_table_name_analz" : {"type" : "string", "store" : "yes", "index" : "analyzed", "norms": {"enabled": "false"}, index_analyzer: "melon_korean_analyzer", search_analyzer: "melon_korean_analyzer"},
            "database" : {"type" : "string", "store" : "yes", "index" : "not_analyzed"},
            "owner" : {"type" : "string", "store" : "yes", "index" : "not_analyzed"},
            "table_space" : {"type" : "string", "store" : "yes", "index" : "not_analyzed"},
            "columns": {
                "properties": {
                    "no" : {"type" : "integer", "store" : "yes", "index" : "not_analyzed"},
                    "kor_name_token" : {"type" : "string", "store" : "yes", "index" : "analyzed", "norms": {"enabled": "false"}, index_analyzer: "melon_keyword_analyzer", search_analyzer: "melon_keyword_analyzer"},
                    "eng_name" : {"type" : "string", "store" : "yes", "index" : "not_analyzed"},
                    "data_type" : {"type" : "string", "store" : "yes", "index" : "not_analyzed"},
                    "length" : {"type" : "string", "store" : "yes", "index" : "not_analyzed"},
                    "not_null" : {"type" : "string", "store" : "yes", "index" : "not_analyzed"},
                    "pk_order" : {"type" : "integer", "store" : "yes", "index" : "not_analyzed"},
                    "default_val" : {"type" : "string", "store" : "yes", "index" : "not_analyzed"},
                    "fk_desc_token" : {"type" : "string", "store" : "yes", "index" : "analyzed", "norms": {"enabled": "false"}, index_analyzer: "melon_keyword_analyzer", search_analyzer: "melon_keyword_analyzer"},
                    "summary_token" : {"type" : "string", "store" : "yes", "index" : "analyzed", "norms": {"enabled": "false"}, index_analyzer: "melon_keyword_analyzer", search_analyzer: "melon_keyword_analyzer"}
                }
            }
        }
    }
}
'