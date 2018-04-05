# Copies specified IR file over to Uvic Linux server and runs it on the JVM.
#
# Peforms necessary intermediate steps such as:
#   - generating a jasmin input file from IR file
#   - generating a JVM class file from jasmin file
#
# Params:
#   ir_input_file: path to IR file

ir_input_file=$1
username="jcgalleg"

# Assumes the existence/location of various files:
# - ir_files
# - ./codegen containing codegen exe, can just be a soft/symbolic link
# - and more! :-) (e.g. setclasspath script)

# TODO: assert that we're in ~/code/.../stage dir

# stop executing script on error
set -e

# copy IR files over
scp -r $ir_input_file linuxUvic:/home/$username/compiler-construction/compiler/project/stage/$ir_input_file

# log into remote server to run commands
ssh linuxUvic /bin/bash << RUNTHESEBOYS

# stop executing script on error
set -e

# set up
cd /home/$username/compiler-construction/compiler/project/stage
source ../setclasspath

echo '* Generating jasmin file "out.j"'
./codegen/codegen --file=$ir_input_file > out.j

echo '* Generating class file'
java jasmin.Main out.j
cat out.j

echo '* Running program!'
# TODO: parametrize classname instead of hardcoding
java classname

RUNTHESEBOYS
