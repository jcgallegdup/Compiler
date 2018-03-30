# TODO ensure that Compiler.class exists in current dir
# TODO support "-a" and -r" flags to specify "accept" and "reject" tests
testDir=$1
outputDir="ir_files"

# sed removes the extra frontslash in path
tests=$(find $testDir -type f -name "*.ul" | sed 's/\/\//\//')

# TODO print seprator between accept and reject tests
for pathToTest in $tests
do
    echo "* Running $pathToTest"

    testFileName=$(echo $pathToTest | egrep -o "[a-zA-Z_0-9]*\.ul")
    output=$(java Compiler $pathToTest)
    echo "$output" > "$outputDir/$testFileName.ir"
done

numTests="$(echo $tests | wc -w)"
printf "\n"
echo "Ran $numTests tests:"

#sh test_ir_output.sh $outputDir
