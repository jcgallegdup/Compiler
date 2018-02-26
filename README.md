# Compiler
### Set up
- set the classpath: `source project/setclasspath`

### Semantic Testing
Test the semantic analysis phase of the compiler: test the compiler's enforcement of type rules.
- move to `stage` directory: `cd project/stage`
- build project: `make`

#### Run All Existing Tests
- optionally specify whether to run accept or reject inputs:
  - `bash run_all_tests.sh [accept|reject]`
  - if you specify `accept`, each test's output will be written to an individual file in the `results` directory
- when a test is rejected, an error message is printed to the terminal
- when a test is accepted, the output is a pretty print version of the source code

#### Create New Tests
- add tests by creating a `<test_name>.ul` file anywhere within the `project/stage/tests` directory
  - include in `accept` or `reject` directory, depending on whether the test input is in the language
  - e.g. `echo "void main() {}" >> tests/accept/simple_program.ul`


### Parse Testing
Test the tokenization phase of the compiler: test that the correct grammar is accepted.
- move to `stage` directory: `cd project/stage`
- create a results directory: `mkdir results`
  - output of accept tests will be written to individual files in this directory
- build project: `make`

#### Run All Existing Tests
- optionally specify whether to run accept or reject inputs:
  - `bash run_all_tests.sh [accept|reject]`
  - if you specify `accept`, each test's output will be written to an individual file in the `results` directory
- when a test is rejected, an error message is printed to the terminal
- when a test is accepted, the output is a pretty print version of the source code

#### Create New Tests
- add tests by creating a `<test_name>.ul` file anywhere within the `project/stage/tests` directory
  - include in `accept` or `reject` directory, depending on whether the test input is in the language
  - e.g. `echo "void main() {}" >> tests/accept/simple_program.ul`

#### Run Test Individually
-  e.g. `java Compiler tests/accept/simple_program.ul`

### Notes
Project for UVic's 4th year Compiler Construction course.
