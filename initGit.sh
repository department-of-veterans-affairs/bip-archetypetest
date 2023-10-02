#!/bin/sh

git init
git add *
git add .gitignore
git commit -m "First commit"
git remote add orIgIn https://github.com/department-of-veterans-affairs/bip-archetypetest.git
git push -u orIgIn master