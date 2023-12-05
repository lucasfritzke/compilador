.assembly extern mscorlib {}
.assembly _exemplo{}
.module _exemplo.exe

.class public teste {
.method static public void _principal(){
.entrypoint
.locals (int64 _inum)
ldc.i8 10
conv.r8
conv.i8 
stloc  _inum
.locals (string _snome)
ldstr "oi"
stloc  _snome
l1: 
ldloc _inum
conv.r8 
ldc.i8 0
conv.r8
cgt
brfalse l2
ldloc _inum
conv.r8 
ldc.i8 5
conv.r8
cgt
ldloc _inum
conv.r8 
ldc.i8 2
conv.r8
ceq
or
brfalse l3
ldloc _inum
conv.r8 
conv.i8
call void [mscorlib]System.Console::WriteLine(int64)
l3: 
ldloc _inum
conv.r8 
ldc.i8 1
conv.r8
sub 
conv.i8 
stloc  _inum
br l1
l2: 
ldloc _snome
ldstr "aa"
ceq
ldc.i4.1
xor
brfalse l4
ldstr "Feliz Natal"
stloc  _snome
l4: 
ldloc _snome
call void [mscorlib]System.Console::WriteLine(string)
ret
}
}