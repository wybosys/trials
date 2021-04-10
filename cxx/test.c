#include "stdio.h"

const char *abc = "abcabcabcabc";

int main()
{
    //printf("%s", abc);
    for (char a = 0; a < 128; ++a)
    printf("%d\n", a);
}
