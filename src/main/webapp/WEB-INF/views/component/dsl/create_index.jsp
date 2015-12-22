<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
curl -XDELETE 'http://localhost:9200/song?pretty=true'

curl -XPUT 'http://localhost:9200/song?pretty=true' -d '
{
   "number_of_shards": 5,
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

curl -XPUT 'http://localhost:9200/song/_mappings/detail?pretty=true' -d '
{
    "detail" : {
        "_id" : {
            "index" : "not_analyzed",
            "path" : "songId"
        },
        "_source" : {
            "enabled" : "true"
        },
        "_all" : {
            "enabled" : "false"
        },
        "search_analyzer": "standard",
        "properties" : {
            "songId" : {"type" : "string", "store" : "yes", "index" : "not_analyzed"},
            "title_analz" : {"type" : "string", "store" : "yes", "index" : "analyzed", "norms": {"enabled": "false"}, index_analyzer: "melon_korean_analyzer", search_analyzer: "melon_korean_analyzer"},
            "title_token" : {"type" : "string", "store" : "yes", "index" : "analyzed", "norms": {"enabled": "false"}, index_analyzer: "melon_keyword_analyzer", search_analyzer: "melon_keyword_analyzer"},
            "title_token_eng" : {"type" : "string", "store" : "yes", "index" : "analyzed", "norms": {"enabled": "false"}, index_analyzer: "stop", search_analyzer: "stop"},
            "album_name_analz" : {"type" : "string", "store" : "yes", "index" : "analyzed", "norms": {"enabled": "false"}, index_analyzer: "melon_korean_analyzer", search_analyzer: "melon_korean_analyzer"},
            "album_name_token" : {"type" : "string", "store" : "yes", "index" : "analyzed", "norms": {"enabled": "false"}, index_analyzer: "melon_keyword_analyzer", search_analyzer: "melon_keyword_analyzer"},
            "album_name_token_eng" : {"type" : "string", "store" : "yes", "index" : "analyzed", "norms": {"enabled": "false"}, index_analyzer: "stop", search_analyzer: "stop"},
            "artist_id" : {"type" : "string", "store" : "yes", "index" : "not_analyzed"},
            "artist_name_analz" : {"type" : "string", "store" : "yes", "index" : "analyzed", "norms": {"enabled": "false"}, index_analyzer: "melon_korean_analyzer", search_analyzer: "melon_korean_analyzer"},
            "artist_name_token" : {"type" : "string", "store" : "yes", "index" : "analyzed", "norms": {"enabled": "false"}, index_analyzer: "melon_keyword_analyzer", search_analyzer: "melon_keyword_analyzer"},
            "artist_name_token_eng" : {"type" : "string", "store" : "yes", "index" : "analyzed", "norms": {"enabled": "false"}, index_analyzer: "stop", search_analyzer: "stop"},
            "label_name_analz" : {"type" : "string", "store" : "yes", "index" : "analyzed", "norms": {"enabled": "false"}, index_analyzer: "melon_korean_analyzer", search_analyzer: "melon_korean_analyzer"},
            "label_name_token" : {"type" : "string", "store" : "yes", "index" : "analyzed", "norms": {"enabled": "false"}, index_analyzer: "melon_keyword_analyzer", search_analyzer: "melon_keyword_analyzer"},
            "label_name_token_eng" : {"type" : "string", "store" : "yes", "index" : "analyzed", "norms": {"enabled": "false"}, index_analyzer: "stop", search_analyzer: "stop"},
            "issue_date" : {"type" : "date", "store" : "yes", "index" : "not_analyzed", "format": "yyyyMMdd"},
            "act_type" : {"type" : "string", "store" : "yes", "index" : "not_analyzed", index_analyzer: "melon_keyword_analyzer", search_analyzer: "melon_keyword_analyzer"},
            "album_image_path" : {"type" : "string", "store" : "yes", "index" : "not_analyzed", index_analyzer: "melon_keyword_analyzer", search_analyzer: "melon_keyword_analyzer"},
            "artist_image_path" : {"type" : "string", "store" : "yes", "index" : "not_analyzed", index_analyzer: "melon_keyword_analyzer", search_analyzer: "melon_keyword_analyzer"},
            "genres" : {"type" : "string", "index_name" : "genres", "store" : "yes", "index" : "analyzed", "norms": {"enabled": "false"}, index_analyzer: "melon_keyword_analyzer", search_analyzer: "melon_keyword_analyzer"},
            "lyricists" : {"type" : "string", "index_name" : "lyricists", "store" : "yes", "index" : "analyzed", "norms": {"enabled": "false"}, index_analyzer: "melon_keyword_analyzer", search_analyzer: "melon_keyword_analyzer"},
            "composers" : {"type" : "string", "index_name" : "composers", "store" : "yes", "index" : "analyzed", "norms": {"enabled": "false"}, index_analyzer: "melon_keyword_analyzer", search_analyzer: "melon_keyword_analyzer"},
            "arrangers" : {"type" : "string", "index_name" : "arrangers", "store" : "yes", "index" : "analyzed", "norms": {"enabled": "false"}, index_analyzer: "melon_keyword_analyzer", search_analyzer: "melon_keyword_analyzer"},
            "lyric_analz" : {"type" : "string", "store" : "yes", "index" : "analyzed", "norms": {"enabled": "false"}, index_analyzer: "melon_korean_analyzer", search_analyzer: "melon_korean_analyzer"},
            "lyric_token" : {"type" : "string", "store" : "yes", "index" : "analyzed", "norms": {"enabled": "false"}, index_analyzer: "stop", search_analyzer: "stop"}
        }
    }
}
'