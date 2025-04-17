-module(receiver).
-include_lib("amqp_client/include/amqp_client.hrl").
-export([test/0]).

test()->
    {ok, Connection} = amqp_connection:start(#amqp_params_network{}),
    {ok, Channel} = amqp_connection:open_channel(Connection),
    #'exchange.declare_ok'{} = amqp_channel:call(Channel, 
                                                 #'exchange.declare'{exchange = <<"hello-exchange">>, durable=true}),
    #'queue.declare_ok'{queue=Queue} = amqp_channel:call(Channel, 
                                                         #'queue.declare'{queue = <<"hello-queue">>, durable=true}),
    Bind = #'queue.bind'{queue = Queue, exchange = <<"hello-exchange">>, routing_key = <<"hola">>},
    #'queue.bind_ok'{} = amqp_channel:call(Channel, Bind),
    Get = #'basic.get'{queue=Queue, no_ack=true},
    {#'basic.get_ok'{}, Content} = amqp_channel:call(Channel, Get),
    #amqp_msg{payload = Payload} = Content,
    io:format("~p~n", [Payload]).

    
    
