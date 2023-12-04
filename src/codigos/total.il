.assembly extern mscorlib {}
.assembly _exemplo{}
.module _exemplo.exe

.class public teste {
.method static public void _principal(){
.entrypoint
.locals (int64 _inum2)
.locals (int64 _inum3)
ldc.i8 4
stloc _inum3
ldc.i8 2
conv.r8
conv.i8 
stloc _inum2
.locals (int64 _inum4)
ldc.i8 7
stloc _inum4
.locals (int64 _inum5)
ldc.i8 7
stloc _inum5
ldloc _inum5
conv.r8 
conv.i8
call void [mscorlib]System.Console::WriteLine(int64)
ret
}
}