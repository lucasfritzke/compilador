.assembly extern mscorlib {}
.assembly _exemplo{}
.module _exemplo.exe

.class public teste {
.method static public void _principal(){
.entrypoint
ldc.i4.1
ldc.i4.0
and
call void [mscorlib]System.Console::WriteLine(bool)
ret
}
}