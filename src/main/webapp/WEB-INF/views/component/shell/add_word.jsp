<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

cd $MECAB_DOWNLOAD_DIR
cd /home/socurites/Runnable/mecab/mecab-ko-dic-2.0.1-20150920

vi ./user-dic/melon_noun.csv
취향저격,,,,NNG,*,T,NNG,*,*,*,*,

/usr/local/libexec/mecab/mecab-dict-index -m model.def -u ./melon_noun.csv -f utf-8 -t utf-8 -a ./user-dic/melon_noun.csv

make clean
make
make install

web-app restart