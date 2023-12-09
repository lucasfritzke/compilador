.assembly extern mscorlib {}
.assembly _exemplo{}
.module _exemplo.exe

.class public teste {
.method static public void _principal(){
.entrypoint
.locals (int64 _inum)
.locals (float64 _fnum)
ldc.i8 1
conv.r8
conv.i8 
stloc  _inum
ldloc _inum
conv.r8 
ldc.r8 2.255
add 
conv.i8 
stloc  _inum
ldloc _inum
conv.r8 
conv.i8
call void [mscorlib]System.Console::WriteLine(int64)
ret
}
}