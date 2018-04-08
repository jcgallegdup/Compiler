.class classname
.super java/lang/Object

.method public static __main()V
    .limit locals 5
    .var 0 is T0 Z
    .var 1 is T1 Z
    .var 2 is T2 Z
    .var 3 is T3 Z
    .var 4 is T4 Ljava/lang/String;
    .limit stack 20

    ; T1 := TRUE;
    ldc 1
    istore 1
    
    ; T0 := T1;
    iload 1
    istore 0
    
    ; L0:;
    L0:
    
    ; T2 := Z! T0;
    iload 0
    ldc 1
    ixor
    istore 2
    
    ; IF T2 GOTO L1;
    iload 2
    ifne L1
    
    ; T3 := FALSE;
    ldc 0
    istore 3
    
    ; T0 := T3;
    iload 3
    istore 0
    
    ; T4 := "Exit after one iteration";
    ldc "Exit after one iteration"
    astore 4
    
    ; PRINTLNU T4;
    getstatic java/lang/System/out Ljava/io/PrintStream;
    aload 4
    invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
    
    ; GOTO L0;
    goto L0
    
    ; L1:;
    L1:
    
    ; RETURN ;
    return
    
.end method

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

