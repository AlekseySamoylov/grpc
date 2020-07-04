package com.alekseysamoylov.grpc.server

import io.grpc.ServerBuilder


class GreetingServer {
}

fun main() {
    println("Hello I'm a gRPC server")

    val server = ServerBuilder.forPort(50051)
        .build()
    server.start()

    Runtime.getRuntime().addShutdownHook(Thread{
        println("Received Shutdown Request")
        server.shutdown()
        println("Successfully Stopped Server")
    })
    server.awaitTermination()
}
