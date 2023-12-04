.assembly extern mscorlib {}
.assembly _exemplo{}
.module _exemplo.exe

.class public teste {
.method static public void _principal(){
.entrypoint
ldc.i4.1
brfalse l1
ldstr "Olá mundo"
call void [mscorlib]System.Console::WriteLine(string)
l1: 
ret
}
}