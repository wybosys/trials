<?php

$cnn = new AMQPConnection();
$cnn->setLogin('guest');
$cnn->setPassword('guest');
$cnn->pconnect();

$ch = new AMQPChannel($cnn);

$ex = new AMQPExchange($ch);
$ex->setName('hello-exchange');
$ex->setType(AMQP_EX_TYPE_DIRECT);
$ex->setFlags(AMQP_DURABLE);
$ex->declareExchange();

$ex->publish('quit', 'hola', AMQP_NOPARAM, array('delivery_mode' => 2));


