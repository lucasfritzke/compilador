.assembly extern mscorlib {}
.assembly _exemplo{}
.module _exemplo.exe

.class public teste {
.method static public void _principal(){
.entrypoint
ldc.i8 29
conv.r8
ldc.i8 -1
conv.r8
mul
ldc.i8 1
conv.r8
sub 
conv.i8
call void [mscorlib]System.Console::WriteLine(int64)
ret
}
}