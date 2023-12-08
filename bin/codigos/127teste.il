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
ldc.i8 2
conv.r8
ldc.i8 1
conv.r8
cgt
stloc  _bfalse
ldc.i4.1
stloc  _btrue
ldloc _inum1
conv.r8 
conv.i8
call void [mscorlib]System.Console::WriteLine(int64)
ldloc _stext1
call void [mscorlib]System.Console::WriteLine(string)
ldloc _stext2
call void [mscorlib]System.Console::WriteLine(string)
ldloc _fnum
call void [mscorlib]System.Console::WriteLine(float64)
ldloc _bfalse
call void [mscorlib]System.Console::WriteLine(bool)
ldloc _btrue
call void [mscorlib]System.Console::WriteLine(bool)
ret
}
}