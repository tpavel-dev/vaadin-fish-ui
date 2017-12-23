find  -maxdepth 2 -type d -name classes -exec rm -r "{}"  \;
find  -maxdepth 2 -type d -name build -exec rm -r "{}"  \;
find  -maxdepth 2 -type d -name '.gradle' -exec rm -r "{}"  \;
