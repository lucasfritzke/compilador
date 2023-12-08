.assembly extern mscorlib {}
.assembly _exemplo{}
.module _exemplo.exe

.class public teste {
.method static public void _principal(){
.entrypoint
.locals (bool _blado)
.locals (int64 _inum2)
.locals (int64 _inum)
ldc.i8 500
conv.r8
conv.i8 
stloc  _inum
ldc.i8 10
conv.r8
conv.i8 
stloc  _inum2
ldloc _inum
conv.r8 
ldc.i8 10
conv.r8
add 
ldc.i8 2
conv.r8
div 
ldc.i8 25
conv.r8
cgt
stloc  _blado
ldloc _blado
call void [mscorlib]System.Console::WriteLine(bool)
l1: 
ldloc _inum
conv.r8 
ldc.i8 100
conv.r8
cgt
brfalse l2
ldloc _inum
conv.r8 
conv.i8
call void [mscorlib]System.Console::WriteLine(int64)
l3: 
ldloc _inum2
conv.r8 
ldc.i8 0
conv.r8
cgt
brfalse l4
ldloc _inum2
conv.r8 
conv.i8
call void [mscorlib]System.Console::WriteLine(int64)
ldloc _inum2
conv.r8 
ldc.i8 2
conv.r8
sub 
conv.i8 
stloc  _inum2
br l3
l4: 
ldloc _inum
conv.r8 
ldc.i8 70
conv.r8
sub 
conv.i8 
stloc  _inum
br l1
l2: 
ret
}
}