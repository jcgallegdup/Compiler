# Copies specified Jasmin file over to Uvic Linux server and runs it on the JVM.
#
# Peforms necessary intermediate steps such as:
#   - generating a JVM class file from jasmin file
#
# Params:
#   jasmin_input_file: path to jasmin file
#
# NOTE: Assumes the existence/location of the setclasspath script.

jasmin_input_filepath=$1
# parse out filename (has an effect only if relative path was supplied)
jasmin_input_file=$(echo $jasmin_input_filepath | egrep -o "[a-zA-Z_0-9]*\.j")
username="jcgalleg"

# TODO: assert that we're in ~/code/.../stage dir

# stop executing script on error
set -e

# copy IR files over
scp -r $jasmin_input_file linuxUvic:/home/$username/compiler-construction/compiler/project/stage/$jasmin_input_file

# log into remote server to run commands
ssh linuxUvic /bin/bash << RUNTHESEBOYS

# stop executing script on error
set -e

# set up
cd /home/$username/compiler-construction/compiler/project/stage
source ../setclasspath

echo '* Generating class file'
java jasmin.Main $jasmin_input_file

echo '* Running program!'
# TODO: parametrize classname instead of hardcoding
java classname

RUNTHESEBOYS
