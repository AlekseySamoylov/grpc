package com.alekseysamoylov.grpc.server

import com.proto.dummy.GreetRequest
import com.proto.dummy.GreetResponse
import com.proto.dummy.GreetServiceGrpc
import io.grpc.stub.StreamObserver


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
}
