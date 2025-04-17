#!/usr/bin/env python3

import jax.numpy as np
from jax import random
import time

#x = random.uniform(random.PRNGKey(0), [5000, 5000])
#np.matmul(x, x)


def Rand(x, y):
    return random.uniform(random.PRNGKey(0), [x, y])


a = Rand(3000, 2000)
b = Rand(2000, 4000)
c = Rand(4000, 3000)
begin = time.time()
for i in range(100):
    np.dot(np.dot(a, b), c)
end = time.time()
print(end-begin)
