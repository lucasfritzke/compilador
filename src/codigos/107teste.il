.assembly extern mscorlib {}
.assembly _exemplo{}
.module _exemplo.exe

.class public teste {
.method static public void _principal(){
.entrypoint
ldc.i4.0
ldc.i4.1 
xor
call void [mscorlib]System.Console::WriteLine(bool)
ret
}
}