#!/bin/sh -x
#
# generate web site by using Maven and deploy that into
# the java.net CVS repository
# 
ant clean dist
if [ $? != 0 ]; then
  echo ant failed
  exit
fi

cd build/javadoc
cvs "-d:pserver:kohsuke@kohsuke.sfbay:/cvs" -z3 import -ko -W "*.png -k 'b'" -W "*.gif -k 'b'" -m "deploying the new web contents" rngom/www/javadoc site-deployment t`date +%Y%m%d-%H%M%S`
cd ../..
cd ../www
date >> update.html
cvs commit -m "to work around a bug in java.net web updater" update.html
