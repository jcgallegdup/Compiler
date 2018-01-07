#!/usr/bin/env bash
set -e 
for file in tests/accept/*.ul
    do
        java Compiler $file
    done
#java Compiler tests/accept/*.ul
