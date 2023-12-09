.assembly extern mscorlib {}
.assembly _exemplo{}
.module _exemplo.exe

.class public teste {
.method static public void _principal(){
.entrypoint
ldc.i8 7
conv.r8
ldc.i8 -1
conv.r8
mul
ldc.i8 0
conv.r8
ceq
brfalse l1
ldstr "ola"
call void [mscorlib]System.Console::WriteLine(string)
l1: 
l2: 
ldstr "Lucas"
call void [mscorlib]System.Console::WriteLine(string)
ldc.i8 2
conv.r8
ldc.i8 5
conv.r8
cgt
brtrue bool
ldstr "oi"
call void [mscorlib]System.Console::WriteLine(string)
ldc.i8 123
conv.r8
conv.i8
call void [mscorlib]System.Console::WriteLine(int64)
ldc.i4.1
call void [mscorlib]System.Console::WriteLine(bool)
ret
}
}