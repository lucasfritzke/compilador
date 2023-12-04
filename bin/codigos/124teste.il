.assembly extern mscorlib {}
.assembly _exemplo{}
.module _exemplo.exe

.class public teste {
.method static public void _principal(){
.entrypoint
.locals (int64 _inum)
ldc.i8 4
conv.r8
conv.i8 
stloc  _inum
.locals (bool _bvar)
ldc.i4.1
stloc  _bvar
l1: 
ldloc _bvar
brfalse l2
ldloc _inum
conv.r8 
conv.i8
call void [mscorlib]System.Console::WriteLine(int64)
ldc.i4.0
stloc  _bvar
br l1
l2: 
ret
}
}