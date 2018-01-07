#!/usr/bin/env bash
set -e 
for file in tests/reject/*.ul
    do
        java Compiler $file
    done
#java Compiler tests/accept/*.ul
