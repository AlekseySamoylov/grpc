package com.alekseysamoylov.grpc.client

import com.proto.dummy.GreetRequest
import com.proto.dummy.GreetServiceGrpc
import com.proto.dummy.Greeting
import io.grpc.ManagedChannelBuilder


class GreetingClient {
}

fun main() {
    println("Hello, I'm a gRPC client")

    val channel = ManagedChannelBuilder.forAddress("localhost", 50051)
        .usePlaintext() // to avoid ssl
        .build()
    println("Creating stub")

    val greetClient = GreetServiceGrpc.newBlockingStub(channel)
    val greetResponse = greetClient.greet(GreetRequest.newBuilder()
        .setGreeting(Greeting.newBuilder()
            .setFirstName("Aleksey")
            .setLastName("Samoylov"))
        .build())

    println("Greeting response: $greetResponse")

    println("Shutting down channel")

    channel.shutdown()
}
