#!/usr/bin/env python3

import json5

obj = {
    "abc": 123,
    "arr": [
        1,
        2,
        3,
    ],
}

sobj = """
// 测试
{
    /** 测试 */
    abc: 123,
    $abc: 123,
}
"""

print(json5.dumps(obj))
print(json5.loads(sobj))
print(json5.dumps(json5.loads(sobj)))
print(type(json5.loads(sobj)))
print(json5.loads(sobj)["$abc"])
