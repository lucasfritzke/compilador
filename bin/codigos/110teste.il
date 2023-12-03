.assembly extern mscorlib {}
.assembly _exemplo{}
.module _exemplo.exe

.class public teste {
.method static public void _principal(){
.entrypoint
ldc.i8 10
conv.r8
ldc.i8 5
conv.r8
add 
conv.i8
call void [mscorlib]System.Console::WriteLine(int64)
ret
}
}