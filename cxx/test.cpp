#include <cstdio>
#include <vector>
#include <iostream>
#include <string>

class A
{
public:
    A(::std::string const &s) : str(s)
    {
    }
    ~A()
    {
        ::std::cout << "A Release" << ::std::endl;
    }
    ::std::string str;
};

A *f(const std::vector<A> &v)
{
    return (A *)&v.front();
}

void Test()
{
    A *r;
    {
        r = f({A("HAHA")});
    }
    A &x = *r;
    ::std::cout << x.str << ::std::endl;
    x.str = "abc";
    ::std::cout << x.str << ::std::endl;
}

::std::string abc = "abcabcabcabc";

int main()
{
    Test();
    ::std::cout << abc << std::endl;
    return 0;
}
