find tests/accept/ -type f -name "*.ul" -exec "sh" "run_ul_test.sh" "{}" \; | egrep -o "[a-zA-Z0-9_]*\.ul"
