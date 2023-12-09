.assembly extern mscorlib {}
.assembly _exemplo{}
.module _exemplo.exe

.class public teste {
.method static public void _principal(){
.entrypoint
.locals (int64 _ilado)
.locals (int64 _ilado2)
ldstr "digite um valor para lado: "
call void [mscorlib]System.Console::Write(string)
call string [mscorlib]System.Console::ReadLine() 
call int64 [mscorlib]System.Int64::Parse(string) 
stloc _ilado
call string [mscorlib]System.Console::ReadLine() 
call int64 [mscorlib]System.Int64::Parse(string) 
stloc _ilado2
ldloc _ilado2
conv.r8 
conv.i8
call void [mscorlib]System.Console::WriteLine(int64)
ldloc _ilado
conv.r8 
ldloc _ilado2
conv.r8 
mul 
conv.i8 
stloc  _ilado
ldloc _ilado
conv.r8 
conv.i8
call void [mscorlib]System.Console::WriteLine(int64)
ret
}
}