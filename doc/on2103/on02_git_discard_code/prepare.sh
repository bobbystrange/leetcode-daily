#!/usr/bin/env bash

base_dir=$HOME/data/git
mkdir -p $base_dir
rm -rf remote local

mkdir -p $base_dir/remote && cd $base_dir/remote
git init --bare

cd $base_dir
git clone file://$base_dir/remote local
cd local

# commit c1
touch c1
git add --all && git commit -m 'c1' && git push -u origin master

# commit c2 to master
git branch A && git branch B
touch c2
git add --all && git commit -m 'c2' && git push -u origin master

# commit c3 to A
git switch A
touch c3
git add --all && git commit -m 'c3' && git push -u origin A

# commit c4 to B
git switch B
touch c4
git add --all && git commit -m 'c4' && git push -u origin B

# commit c5 to B
touch c5
git add --all && git commit -m 'c5' && git push -u origin B

# commit c6 to master
git switch master
touch c6
git add --all && git commit -m 'c6' && git push -u origin master
