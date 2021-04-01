-module(sender).
-include_lib("amqp_client/include/amqp_client.hrl").
-export([test/0]).

test()->
    {ok, Connection}=amqp_connection:start(#amqp_params_network{host="localhost"}),
    {ok, Channel}=amqp_connection:open_channel(Connection),
    amqp_channel:call(Channel, #'queue.declare'{queue= <<"hello-queue">>,durable=true}),
    amqp_channel:cast(Channel, 
                      #'basic.publish'{exchange= <<"hello-exchange">>,routing_key= <<"hola">>},
                      #amqp_msg{payload= <<"quit">>}),
    amqp_channel:close(Channel),
    amqp_connection:close(Connection),
    ok.
