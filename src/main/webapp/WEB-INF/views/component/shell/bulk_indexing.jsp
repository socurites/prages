<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
cd git/melon-helloes/src/main/resources/schema/
curl -s -XPOST 'http://localhost:9200/song/_bulk' --data-binary @song.data.json