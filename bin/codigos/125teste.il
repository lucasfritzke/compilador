.assembly extern mscorlib {}
.assembly _exemplo{}
.module _exemplo.exe

.class public teste {
.method static public void _principal(){
.entrypoint
.locals (int64 _inum)
.locals (int64 _ilado)
.locals (int64 _iarea)
ldc.i8 2
conv.r8
ldc.i8 2
conv.r8
add 
conv.i8 
stloc _inum
ldc.i8 3
conv.r8 
conv.i8
call void [mscorlib]System.Console::WriteLine(int64)
ldc.i8 3
conv.r8 
conv.i8
call void [mscorlib]System.Console::WriteLine(int64)
ret
}
}