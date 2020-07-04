package com.alekseysamoylov.grpc.server

import com.proto.dummy.*
import io.grpc.stub.StreamObserver
import java.util.concurrent.TimeUnit


class GreetServiceImpl : GreetServiceGrpc.GreetServiceImplBase() {
    override fun greet(request: GreetRequest, responseObserver: StreamObserver<GreetResponse>) {
        val greeting = request.greeting
        val result = "Hello ${greeting.firstName} ${greeting.lastName}"
        val response = GreetResponse.newBuilder()
            .setResult(result)
            .build()
        // send response
        responseObserver.onNext(response)
        // complete the RPC call
        responseObserver.onCompleted()
    }

    override fun greetManyTimes(
        request: GreetManyTimesRequest,
        responseObserver: StreamObserver<GreetManyTimesResponse>
    ) {
        val greeting = request.greeting

        repeat(10) {
            val result = "Hello ${greeting.firstName} ${greeting.lastName}, response number: $it"
            val response = GreetManyTimesResponse.newBuilder()
                .setResult(result)
                .build()
            // send response
            responseObserver.onNext(response)
            TimeUnit.SECONDS.sleep(1)
        }
        // complete the RPC call
        responseObserver.onCompleted()
    }
}
