# Compiler
### Set up
- set the classpath: `source project/setclasspath`
### Testing
#### Run Existing Tests
- optionally specify whether to run accept or reject inputs:
  - `sh project/stage/run_all_tests.sh [accept|reject]`
#### Create New Tests
- add tests by creating a `<test_name>.ul` file anywhere within the `project/stage/tests` directory
  - include in `accept` or `reject` directory, depending on whether the test input is in the language
  - e.g. `echo "void main() {}" >> project/stage/tests/accept/simple_program.ul`
### Notes
Project for UVic's 4th year Compiler Construction course.
