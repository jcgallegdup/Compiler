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

program: function+;

function: functionDecl functionBody;

functionDecl: compoundType identifier '(' formalParams ')';

functionBody: '{' varDecl* statement* '}';

formalParams
        : compoundType identifier moreFormals*
        |
    ;

moreFormals: ',' compoundType identifier;

varDecl: compoundType identifier ';';

statement options {backtrack=true;}
        : ';'
        | expr ';'
        | PRINT expr ';'
        | PRINTLN expr ';'
        | RETURN expr? ';'
        | ifStatement
        | WHILE '(' expr ')' block
        | ID EQUALS expr ';'
        | ID '[' expr ']' EQUALS expr ';'
        ;

ifStatement options {backtrack=true;}
        : IF '(' expr ')' block ELSE block
        | IF '(' expr ')' block
    ;

block: '{' statement* '}';

expr
        : exprAtom
    ;

exprAtom
        : ID '(' exprList ')'
        | ID '[' expr ']'
        | ID
        | literal
        | '(' expr ')'
    ;

exprList
        : expr exprMore*
        |
    ;

exprMore: ',' expr;

identifier: ID;

compoundType
        : TYPE
        | TYPE '[' INTCONSTANT ']'
    ;

literal
        : 'true'
        | 'false'
        | INTCONSTANT
        | FLOATCONSTANT
        | CHARCONSTANT
        | STRINGCONSTANT
    ;

/* Lexer */
     
IF: 'if';

ELSE: 'else';

WHILE: 'while';

PRINT: 'print';

PRINTLN: 'println';

RETURN: 'return';

EQUALS: '=';

TYPE
        : 'int'
        | 'float'
        | 'char'
        | 'string'
        | 'boolean'
        | 'void'
    ;

ID: ('a'..'z' | 'A'..'Z' | '_')('a'..'z' | 'A'..'Z' | '_' | DIGIT)*;

CHARCONSTANT: '\''('a'..'z' | 'A'..'Z' | '_' | DIGIT)'\'';

STRINGCONSTANT: '\"'('a'..'z' | 'A'..'Z' | '_' | DIGIT)+'\"';

INTCONSTANT: DIGIT+;

FLOATCONSTANT: DIGIT+ '.' DIGIT+;

/* These two lines match whitespace and comments 
 * and ignore them.
 * You want to leave these as last in the file.  
 * Add new lexical rules above 
 */
WS: ( '\t' | ' ' | ('\r' | '\n') )+ { $channel = HIDDEN;};

COMMENT: '//' ~('\r' | '\n')* ('\r' | '\n') { $channel = HIDDEN;};

fragment DIGIT: '0'..'9';
