#!/usr/bin/env python3

import asyncio
import time


def sim_block():
    print("sim block: start")
    time.sleep(5)
    print("sim block: end")


async def main():
    tasks = []
    tasks.append(asyncio.create_task(asyncio.to_thread(sim_block)))
    tasks.append(asyncio.create_task(asyncio.to_thread(sim_block)))
    for each in tasks:
        await each


asyncio.run(main())
