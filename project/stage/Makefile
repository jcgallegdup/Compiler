#
GNAME= ulNoActions
GSRC= $(GNAME).g

all: grammar compiler

grammar: $(GSRCS)
	java org.antlr.Tool -fo . $(GSRC) 

compiler:
	javac AST/*.java Type/*.java *.java

clean:
	rm AST/*.class Type/*.class *.class $(GNAME)*.java $(GNAME)__.g $(GNAME).tokens


 
