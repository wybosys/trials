#!/usr/bin/env python3
import six, abc, inspect

def d0(*args):
    print("d0:")
    print(args)
    return args[0]

def d1(func):
    print("d1:")
    print(inspect.getmembers(func))
    return func

def c0(clz):
    def func(v):
        print('xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx')
        print(v)
        return clz    
    #print("c0")
    #setattr(clz, 'abc', 'ffffffffffff')
    #return clz
    return func

def Fp0(*args):
    print(args)
    return "ppppppppppppppppppppppp"

class A:
    
    #abc = Fp0()
    cde = 123

    def __init__(self):
        print(self.abc)
        self.abc = 'abc'
        print(self.abc)
        self.cde = 0
        print(self.cde)
        print(A.cde)

    #@d1
    def a(self):
        print('A:a()')

class F:

    def __init__(self):
        print("F")

class B(F):

    def __init__(self):
        super().__init__()
        print("B")

class C:

    def __init__(self):
        print("C")

class G(C):

    def __init__(self):
        #super().__init__()
        print("G")

class E:

    def __init__(self):
        print("E")

class X:
    a = 0

def subinits(obj):
    print(obj.__class__.__mro__)
    for e in obj.__class__.__mro__:
        if e == object:
            continue
        super(e, obj).__init__()
        
class D(B,C,E):

    def __init__(self):
        subinits(self)
        print("D")

#G()
#d=D()
#print(d.__class__.__mro__)
x=X()
print(x.a)
x.a = 1
print(X.a)
