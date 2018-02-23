#!/bin/bash

git submodule init
git submodule update --remote
cd protocol/ && ./install.sh && cd - &> /dev/null
