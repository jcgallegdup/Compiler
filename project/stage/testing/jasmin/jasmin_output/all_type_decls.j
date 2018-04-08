source all_type_decls.ir
.class public classname
.super java/lang/Object

.method public static __main()V
	.limit locals 6
	.var 0 is T0  I from L_0 to L_1
	.var 1 is T1  F from L_0 to L_1
	.var 2 is T2  Z from L_0 to L_1
	.var 3 is T3  Ljava/lang/String; from L_0 to L_1
	.var 4 is T4  C from L_0 to L_1
	.var 5 is T5  [I from L_0 to L_1
	.limit stack 16
L_0:
	ldc 0
	istore 0
	ldc 0.0
	fstore 1
	ldc 0
	istore 2
	aconst_null
	astore 3
	ldc 0
	istore 4
	aconst_null
	astore 5
.line 10
;		    T5 := NEWARRAY I 10;
	ldc 10
	newarray int
	astore 5
.line 11
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
