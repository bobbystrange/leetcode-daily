#!/usr/bin/env bash

base_dir=$HOME/data/git
cd $base_dir/local

# c1 c4 c5
git switch B
# save branch
current_b=$(git log -1 HEAD | grep commit | cut -d' ' -f2)
previous_b=$(git log -2 HEAD | grep commit | tail -1 | cut -d' ' -f2)
git checkout -b BB
# delete origin
git checkout $previous_b
git push --delete origin B
# push a new branch with same name
git branch -D B
git checkout -b B
# B: c1 c4
git push -u origin B
# use old branch
git switch BB
git branch -D B
# B: c1 c4 c5, and A: c1 c3
git branch -m B
# merge A, B: c1 c3 c4 c5
git merge -m "Merge branch 'A' into B" A
git push --set-upstream origin B

