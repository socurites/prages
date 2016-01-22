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
		                    "query": {
		                      "prefix": {
		                        "titleWebListSyllable": {
		                          "value": "#syllable"
		                        }
		                      }
		                    }
		                  }
		                },
		                {
		                  "constant_score": {
		                    "query": {
		                      "prefix": {
		                        "titleWebListFirst": {
		                          "value": "#first"
		                        }
		                      }
		                    }
		                  }
		                },
		                {
		                  "constant_score": {
		                    "query": {
		                      "prefix": {
		                        "titleWebListEnSyllable": {
		                          "value": "#enSyllable"
		                        }
		                      }
		                    }
		                  }
		                },
		                {
		                  "constant_score": {
		                    "query": {
		                      "prefix": {
		                        "albumRepNmSyllable": {
		                          "value": "#syllable"
		                        }
		                      }
		                    }
		                  }
		                },
		                {
		                  "constant_score": {
		                    "query": {
		                      "prefix": {
		                        "albumRepNmFirst": {
		                          "value": "#syllable"
		                        }
		                      }
		                    }
		                  }
		                },
		                {
		                  "constant_score": {
		                    "query": {
		                      "prefix": {
		                        "albumRepNmEnSyllable": {
		                          "value": "#enSyllable"
		                        }
		                      }
		                    }
		                  }
		                },
		                {
		                  "constant_score": {
		                    "query": {
		                      "prefix": {
		                        "titleWapSyllable": {
		                          "value": "#syllable"
		                        }
		                      }
		                    }
		                  }
		                },
		                {
		                  "constant_score": {
		                    "query": {
		                      "prefix": {
		                        "titleWapFirst": {
		                          "value": "#first"
		                        }
		                      }
		                    }
		                  }
		                },
		                {
		                  "constant_score": {
		                    "query": {
		                      "prefix": {
		                        "titleWapEnSyllable": {
		                          "value": "#enSyllable"
		                        }
		                      }
		                    }
		                  }
		                },
		                {
		                  "constant_score": {
		                    "query": {
		                      "prefix": {
		                        "mainArtistNmBasketSyllable": {
		                          "value": "#syllable"
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
                    "svcAvailFlg": "1"
                  }
                },
                {
                  "terms": {
                    "flacFlg": ["1", "2", "3"]
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
            "prefix": {
              "titleWebListSyllable": "#syllable"
            }
          },
          "weight": 3.0
        },
        {
          "filter": {
            "prefix": {
              "titleWebListFirst": "#first"
            }
          },
          "weight": 2.0
        },
        {
          "filter": {
            "prefix": {
              "titleWebListEnSyllable": "#enSyllable"
            }
          },
          "weight": 2.0
        },
        {
          "filter": {
            "prefix": {
              "albumRepNmSyllable": "#syllable"
            }
          },
          "weight": 3.0
        },
        {
          "filter": {
            "prefix": {
              "albumRepNmFirst": "#first"
            }
          },
          "weight": 2.0
        },
        {
          "filter": {
            "prefix": {
              "albumRepNmEnSyllable": "#enSyllable"
            }
          },
          "weight": 2.0
        },
        {
          "filter": {
            "prefix": {
              "titleWapSyllable": "#syllable"
            }
          },
          "weight": 3.0
        },
        {
          "filter": {
            "prefix": {
              "titleWapFirst": "#first"
            }
          },
          "weight": 2.0
        },
        {
          "filter": {
            "prefix": {
              "titleWapEnSyllable": "#enSyllable"
            }
          },
          "weight": 2.0
        },
        {
          "filter": {
            "prefix": {
              "mainArtistNmBasketSyllable": "#syllable"
            }
          },
          "weight": 1.5
        }
      ],
      "score_mode": "max"
    }
  },
  "sort": [
    "_score",
    {
      "sortHit": {
        "order": "desc"
      }
    },
    {
      "sortAlbum": {
        "order": "asc"
      }
    }
  ],
  "from": 0,
  "size": "#size"
}
