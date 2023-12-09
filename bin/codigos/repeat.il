.assembly extern mscorlib {}
.assembly _exemplo{}
.module _exemplo.exe

.class public teste {
.method static public void _principal(){
.entrypoint
.locals (int64 _in)
ldc.i8 3
conv.r8
conv.i8 
stloc  _in
.locals (int64 _in1)
ldc.i8 10
conv.r8
conv.i8 
stloc  _in1
.locals (int64 _in3)
l1: 
l2: 
ldstr "-"
call void [mscorlib]System.Console::WriteLine(string)
ldc.i4.0
brtrue l2
ldloc _in
conv.r8 
conv.i8
call void [mscorlib]System.Console::WriteLine(int64)
ldloc _in
conv.r8 
ldc.i8 1
conv.r8
sub 
conv.i8 
stloc  _in
ldloc _in
conv.r8 
ldc.i8 0
conv.r8
ceq
ldloc _in
conv.r8 
ldc.i8 1
conv.r8
ldc.i8 -1
conv.r8
mul
ceq
or
brfalse l3
ldstr "Opa"
call void [mscorlib]System.Console::WriteLine(string)
l3: 
ldc.i8 10
conv.r8
conv.i8 
stloc  _in3
l4: 
ldloc _in3
conv.r8 
ldc.i8 0
conv.r8
cgt
brfalse l5
ldloc _in3
conv.r8 
conv.i8
call void [mscorlib]System.Console::WriteLine(int64)
ldloc _in3
conv.r8 
ldc.i8 2
conv.r8
sub 
conv.i8 
stloc  _in3
br l4
l5: 
ldloc _in
conv.r8 
ldc.i8 3
conv.r8
ldc.i8 -1
conv.r8
mul
cgt
brtrue l1
ret
}
}