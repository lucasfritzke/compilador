.assembly extern mscorlib {}
.assembly _exemplo{}
.module _exemplo.exe

.class teste {
.method static public void _principal(){
.entrypoint
ldc.i8 10
conv.r8
conv.i8
call void [mscorlib]System.Console::WriteLine(int64)
ldc.r8 10.5
call void [mscorlib]System.Console::WriteLine(float64)
ret
}
}