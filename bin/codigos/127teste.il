.assembly extern mscorlib {}
.assembly _exemplo{}
.module _exemplo.exe

.class public teste {
.method static public void _principal(){
.entrypoint
.locals (int64 _inum1)
.locals (string _stext1)
.locals (string _stext2)
.locals (float64 _fnum)
.locals (bool _bfalse)
.locals (bool _btrue)
ldc.i8 25
conv.r8
conv.i8 
stloc  _inum1
ldstr "Lucas"
dup 
stloc  _stext1
stloc  _stext2
ldc.r8 1.235
stloc  _fnum
ldc.i4.0
stloc  _bfalse
ldc.i4.1
stloc  _btrue
ldc.i8 25
conv.r8 
conv.i8
call void [mscorlib]System.Console::WriteLine(int64)
ldstr "Lucas"
call void [mscorlib]System.Console::WriteLine(string)
ldstr "Lucas"
call void [mscorlib]System.Console::WriteLine(string)
ldc.r8 1.235
call void [mscorlib]System.Console::WriteLine(float64)
ldc.i4.0
call void [mscorlib]System.Console::WriteLine(bool)
ldc.i4.1
call void [mscorlib]System.Console::WriteLine(bool)
ret
}
}