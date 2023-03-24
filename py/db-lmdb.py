#!/usr/bin/env python3

import lmdb

env = lmdb.open('./lmdb.db', map_size=1024*1024)
txn = env.begin(write=True)

v = txn.get(b'counter')
if v:
    v = int(v) + 1
else:
    v = 0
txn.put(key=b'counter', value=str(v).encode('utf-8'))
print(v)

txn.commit()
env.close()
