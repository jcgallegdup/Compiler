# TODO support "-a" and -r" flags to specify "accept" and "reject" tests
# sed removes the extra frontslash in path
tests=$(find tests/$1 -type f -name "*.ul" | sed 's/\/\//\//')
# TODO print seprator between accept and reject tests
for pathToTest in $tests
do
    # TODO ensure that Compiler.class exists in current dir
    echo "* Running $pathToTest"
    testFileName=$(echo $pathToTest | egrep -o "[a-zA-Z_0-9]*\.ul")
    java Compiler $pathToTest >> "results/$testFileName.out"
    echo $output
    echo "\n\n"
done

numTests="$(echo $tests | wc -w)"
echo "\nRan $numTests tests:"
