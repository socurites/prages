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
                            "songRepNmExact": "#standardTerm"
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
                            "artistNmBasketExact": "#standardTerm"
                          }
                        }
                      }
                    },
                    {
                      "constant_score": {
                        "query": {
                          "match": {
                            "artistNmBasketAnalz": {
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
                            "artistNmBasketGram": {
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
                            "albumTitleWebListExact": "#standardTerm"
                          }
                        }
                      }
                    },
                    {
                      "constant_score": {
                        "query": {
                          "match": {
                            "albumTitleWebListAnalz": {
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
                            "albumTitleWebListGram": {
                              "query": "#koTerm",
                              "minimum_should_match": "100%",
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
                  "terms": {
                    "flacFlg": ["#flacFlg", "3"]
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
              "songRepNmExact": "#standardTerm"
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
              "artistNmBasketExact": "#standardTerm"
            }
          },
          "weight": 10.0
        },
        {
          "filter": {
            "query": {
              "match": {
                "artistNmBasketAnalz": {
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
              "albumTitleWebListExact": "#standardTerm"
            }
          },
          "weight": 10.0
        },
        {
          "filter": {
            "query": {
              "match": {
                "albumTitleWebListAnalz": {
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
