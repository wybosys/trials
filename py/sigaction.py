#!/usr/bin/env python3

import time
import signal
import os
import sys


def action_term(signum, frame):
    print("normal killing")
    sys.exit()


signal.signal(signal.SIGTERM, action_term)

print(f"pid: {os.getpid()}")
while True:
    print("tick")
    time.sleep(1)
