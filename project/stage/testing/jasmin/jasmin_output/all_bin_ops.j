.source all_bin_ops.ir
.class public classname
.super java/lang/Object

.method public static __main()V
	.limit locals 18
	.var 0 is T0  I from L_0 to L_1
	.var 1 is T1  I from L_0 to L_1
	.var 2 is T2  I from L_0 to L_1
	.var 3 is T3  I from L_0 to L_1
	.var 4 is T4  I from L_0 to L_1
	.var 5 is T5  I from L_0 to L_1
	.var 6 is T6  Z from L_0 to L_1
	.var 7 is T7  Z from L_0 to L_1
	.var 8 is T8  Z from L_0 to L_1
	.var 9 is T9  F from L_0 to L_1
	.var 10 is T10  F from L_0 to L_1
	.var 11 is T11  F from L_0 to L_1
	.var 12 is T12  I from L_0 to L_1
	.var 13 is T13  I from L_0 to L_1
	.var 14 is T14  Z from L_0 to L_1
	.var 15 is T15  I from L_0 to L_1
	.var 16 is T16  I from L_0 to L_1
	.var 17 is T17  I from L_0 to L_1
	.limit stack 16
L_0:
	ldc 0
	istore 0
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
	ldc 0
	istore 8
	ldc 0.0
	fstore 9
	ldc 0.0
	fstore 10
	ldc 0.0
	fstore 11
	ldc 0
	istore 12
	ldc 0
	istore 13
	ldc 0
	istore 14
	ldc 0
	istore 15
	ldc 0
	istore 16
	ldc 0
	istore 17
.line 22
;		    T0 := 1;
	ldc 1
	istore 0
.line 23
;		    T1 := 2;
	ldc 2
	istore 1
.line 24
;		    T2 := T0 I+ T1;
	iload 0
	iload 1
	iadd
	istore 2
.line 25
;		    T3 := 1;
	ldc 1
	istore 3
.line 26
;		    T4 := 2;
	ldc 2
	istore 4
.line 27
;		    T5 := T3 I- T4;
	iload 3
	iload 4
	isub
	istore 5
.line 28
;		    T6 := FALSE;
	ldc 0
	istore 6
.line 29
;		    T7 := TRUE;
	ldc 1
	istore 7
.line 30
;		    T8 := T6 Z== T7;
	iload 6
	iload 7
	ixor
	ldc 1
	ixor
	istore 8
.line 31
;		    T9 := 1.0;
	ldc 1.000000
	fstore 9
.line 32
;		    T10 := 2.0;
	ldc 2.000000
	fstore 10
.line 33
;		    T11 := T9 F+ T10;
	fload 9
	fload 10
	fadd
	fstore 11
.line 34
;		    T12 := 1;
	ldc 1
	istore 12
.line 35
;		    T13 := 2;
	ldc 2
	istore 13
.line 36
;		    T14 := T12 I< T13;
	iload 12
	iload 13
	isub
	iflt L_8
	ldc 0
	goto L_9
L_8:
	ldc 1
L_9:
	istore 14
.line 37
;		    T15 := 1;
	ldc 1
	istore 15
.line 38
;		    T16 := 2;
	ldc 2
	istore 16
.line 39
;		    T17 := T15 I* T16;
	iload 15
	iload 16
	imul
	istore 17
.line 40
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
