.assembly extern mscorlib {}
.assembly _exemplo{}
.module _exemplo.exe

.class public teste {
.method static public void _principal(){
.entrypoint
ldstr "Ola mundo"
call void [mscorlib]System.Console::WriteLine(string)
ret
}
}