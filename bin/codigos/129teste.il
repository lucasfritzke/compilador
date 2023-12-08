.assembly extern mscorlib {}
.assembly _exemplo{}
.module _exemplo.exe

.class public teste {
.method static public void _principal(){
.entrypoint
.locals (string _snome)
ldstr "Lucas"
stloc  _snome
ldloc _snome
call void [mscorlib]System.Console::WriteLine(string)
ret
}
}