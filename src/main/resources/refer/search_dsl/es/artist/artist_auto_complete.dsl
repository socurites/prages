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
		                        "artistRepNmSyllable": {
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
		                        "artistRepNmFirst": {
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
		                        "artistRepNmEnSyllable": {
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
                    "flacFlg": "Y"
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
              "artistRepNmSyllable": "#syllable"
            }
          },
          "weight": 3.0
        },
        {
          "filter": {
            "prefix": {
              "artistRepNmFirst": "#first"
            }
          },
          "weight": 2.0
        },        
        {
          "filter": {
            "prefix": {
              "artistRepNmEnSyllable": "#enSyllable"
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
  ],
  "from": 0,
  "size": "#size"
}