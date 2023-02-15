#!/usr/bin/env python3

import torch as np
from torch import rand
import time

a = rand(3000, 2000).cuda()
b = rand(2000, 4000).cuda()
c = rand(4000, 3000).cuda()
begin = time.time()
for i in range(100):
    np.dot(np.dot(a, b), c)
end = time.time()
print(end-begin)
