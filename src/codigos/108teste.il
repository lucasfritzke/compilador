.assembly extern mscorlib {}
.assembly _exemplo{}
.module _exemplo.exe

.class public teste {
.method static public void _principal(){
.entrypoint
.locals (bool _btrue)
ldc.i8 10
conv.r8
ldc.i8 1
conv.r8
clt
stloc  _btrue
ldloc _btrue
call void [mscorlib]System.Console::WriteLine(bool)
ret
}
}