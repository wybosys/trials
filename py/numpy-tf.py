#!/usr/bin/env python3

import tensorflow._api.v2.experimental.numpy as np
import tensorflow._api.v2.experimental.numpy.random as random
import time

a = random.rand(3000, 2000)
b = random.rand(2000, 4000)
c = random.rand(4000, 3000)
begin = time.time()
for i in range(100):
    np.dot(np.dot(a, b), c)
end = time.time()
print(end-begin)
