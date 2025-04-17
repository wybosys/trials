#!/usr/bin/env python3

import pika
cred = pika.PlainCredentials("sample", "sample")
conparm = pika.ConnectionParameters("localhost", credentials=cred)
conbrok = pika.BlockingConnection(conparm)
cnl = conbrok.channel()
cnl.exchange_declare(exchange="hello-exchange", type="direct",
                     passive=False, durable=True, auto_delete=False)
cnl.queue_declare(queue="hello-queue", durable=True)
cnl.queue_bind(queue="hello-queue",
               exchange="hello-exchange", routing_key="hola")


def cbmsg(channel, mth, hdr, body):
    channel.basic_ack(delivery_tag=mth.delivery_tag)
    if body == "quit":
        channel.basic_cancel(consumer_tag="hello-receiver")
        channel.stop_consuming()
    else:
        print(body)
    return


cnl.basic_consume(cbmsg, queue="hello-queue", consumer_tag="hello-receiver")
cnl.start_consuming()
