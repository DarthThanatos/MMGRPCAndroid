package com.example.mastermind.protocol

import android.app.Activity
import android.os.AsyncTask
import com.example.mastermind.GreeterGrpc
import com.example.mastermind.HelloRequest
import com.example.mastermind.intro.view.IntroActivity
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.StatusRuntimeException
import java.lang.ref.WeakReference
import java.util.concurrent.TimeUnit


class GrpcTask(activity: IntroActivity) : AsyncTask<String, Void, String>(){

    private val activityReference = WeakReference<Activity>(activity)
    private lateinit var channel: ManagedChannel


    override fun doInBackground(vararg params: String): String {
        val host: String=  params[0]
        val message: String = params[1]
        val port: Int = params[2].toInt()
        try{
            channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build()
            val stub = GreeterGrpc.newBlockingStub(channel)
            val request = HelloRequest.newBuilder().setName(message).build()
            return stub.sayHello(request).message
        }catch (e: StatusRuntimeException){
            e.printStackTrace()
            return "Failed"
        }
    }

    override fun onPostExecute(result: String) {
        channel.shutdown().awaitTermination(1, TimeUnit.SECONDS)
        val activity = activityReference.get()
//        activity?.findViewById<TextView>(R.id.textView)?.setText(result)
    }
}