#!/bin/sh

# Just an example... Be sure to keep the second parametr in quotes - if it contains more words.

echo '{"key":"'$1'", "message": "'$2'"}' |
curl -d @- --header "Content-Type:application/json" http://localhost:8080/api/messages;
