grammar ulNoActions;
                
@members
{
protected void mismatch (IntStream input, int ttype, BitSet follow)
        throws RecognitionException
{
        throw new MismatchedTokenException(ttype, input);
}
public void recoverFromMismatchedSet (IntStream input,
                                      RecognitionException e,
                                      BitSet follow)
        throws RecognitionException
{
        reportError(e);
        throw e;
}
}

@rulecatch {
        catch (RecognitionException ex) {
                reportError(ex);
                throw ex;
        }
}

/*
 * This is a subset of the ulGrammar to show you how
 * to make new production rules.
 * You will need to:
 *  - change functionBody to include variable declarations and statements 
 */

program : function+
    ;

function: functionDecl functionBody
    ;

functionDecl
	: compoundType identifier '(' formalParams ')'
    ;

functionBody: '{' varDecl* statement* '}'
    ;

formalParams
	:	compoundType identifier moreFormals*
	|
    ;

moreFormals
	:	',' compoundType identifier
	;

varDecl :	compoundType identifier ';'
	;

statement
	:	 ';'
	|	 PRINT expr ';'
	;

expr
	: ID
	| LITERAL
	;

identifier: ID
    ;

// TODO: validate order of rules here
compoundType
	: TYPE
	| TYPE '[' INTCONSTANT ']'
    ;

/* Lexer */
     
IF    : 'if'
    ;

PRINT	:'print';

/*
 * FIXME:
 * Change this to match the specification for identifier
 *
*/
/* Fixme: add the other types here */
TYPE    : 'int'
	| 'float'
	| 'char'
	| 'string'
	| 'boolean'
	| 'void'
    ;

LITERAL	: 'true'
	| 'false'
    ;

ID    : ('a'..'z' | 'A'..'Z' | '_')('a'..'z' | 'A'..'Z' | '_' | DIGIT)*
    ;

INTCONSTANT
	: DIGIT+
    ;


/* These two lines match whitespace and comments 
 * and ignore them.
 * You want to leave these as last in the file.  
 * Add new lexical rules above 
 */
WS      : ( '\t' | ' ' | ('\r' | '\n') )+ { $channel = HIDDEN;}
        ;

COMMENT : '//' ~('\r' | '\n')* ('\r' | '\n') { $channel = HIDDEN;}
        ;

fragment DIGIT
    :	'0'..'9'
    ;
