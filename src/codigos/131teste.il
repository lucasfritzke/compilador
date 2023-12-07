.assembly extern mscorlib {}
.assembly _exemplo{}
.module _exemplo.exe

.class public teste {
.method static public void _principal(){
.entrypoint
.locals (int64 _ilado)
ldstr
call  void  [mscorlib]System.Console::Write(string)
call int64 [mscorlib]System.Console::ReadLine() 
conv.i8 
stloc _ilado
ret
}
}