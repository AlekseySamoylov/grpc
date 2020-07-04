package com.alekseysamoylov.grpc.client

import com.proto.dummy.DummyServiceGrpc
import io.grpc.ManagedChannelBuilder


class GreetingClient {
}

fun main() {
    println("Hello, I'm a gRPC client")

    val channel = ManagedChannelBuilder.forAddress("localhost", 50051)
        .usePlaintext() // to avoid ssl
        .build()
    println("Creating stub")

    val syncClient = DummyServiceGrpc.newBlockingStub(channel)
//    val asyncClient = DummyServiceGrpc.newFutureStub(channel)

    // do something
    println("Shutting down channel")

    channel.shutdown()
}
