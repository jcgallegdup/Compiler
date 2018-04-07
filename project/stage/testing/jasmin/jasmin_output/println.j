.source empty_main.ir
.class public classname
.super java/lang/Object

.method public static __main()V
	.limit locals 1
	.var 0 is T0  Ljava/lang/String; from L_0 to L_1
	.limit stack 16
L_0:
	aconst_null
	astore 0
.line 5
;		    T0 := "Hello world";
	ldc "Hello world"
	astore 0
.line 6
;		    PRINTLNU T0;
	getstatic java/lang/System/out Ljava/io/PrintStream;
	aload 0
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
.line 7
;		    RETURN ;
	return
L_1:
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
