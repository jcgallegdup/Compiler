# TODO support "-a" and -r" flags to specify "accept" and "reject" tests
# sed removes the extra frontslash in path
tests=$(find tests/$1 -type f -name "*.ul" | sed 's/\/\//\//')
# TODO print seprator between accept and reject tests
for filename in $tests
do
    # TODO ensure that Compiler.class exists in current dir
    echo "* Running $filename"
    output=$(java Compiler $filename)
    echo $output
    echo "\n\n"
done

numTests="$(echo $tests | wc -w)"
echo "\nRan $numTests tests:"
