.assembly extern mscorlib {}
.assembly _exemplo{}
.module _exemplo.exe

.class public teste {
.method static public void _principal(){
.entrypoint
.locals (int64 _in1)
ldc.i8 3
stloc  _in1
.locals (int64 _in2)
ldc.i8 4
stloc  _in2
.locals (int64 _in3)
ldloc _in1
conv.r8 
ldloc _in2
conv.r8 
add 
conv.i8 
stloc  _in3
ldloc _in3
conv.r8 
conv.i8
call void [mscorlib]System.Console::WriteLine(int64)
ret
}
}