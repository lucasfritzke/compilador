.assembly extern mscorlib {}
.assembly _exemplo{}
.module _exemplo.exe

.class public teste {
.method static public void _principal(){
.entrypoint
.locals (int64 _in1)
ldc.i8 10
stloc  _in1
.locals (int64 _in2)
ldc.i8 5
stloc  _in2
.locals (int64 _in3)
ldc.i8 4
stloc  _in3
.locals (float64 _fn4)
ldc.r8 0.3
stloc  _fn4
ldloc _in1
conv.r8 
ldloc _in2
conv.r8 
div 
ldloc _in3
conv.r8 
ldloc _fn4
mul 
cgt
call void [mscorlib]System.Console::WriteLine(bool)
ret
}
}