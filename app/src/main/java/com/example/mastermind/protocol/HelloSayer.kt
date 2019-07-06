package com.example.mastermind.protocol

import io.grpc.StatusRuntimeException
import server.GreeterGrpc
import server.HelloReply
import server.HelloRequest
import java.util.logging.Level
import java.util.logging.Logger


interface HelloSayer{
    fun greet(args: Array<String>, blockingStub: GreeterGrpc.GreeterBlockingStub)
}

class HelloSayerImpl: HelloSayer{

    private val logger = Logger.getLogger(MasterMindClient::class.java.name)

    /** Say hello to server.  */
    override fun greet(args: Array<String>, blockingStub: GreeterGrpc.GreeterBlockingStub) {
        val name = if (args.size > 0) args[0] else "world"
        logger.log(Level.INFO, "Will try to greet {0}...", name)
        val request = HelloRequest.newBuilder().setName(name).build()
        val response: HelloReply =  try {

            blockingStub.sayHello(request)
        } catch (e: StatusRuntimeException) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.status.description)
            return
        }

        logger.info("Greeting: ${response.message}")
        val requestOne = HelloRequest.newBuilder().setName(name).build()
        val responseOne: HelloReply =  try {
            blockingStub.sayHelloAgain(requestOne)
        } catch (e: StatusRuntimeException) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.status.description)
            return
        }

        logger.info("Greeting: ${responseOne.message}")

    }
}
