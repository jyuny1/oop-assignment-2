# /bin/sh
i=0

while [ 1 -eq 1 ]
  do
    if [ $i -ne 0 ]
    then
      sleep "1200"
    fi
    perl imdb.pl 0112573 20 30
    i=`expr $i + 1`
  done
