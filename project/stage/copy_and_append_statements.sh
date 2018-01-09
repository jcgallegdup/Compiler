path_to_original_file=$1
path_to_new_file_w_name=$2
remove=$3
statements=$4
touch $path_to_new_file_w_name; cat $path_to_original_file | sed s"/$remove/$statements}/" >> $path_to_new_file_w_name
