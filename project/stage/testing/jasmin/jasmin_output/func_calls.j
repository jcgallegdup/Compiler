.source func_calls.ir
.class public classname
.super java/lang/Object

.method public static __main()V
	.limit locals 4
	.var 0 is T0  I from L_0 to L_1
	.var 1 is T1  Z from L_0 to L_1
	.var 2 is T2  I from L_0 to L_1
	.var 3 is T3  Z from L_0 to L_1
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
.line 8
;		    T2 := CALL gimmeInt ();
	invokestatic classname/gimmeInt()I
	istore 2
.line 9
;		    T0 := T2;
	iload 2
	istore 0
.line 10
;		    T3 := CALL gimmeBool ();
	invokestatic classname/gimmeBool()Z
	istore 3
.line 11
;		    T1 := T3;
	iload 3
	istore 1
.line 12
;		    RETURN ;
	return
L_1:
.end method

.method public static gimmeInt()I
	.limit locals 2
	.var 0 is T0  I from L_2 to L_3
	.var 1 is T1  I from L_2 to L_3
	.limit stack 16
L_2:
	ldc 0
	istore 0
	ldc 0
	istore 1
.line 18
;		    T1 := 1;
	ldc 1
	istore 1
.line 19
;		    T0 := T1;
	iload 1
	istore 0
.line 20
;		    RETURN T0;
	iload 0
	ireturn
L_3:
.end method

.method public static gimmeBool()Z
	.limit locals 1
	.var 0 is T0  Z from L_4 to L_5
	.limit stack 16
L_4:
	ldc 0
	istore 0
.line 25
;		    T0 := FALSE;
	ldc 0
	istore 0
.line 26
;		    RETURN T0;
	iload 0
	ireturn
L_5:
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
