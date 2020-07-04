package com.alekseysamoylov.grpc.client

import com.proto.dummy.GreetManyTimesRequest
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
    val greetClient = GreetServiceGrpc.newBlockingStub(channel)

    unaryCall(greetClient)

    streamCallCall(greetClient)



    println("Shutting down channel")

    channel.shutdown()
}

private fun streamCallCall(greetClient: GreetServiceGrpc.GreetServiceBlockingStub) {
    println("Start Streaming call")
    val greetManyTimesRequest = GreetManyTimesRequest.newBuilder()
        .setGreeting(Greeting.newBuilder()
            .setFirstName("Aleksey")
            .setLastName("Samoylov"))
        .build()

    // stream the responses (in a blocking manner)
    greetClient.greetManyTimes(greetManyTimesRequest).forEachRemaining { manyTimesResponse ->
        println("StreamingResponse: ${manyTimesResponse.result}")
    }
}

private fun unaryCall(greetClient: GreetServiceGrpc.GreetServiceBlockingStub) {
    println("Start Unary call")

    val greetResponse = greetClient.greet(GreetRequest.newBuilder()
        .setGreeting(Greeting.newBuilder()
            .setFirstName("Aleksey")
            .setLastName("Samoylov"))
        .build())

    println("Greeting response: $greetResponse")

}
