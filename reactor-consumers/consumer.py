# -*- coding:utf-8 -*-

import pika
import time
from pika.adapters import twisted_connection
from twisted.internet import defer, reactor, protocol, task


@defer.inlineCallbacks
def run(connection):

    channel = yield connection.channel()

    exchange = yield channel.exchange_declare(
        exchange='topic_link', type='topic')

    queue1 = yield channel.queue_declare(
        queue='hello', auto_delete=False, exclusive=False)

    queue2 = yield channel.queue_declare(
        queue='world', auto_delete=False, exclusive=False)

    yield channel.queue_bind(
        exchange='topic_link', queue='hello', routing_key='hello')

    yield channel.queue_bind(
        exchange='topic_link', queue='world', routing_key='world')

    yield channel.basic_qos(prefetch_count=1)

    queue_object1, consumer_tag1 = yield channel.basic_consume(
        queue='hello', no_ack=False)

    queue_object2, consumer_tag2 = yield channel.basic_consume(
        queue='world', no_ack=False)

    l1 = task.LoopingCall(read, queue_object1)

    l2 = task.LoopingCall(read, queue_object2)

    l1.start(0.01)
    l2.start(0.01)


@defer.inlineCallbacks
def read(queue_object):

    print 'Is this a blocking call?'
    ch, method, properties, body = yield queue_object.get()
    print 'Trying again'

    if body:
        print body

    time.sleep(10)
    yield ch.basic_ack(delivery_tag=method.delivery_tag)


parameters = pika.ConnectionParameters()
cc = protocol.ClientCreator(
    reactor, twisted_connection.TwistedProtocolConnection, parameters)
d = cc.connectTCP('localhost', 5672)
d.addCallback(lambda protocol: protocol.ready)
d.addCallback(run)
reactor.run()
