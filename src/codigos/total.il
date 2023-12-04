.assembly extern mscorlib {}
.assembly _exemplo{}
.module _exemplo.exe

.class public teste {
.method static public void _principal(){
.entrypoint
.locals (int64 _inum2)
.locals (int64 _inum3)
.locals (int64 _inum4)
.locals (int64 _inum5)
ldc.i8 7
conv.r8 
conv.i8
call void [mscorlib]System.Console::WriteLine(int64)
ret
}
}