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
		                        "songRepNmSyllable": {
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
		                        "songRepNmFirst": {
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
		                        "songRepNmEnSyllable": {
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
		                        "artistNmBasketSyllable": {
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
		                        "artistNmBasketEnSyllable": {
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
		                        "albumTitleWebListSyllable": {
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
		                        "albumTitleWebListEnSyllable": {
		                          "value": "#enSyllable"
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
                    "svcAvailFlg": "0"
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
              "songRepNmSyllable": "#syllable"
            }
          },
          "weight": 3.0
        },
        {
          "filter": {
            "prefix": {
              "songRepNmFirst": "#first"
            }
          },
          "weight": 2.0
        },
        {
          "filter": {
            "prefix": {
              "songRepNmEnSyllable": "#enSyllable"
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
              "artistNmBasketSyllable": "#syllable"
            }
          },
          "weight": 1.5
        },
        {
          "filter": {
            "prefix": {
              "artistNmBasketEnSyllable": "#enSyllable"
            }
          },
          "weight": 1.5
        },
        {
          "filter": {
            "prefix": {
              "albumTitleWebListSyllable": "#syllable"
            }
          },
          "weight": 2.0
        },        
        {
          "filter": {
            "prefix": {
              "albumTitleWebListEnSyllable": "#enSyllable"
            }
          },
          "weight": 2.0
        }
      ],
      "score_mode": "max"
    }
  },
  "sort": [
    "_score",
    {
      "songPriority": {
        "order": "desc"
      }
    },
    {
      "sortHit": {
        "order": "desc"
      }
    },
    {
      "sortSong": {
        "order": "asc"
      }
    }
  ],
  "from": 0,
  "size": "#size"
}
