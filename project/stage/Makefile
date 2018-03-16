#
GNAME= ulNoActions
GSRC= $(GNAME).g

all: grammar compiler

grammar: $(GSRCS)
	java org.antlr.Tool -fo . $(GSRC) 

compiler:
	javac IR/*.java AST/*.java Type/*.java *.java

clean:
	rm IR/*.class AST/*.class Type/*.class *.class $(GNAME)*.java $(GNAME)__.g $(GNAME).tokens


 
