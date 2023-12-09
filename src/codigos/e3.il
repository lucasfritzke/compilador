.assembly extern mscorlib {}
.assembly _exemplo{}
.module _exemplo.exe

.class public teste {
.method static public void _principal(){
.entrypoint
.locals (int64 _icontador)
.locals (float64 _fnumero)
.locals (float64 _fresultado)
call string [mscorlib]System.Console::ReadLine() 
call int64 [mscorlib]System.Int64::Parse(string) 
stloc _icontador
call string [mscorlib]System.Console::ReadLine() 
call float64 [mscorlib]System.Double::Parse(string) 
stloc _fnumero
l1: 
ldloc _icontador
conv.r8 
ldc.i8 0
conv.r8
cgt
brfalse l2
ldloc _fnumero
ldloc _icontador
conv.r8 
mul 
stloc  _fresultado
ldloc _fresultado
call void [mscorlib]System.Console::WriteLine(float64)
ldstr "   "
call void [mscorlib]System.Console::WriteLine(string)
ldloc _icontador
conv.r8 
ldc.i8 1
conv.r8
sub 
conv.i8 
stloc  _icontador
br l1
l2: 
ret
}
}