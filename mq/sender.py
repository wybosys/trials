#!/usr/bin/env python3

import pika
import sys

cred = pika.PlainCredentials("sample", "sample")
conparm = pika.ConnectionParameters(
    "wybosys.com", 5672, 'sample', credentials=cred)
conbrok = pika.BlockingConnection(conparm)
channel = conbrok.channel()
channel.exchange_declare(exchange="hello-exchange", type="direct",
                         passive=False, durable=True, auto_delete=False)
msg = sys.argv[1]
msg_props = pika.BasicProperties()
msg_props.content_type = "text/plain"
msg_props.delivery_mode = 2
channel.basic_publish(body=msg, exchange="hello-exchange",
                      properties=msg_props, routing_key="hola")
