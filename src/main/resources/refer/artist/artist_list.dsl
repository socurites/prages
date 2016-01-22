{
  "query": {
    "function_score": {
      "query": {
        "filtered": {
          "query": {
            "constant_score": {
              "query": {
                "bool": {
                  "should": [
                    {
                      "constant_score": {
                        "filter": {
                          "term": {
                            "titleWebListExact": "#standardTerm"
                          }
                        }
                      }
                    },
                  	{
	                  "constant_score": {
	                    "filter": {
	                      "term": {
	                        "titleWebListKoFirst": "#koTerm"
	                      }
	                    }
	                  }
	                },
                    {
                      "constant_score": {
                        "query": {
                          "match": {
                            "titleWebListAnalz": {
                              "query": "#phraseTerm",
                              "operator": "and",
                              "type": "phrase"
                            }
                          }
                        }
                      }
                    },
                    {
                      "constant_score": {
                        "query": {
                          "match": {
                            "titleWebListGram": {
                              "query": "#koTerm",
                              "minimum_should_match": "100%",
                              "type": "phrase"
                            }
                          }
                        }
                      }
                    },
                    {
                      "constant_score": {
                        "filter": {
                          "term": {
                            "titleWapExact": "#standardTerm"
                          }
                        }
                      }
                    },
                    {
                      "constant_score": {
                        "filter": {
                          "term": {
                            "artistRepNmExact": "#standardTerm"
                          }
                        }
                      }
                    },
                    {
                      "constant_score": {
                        "filter": {
                          "term": {
                            "korNameExact": "#standardTerm"
                          }
                        }
                      }
                    },
                    {
                      "constant_score": {
                        "filter": {
                          "term": {
                            "engNameExact": "#standardTerm"
                          }
                        }
                      }
                    },
                    {
                      "constant_score": {
                        "filter": {
                          "term": {
                            "realNameExact": "#standardTerm"
                          }
                        }
                      }
                    },
                    {
                      "constant_score": {
                        "filter": {
                          "term": {
                            "srchKeywordExact": "#standardTerm"
                          }
                        }
                      }
                    },
                    {
                      "constant_score": {
                        "query": {
                          "match": {
                            "srchKeywordAnalz": {
                              "query": "#phraseTerm",
                              "operator": "and",
                              "type": "phrase"
                            }
                          }
                        }
                      }
                    },
                    {
                      "constant_score": {
                        "filter": {
                          "term": {
                            "memNameBasketExact": "#standardTerm"
                          }
                        }
                      }
                    },
                    {
                      "constant_score": {
                        "query": {
                          "match": {
                            "memNameBasketAnalz": {
                              "query": "#phraseTerm",
                              "operator": "and",
                              "type": "phrase"
                            }
                          }
                        }
                      }
                    },
                    {
                      "constant_score": {
                        "filter": {
                          "term": {
                            "groupMemEtcExact": "#standardTerm"
                          }
                        }
                      }
                    },
                    {
                      "constant_score": {
                        "query": {
                          "match": {
                            "groupMemEtcAnalz": {
                              "query": "#phraseTerm",
                              "operator": "and",
                              "type": "phrase"
                            }
                          }
                        }
                      }
                    }                   
                  ]
                }
              }
            }
          },
          "filter": {
            "bool": {
              "must": [
                {
                  "term": {
                    "searchFlag": "Y"
                  }
                },
                {
                  "term": {
                    "flacFlg": "#flacFlg"
                  }
                }
              ]
            }
          }
        }
      },
      "functions": [
        {
          "filter": {
            "term": {
              "titleWebListExact": "#standardTerm"
            }
          },
          "weight": 10.0
        },
      	{
          "filter": {
            "term": {
              "titleWebListKoFirst": "#koTerm"
            }
          },
          "weight": 10.0
        },
        {
          "filter": {
            "query": {
              "match": {
                "titleWebListAnalz": {
                  "query": "#phraseTerm",
                  "operator": "and",
                  "type": "phrase"
                }
              }
            }
          },
          "weight": 9.0
        },
        {
          "filter": {
            "term": {
              "titleWapExact": "#standardTerm"
            }
          },
          "weight": 10.0
        },
        {
          "filter": {
            "term": {
              "artistRepNmExact": "#standardTerm"
            }
          },
          "weight": 10.0
        },
        {
          "filter": {
            "term": {
              "korNameExact": "#standardTerm"
            }
          },
          "weight": 10.0
        },
        {
          "filter": {
            "term": {
              "engNameExact": "#standardTerm"
            }
          },
          "weight": 10.0
        },
        {
          "filter": {
            "term": {
              "realNameExact": "#standardTerm"
            }
          },
          "weight": 10.0
        },
        {
          "filter": {
            "term": {
              "srchKeywordExact": "#standardTerm"
            }
          },
          "weight": 8.0
        },
        {
          "filter": {
            "query": {
              "match": {
                "srchKeywordAnalz": {
                  "query": "#phraseTerm",
                  "operator": "and",
                  "type": "phrase"
                }
              }
            }
          },
          "weight": 7.0
        },
        {
          "filter": {
            "term": {
              "memNameBasketExact": "#standardTerm"
            }
          },
          "weight": 6.0
        },
        {
          "filter": {
            "query": {
              "match": {
                "memNameBasketAnalz": {
                  "query": "#phraseTerm",
                  "operator": "and",
                  "type": "phrase"
                }
              }
            }
          },
          "weight": 5.0
        },
        {
          "filter": {
            "term": {
              "groupMemEtcExact": "#standardTerm"
            }
          },
          "weight": 6.0
        },
        {
          "filter": {
            "query": {
              "match": {
                "groupMemEtcAnalz": {
                  "query": "#phraseTerm",
                  "operator": "and",
                  "type": "phrase"
                }
              }
            }
          },
          "weight": 5.0
        }
      ],
      "score_mode": "max"
    }
  },
  #sort,
  "from": "#from",
  "size": "#size",
  "explain": true
}