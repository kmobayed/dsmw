#!/bin/bash

mkdir $1
cd $1
git init 
touch file
git add file
echo "1 master" >> file
git commit -a -m"master commit"
sleep 3
echo "2 master" >> file
git commit -a -m"master commit"
sleep 3
git branch site1
git checkout site1
echo "3 site1" >> file
git commit -a -m"site1 commit"
sleep 3
echo "4 site1" >> file
git commit -a -m"site1 commit"
sleep 3
git checkout master
echo "3 master" >> file
git commit -a -m"master commit"
sleep 3
echo "4 master" >> file
git commit -a -m"master commit"
git branch site2
git checkout site2
echo "5 site2" >> file
git commit -a -m"site2 commit"
sleep 3
echo "6 site2" >> file
git commit -a -m"site2 commit"
sleep 3
git checkout master
git merge --no-ff site2
echo "7 master" >> file
git commit -a -m"master commit"
sleep 3
echo "8 master" >> file
git commit -a -m"master commit"
sleep 3
git merge --no-ff site1
git mergetool
git commit
nl -n rz file > file2
mv -f file2 file 
git commit -a -m"master commit"
