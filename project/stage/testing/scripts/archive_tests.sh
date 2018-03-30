target_test_dir=$1

tests=$(find $target_test_dir -type f -name "*.ul")
for filename in $tests
do
    # hides ".ul" extension, so that run_ul_tests script ignores
    mv $filename "$filename.archived"
done

numTests="$(echo $tests | wc -w)"
printf "\n"
echo "Archived $numTests tests"
