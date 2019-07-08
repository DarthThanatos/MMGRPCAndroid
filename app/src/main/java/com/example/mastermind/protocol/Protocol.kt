package com.example.mastermind.protocol

import android.os.AsyncTask
import android.util.Log
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import server.GreeterGrpc
import java.util.concurrent.TimeUnit

typealias TaskFunction<T> = (blockingStub: GreeterGrpc.GreeterBlockingStub, asynchStub: GreeterGrpc.GreeterStub) -> T

private val gameRequester = GameRequesterImpl()
private val verifier = VerifierImpl()
private val guesser = GuesserImpl()

class Protocol internal constructor(private val channel: ManagedChannel):
    GameRequester by gameRequester, Verifier by verifier, Guesser by guesser{

    private val blockingStub: GreeterGrpc.GreeterBlockingStub
            = GreeterGrpc.newBlockingStub(channel)

    private val asynchStub = GreeterGrpc.newStub(channel)

    constructor(host: String, port: Int) : this(
        ManagedChannelBuilder.forAddress(host, port)
            // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
            // needing certificates.
            .usePlaintext()
            .build())

    @Throws(InterruptedException::class)
    fun shutdown() {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS)
    }

    private class Task<T>(
        private val blockingStub: GreeterGrpc.GreeterBlockingStub,
        private val asynchStub: GreeterGrpc.GreeterStub,
        private val task: (blockingStub: GreeterGrpc.GreeterBlockingStub, asynchStub: GreeterGrpc.GreeterStub) -> T,
        private val onResult: ((result: T) -> Unit)?): AsyncTask<Void, Void, T>(){
        override fun doInBackground(vararg params: Void?): T
                = task(blockingStub, asynchStub)

        override fun onPostExecute(result: T) {
            onResult?.invoke(result)
        }
    }


    fun <T> runInBackground(
        task: TaskFunction<T>,
        onResult: ((result: T) -> Unit)? = null
    ){
        Task(blockingStub, asynchStub, task, onResult).execute()
    }

}