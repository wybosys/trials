#!/usr/bin/env python3

import numba
import numpy as np
from numba import jit
import pytest
import timeit


def pure():
    x = 0
    for i in np.arange(5000):
        x += i
    return x


@jit(nopython=True)
def usenumba():
    x = 0
    for i in np.arange(5000):
        x += i
    return x


if __name__ == '__main__':
    print("use numba: ", timeit.timeit(lambda: usenumba(), number=1000))
    print("use pure: ", timeit.timeit(lambda: pure(), number=1000))
