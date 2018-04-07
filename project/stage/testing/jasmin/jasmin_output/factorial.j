.source factorial.ir
.class public classname
.super java/lang/Object

.method public static factorial(I)I
	.limit locals 8
	.var 0 is T0  I from L_0 to L_1
	.var 1 is T1  I from L_0 to L_1
	.var 2 is T2  Z from L_0 to L_1
	.var 3 is T3  I from L_0 to L_1
	.var 4 is T4  I from L_0 to L_1
	.var 5 is T5  I from L_0 to L_1
	.var 6 is T6  I from L_0 to L_1
	.var 7 is T7  I from L_0 to L_1
	.limit stack 16
L_0:
	ldc 0
	istore 1
	ldc 0
	istore 2
	ldc 0
	istore 3
	ldc 0
	istore 4
	ldc 0
	istore 5
	ldc 0
	istore 6
	ldc 0
	istore 7
.line 12
;		    T1 := 1;
	ldc 1
	istore 1
.line 13
;		    T2 := T0 I== T1;
	iload 0
	iload 1
	isub
	ifeq L_2
	ldc 0
	goto L_3
L_2:
	ldc 1
L_3:
	istore 2
.line 14
;		    IF T2 GOTO L0;
	iload 2
	ifne L0
.line 15
;		    T3 := 1;
	ldc 1
	istore 3
.line 16
;		    T4 := T0 I- T3;
	iload 0
	iload 3
	isub
	istore 4
.line 17
;		    T5 := CALL factorial (T4);
	iload 4
	invokestatic classname/factorial(I)I
	istore 5
.line 18
;		    T6 := T0 I* T5;
	iload 0
	iload 5
	imul
	istore 6
.line 19
;		    RETURN T6;
	iload 6
	ireturn
.line 20
;		    GOTO L1;
	goto L1
.line 21
;		    L0:;
L0:
.line 22
;		    T7 := 1;
	ldc 1
	istore 7
.line 23
;		    RETURN T7;
	iload 7
	ireturn
.line 24
;		    L1:;
L1:
	ldc 0
	ireturn
L_1:
.end method

.method public static __main()V
	.limit locals 3
	.var 0 is T0  Ljava/lang/String; from L_8 to L_9
	.var 1 is T1  I from L_8 to L_9
	.var 2 is T2  I from L_8 to L_9
	.limit stack 16
L_8:
	aconst_null
	astore 0
	ldc 0
	istore 1
	ldc 0
	istore 2
.line 31
;		    T0 := "The factorial of 8 is ";
	ldc "The factorial of 8 is "
	astore 0
.line 32
;		    PRINTU T0;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	aload 0
	invokevirtual java/io/PrintStream/print(Ljava/lang/String;)V
.line 33
;		    T1 := 8;
	ldc 8
	istore 1
.line 34
;		    T2 := CALL factorial (T1);
	iload 1
	invokestatic classname/factorial(I)I
	istore 2
.line 35
;		    PRINTLNI T2;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	iload 2
	invokevirtual java/io/PrintStream/println(I)V
.line 36
;		    RETURN ;
	return
L_9:
.end method

;--------------------------------------------;
;                                            ;
; Boilerplate                                ;
;                                            ;
;--------------------------------------------;

.method public static main([Ljava/lang/String;)V
	; set limits used by this method
	.limit locals 1
	.limit stack 4
	invokestatic classname/__main()V
	return
.end method

; standard initializer
.method public <init>()V
	aload_0
	invokenonvirtual java/lang/Object/<init>()V
	return
.end method
