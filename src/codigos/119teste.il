.assembly extern mscorlib {}
.assembly _exemplo{}
.module _exemplo.exe

.class public teste {
.method static public void _principal(){
.entrypoint
ldc.i4.0
brfalse l1
ldstr "Ol� mundo"
call void [mscorlib]System.Console::WriteLine(string)
br l2
l1: 
ldstr "N�o ol� mundo"
call void [mscorlib]System.Console::WriteLine(string)
l2: 
ret
}
}