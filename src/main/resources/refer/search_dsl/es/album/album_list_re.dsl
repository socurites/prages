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
                        "query": {
                          "match": {
                            "titleWapAnalz": {
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
                            "albumRepNmExact": "#standardTerm"
                          }
                        }
                      }
                    },
                    {
                      "constant_score": {
                        "query": {
                          "match": {
                            "albumRepNmAnalz": {
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
                            "mainArtistNmBasketExact": "#standardTerm"
                          }
                        }
                      }
                    },
                    {
                      "constant_score": {
                        "query": {
                          "match": {
                            "mainArtistNmBasketGram": {
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
                            "trackSongNmBasketExact": "#standardTerm"
                          }
                        }
                      }
                    },
                    {
                      "constant_score": {
                        "filter": {
                          "term": {
                            "trackArtistNmBasketExact": "#standardTerm"
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
                  "query": {
	                  "match": {
	                    "titleWebListGram": {
	                      "query": "#reStandardTerm",
	                      "minimum_should_match": "100%",
	                      "type": "phrase"
	                    }
	                  }
                  }
                },
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
            "query": {
              "match": {
                "titleWapAnalz": {
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
              "albumRepNmExact": "#standardTerm"
            }
          },
          "weight": 10.0
        },
        {
          "filter": {
            "query": {
              "match": {
                "albumRepNmAnalz": {
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
              "mainArtistNmBasketExact": "#standardTerm"
            }
          },
          "weight": 10.0
        },
        {
          "filter": {
            "term": {
              "trackSongNmBasketExact": "#standardTerm"
            }
          },
          "weight": 5.0
        },
        {
          "filter": {
            "term": {
              "trackArtistNmBasketExact": "#standardTerm"
            }
          },
          "weight": 4.0
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
