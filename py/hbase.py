#!/usr/bin/env python3

import happybase

host = "192.168.103.211"
cnn = happybase.Connection(host)
print(cnn.tables())
cnn.create_table("test", {
    "a":dict(),
    "b":dict()
                          })
print(cnn.tables())
print(cnn.delete_table("test"))

