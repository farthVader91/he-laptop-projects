#! /usr/bin/env python
import amqp


con = amqp.connection.Connection()
con.connect()


chan = amqp.channel.Channel(con)
chan.open()


def on_msg(msg):
    print msg


chan.basic_consume('benchmark', callback=on_msg)


while True:
    con.drain_events()

# print chan.basic_get('benchmark').__dict__
