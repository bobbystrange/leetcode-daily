#!/usr/bin/env bash

base_dir=$HOME/data/git
mkdir -p $base_dir
rm -rf local remote

mkdir -p $base_dir/remote && cd $base_dir/remote
git init --bare

cd $base_dir
git clone file://$base_dir/remote local
cd local

# commit c1
echo 'few red
no white
many black' > hair
echo '--- --- ---
---     ---
--- --- ---' > eye
touch lip
git add --all && git commit -m 'c1' && git push -u origin master

# commit c2 to master
git branch A && git branch B
echo 'warm' >> lip
rm hair
echo '---     ---
--- --- ---
--- --- ---' > eye
git add --all && git commit -m 'c2' && git push -u origin master

# commit c3 to A
git switch A
rm lip
echo '
---     ---
--- --- ---
---     ---' >> eye
echo 'many white
few black
very sparse' > hair
git add --all && git commit -m 'c3' && git push -u origin A

# commit c4 to B
git switch B
echo "accompany
guard" > hand
echo '
a bit sparse' >> hair
git add --all && git commit -m 'c4' && git push -u origin B

# commit c5 to B
rm hair
echo '
--- --- ---' >> eye
git add --all && git commit -m 'c5' && git push -u origin B

# commit c6 to master
git switch master
mv lip lips
echo 'shiny
fascinated' >> lips
echo '
--- --- ---
--- --- ---
--- --- ---' > eye
git add --all && git commit -m 'c6' && git push -u origin master
