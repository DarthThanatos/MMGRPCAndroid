package server;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * The greeting service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.21.0)",
    comments = "Source: Hello.proto")
public final class GreeterGrpc {

  private GreeterGrpc() {}

  public static final String SERVICE_NAME = "helloworld.Greeter";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<server.HelloRequest,
      server.HelloReply> getSayHelloMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SayHello",
      requestType = server.HelloRequest.class,
      responseType = server.HelloReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<server.HelloRequest,
      server.HelloReply> getSayHelloMethod() {
    io.grpc.MethodDescriptor<server.HelloRequest, server.HelloReply> getSayHelloMethod;
    if ((getSayHelloMethod = GreeterGrpc.getSayHelloMethod) == null) {
      synchronized (GreeterGrpc.class) {
        if ((getSayHelloMethod = GreeterGrpc.getSayHelloMethod) == null) {
          GreeterGrpc.getSayHelloMethod = getSayHelloMethod = 
              io.grpc.MethodDescriptor.<server.HelloRequest, server.HelloReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.Greeter", "SayHello"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  server.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  server.HelloReply.getDefaultInstance()))
                  .build();
          }
        }
     }
     return getSayHelloMethod;
  }

  private static volatile io.grpc.MethodDescriptor<server.HelloRequest,
      server.HelloReply> getSayHelloAgainMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SayHelloAgain",
      requestType = server.HelloRequest.class,
      responseType = server.HelloReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<server.HelloRequest,
      server.HelloReply> getSayHelloAgainMethod() {
    io.grpc.MethodDescriptor<server.HelloRequest, server.HelloReply> getSayHelloAgainMethod;
    if ((getSayHelloAgainMethod = GreeterGrpc.getSayHelloAgainMethod) == null) {
      synchronized (GreeterGrpc.class) {
        if ((getSayHelloAgainMethod = GreeterGrpc.getSayHelloAgainMethod) == null) {
          GreeterGrpc.getSayHelloAgainMethod = getSayHelloAgainMethod = 
              io.grpc.MethodDescriptor.<server.HelloRequest, server.HelloReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.Greeter", "SayHelloAgain"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  server.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  server.HelloReply.getDefaultInstance()))
                  .build();
          }
        }
     }
     return getSayHelloAgainMethod;
  }

  private static volatile io.grpc.MethodDescriptor<server.GameDescription,
      server.GameDescription> getCreateGameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateGame",
      requestType = server.GameDescription.class,
      responseType = server.GameDescription.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<server.GameDescription,
      server.GameDescription> getCreateGameMethod() {
    io.grpc.MethodDescriptor<server.GameDescription, server.GameDescription> getCreateGameMethod;
    if ((getCreateGameMethod = GreeterGrpc.getCreateGameMethod) == null) {
      synchronized (GreeterGrpc.class) {
        if ((getCreateGameMethod = GreeterGrpc.getCreateGameMethod) == null) {
          GreeterGrpc.getCreateGameMethod = getCreateGameMethod = 
              io.grpc.MethodDescriptor.<server.GameDescription, server.GameDescription>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.Greeter", "CreateGame"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  server.GameDescription.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  server.GameDescription.getDefaultInstance()))
                  .build();
          }
        }
     }
     return getCreateGameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<server.GameDescription,
      server.GamesByName> getGetGamesByNameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetGamesByName",
      requestType = server.GameDescription.class,
      responseType = server.GamesByName.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<server.GameDescription,
      server.GamesByName> getGetGamesByNameMethod() {
    io.grpc.MethodDescriptor<server.GameDescription, server.GamesByName> getGetGamesByNameMethod;
    if ((getGetGamesByNameMethod = GreeterGrpc.getGetGamesByNameMethod) == null) {
      synchronized (GreeterGrpc.class) {
        if ((getGetGamesByNameMethod = GreeterGrpc.getGetGamesByNameMethod) == null) {
          GreeterGrpc.getGetGamesByNameMethod = getGetGamesByNameMethod = 
              io.grpc.MethodDescriptor.<server.GameDescription, server.GamesByName>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.Greeter", "GetGamesByName"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  server.GameDescription.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  server.GamesByName.getDefaultInstance()))
                  .build();
          }
        }
     }
     return getGetGamesByNameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<server.Player,
      server.Player> getJoinGameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "JoinGame",
      requestType = server.Player.class,
      responseType = server.Player.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<server.Player,
      server.Player> getJoinGameMethod() {
    io.grpc.MethodDescriptor<server.Player, server.Player> getJoinGameMethod;
    if ((getJoinGameMethod = GreeterGrpc.getJoinGameMethod) == null) {
      synchronized (GreeterGrpc.class) {
        if ((getJoinGameMethod = GreeterGrpc.getJoinGameMethod) == null) {
          GreeterGrpc.getJoinGameMethod = getJoinGameMethod = 
              io.grpc.MethodDescriptor.<server.Player, server.Player>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.Greeter", "JoinGame"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  server.Player.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  server.Player.getDefaultInstance()))
                  .build();
          }
        }
     }
     return getJoinGameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<server.Combination,
      server.Player> getWaitForGuesserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WaitForGuesser",
      requestType = server.Combination.class,
      responseType = server.Player.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<server.Combination,
      server.Player> getWaitForGuesserMethod() {
    io.grpc.MethodDescriptor<server.Combination, server.Player> getWaitForGuesserMethod;
    if ((getWaitForGuesserMethod = GreeterGrpc.getWaitForGuesserMethod) == null) {
      synchronized (GreeterGrpc.class) {
        if ((getWaitForGuesserMethod = GreeterGrpc.getWaitForGuesserMethod) == null) {
          GreeterGrpc.getWaitForGuesserMethod = getWaitForGuesserMethod = 
              io.grpc.MethodDescriptor.<server.Combination, server.Player>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.Greeter", "WaitForGuesser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  server.Combination.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  server.Player.getDefaultInstance()))
                  .build();
          }
        }
     }
     return getWaitForGuesserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<server.Player,
      server.Player> getWaitForVerifierMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "WaitForVerifier",
      requestType = server.Player.class,
      responseType = server.Player.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<server.Player,
      server.Player> getWaitForVerifierMethod() {
    io.grpc.MethodDescriptor<server.Player, server.Player> getWaitForVerifierMethod;
    if ((getWaitForVerifierMethod = GreeterGrpc.getWaitForVerifierMethod) == null) {
      synchronized (GreeterGrpc.class) {
        if ((getWaitForVerifierMethod = GreeterGrpc.getWaitForVerifierMethod) == null) {
          GreeterGrpc.getWaitForVerifierMethod = getWaitForVerifierMethod = 
              io.grpc.MethodDescriptor.<server.Player, server.Player>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.Greeter", "WaitForVerifier"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  server.Player.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  server.Player.getDefaultInstance()))
                  .build();
          }
        }
     }
     return getWaitForVerifierMethod;
  }

  private static volatile io.grpc.MethodDescriptor<server.Player,
      server.Player> getKeepAliveMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "KeepAlive",
      requestType = server.Player.class,
      responseType = server.Player.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<server.Player,
      server.Player> getKeepAliveMethod() {
    io.grpc.MethodDescriptor<server.Player, server.Player> getKeepAliveMethod;
    if ((getKeepAliveMethod = GreeterGrpc.getKeepAliveMethod) == null) {
      synchronized (GreeterGrpc.class) {
        if ((getKeepAliveMethod = GreeterGrpc.getKeepAliveMethod) == null) {
          GreeterGrpc.getKeepAliveMethod = getKeepAliveMethod = 
              io.grpc.MethodDescriptor.<server.Player, server.Player>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.Greeter", "KeepAlive"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  server.Player.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  server.Player.getDefaultInstance()))
                  .build();
          }
        }
     }
     return getKeepAliveMethod;
  }

  private static volatile io.grpc.MethodDescriptor<server.Combination,
      server.Verification> getGuessMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Guess",
      requestType = server.Combination.class,
      responseType = server.Verification.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<server.Combination,
      server.Verification> getGuessMethod() {
    io.grpc.MethodDescriptor<server.Combination, server.Verification> getGuessMethod;
    if ((getGuessMethod = GreeterGrpc.getGuessMethod) == null) {
      synchronized (GreeterGrpc.class) {
        if ((getGuessMethod = GreeterGrpc.getGuessMethod) == null) {
          GreeterGrpc.getGuessMethod = getGuessMethod = 
              io.grpc.MethodDescriptor.<server.Combination, server.Verification>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.Greeter", "Guess"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  server.Combination.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  server.Verification.getDefaultInstance()))
                  .build();
          }
        }
     }
     return getGuessMethod;
  }

  private static volatile io.grpc.MethodDescriptor<server.Player,
      server.Combination> getGetGuessesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetGuesses",
      requestType = server.Player.class,
      responseType = server.Combination.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<server.Player,
      server.Combination> getGetGuessesMethod() {
    io.grpc.MethodDescriptor<server.Player, server.Combination> getGetGuessesMethod;
    if ((getGetGuessesMethod = GreeterGrpc.getGetGuessesMethod) == null) {
      synchronized (GreeterGrpc.class) {
        if ((getGetGuessesMethod = GreeterGrpc.getGetGuessesMethod) == null) {
          GreeterGrpc.getGetGuessesMethod = getGetGuessesMethod = 
              io.grpc.MethodDescriptor.<server.Player, server.Combination>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.Greeter", "GetGuesses"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  server.Player.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  server.Combination.getDefaultInstance()))
                  .build();
          }
        }
     }
     return getGetGuessesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<server.Verification,
      server.VerifyAck> getVerifyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Verify",
      requestType = server.Verification.class,
      responseType = server.VerifyAck.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<server.Verification,
      server.VerifyAck> getVerifyMethod() {
    io.grpc.MethodDescriptor<server.Verification, server.VerifyAck> getVerifyMethod;
    if ((getVerifyMethod = GreeterGrpc.getVerifyMethod) == null) {
      synchronized (GreeterGrpc.class) {
        if ((getVerifyMethod = GreeterGrpc.getVerifyMethod) == null) {
          GreeterGrpc.getVerifyMethod = getVerifyMethod = 
              io.grpc.MethodDescriptor.<server.Verification, server.VerifyAck>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "helloworld.Greeter", "Verify"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  server.Verification.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  server.VerifyAck.getDefaultInstance()))
                  .build();
          }
        }
     }
     return getVerifyMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GreeterStub newStub(io.grpc.Channel channel) {
    return new GreeterStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GreeterBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GreeterBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GreeterFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GreeterFutureStub(channel);
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static abstract class GreeterImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void sayHello(server.HelloRequest request,
        io.grpc.stub.StreamObserver<server.HelloReply> responseObserver) {
      asyncUnimplementedUnaryCall(getSayHelloMethod(), responseObserver);
    }

    /**
     */
    public void sayHelloAgain(server.HelloRequest request,
        io.grpc.stub.StreamObserver<server.HelloReply> responseObserver) {
      asyncUnimplementedUnaryCall(getSayHelloAgainMethod(), responseObserver);
    }

    /**
     */
    public void createGame(server.GameDescription request,
        io.grpc.stub.StreamObserver<server.GameDescription> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateGameMethod(), responseObserver);
    }

    /**
     */
    public void getGamesByName(server.GameDescription request,
        io.grpc.stub.StreamObserver<server.GamesByName> responseObserver) {
      asyncUnimplementedUnaryCall(getGetGamesByNameMethod(), responseObserver);
    }

    /**
     */
    public void joinGame(server.Player request,
        io.grpc.stub.StreamObserver<server.Player> responseObserver) {
      asyncUnimplementedUnaryCall(getJoinGameMethod(), responseObserver);
    }

    /**
     */
    public void waitForGuesser(server.Combination request,
        io.grpc.stub.StreamObserver<server.Player> responseObserver) {
      asyncUnimplementedUnaryCall(getWaitForGuesserMethod(), responseObserver);
    }

    /**
     */
    public void waitForVerifier(server.Player request,
        io.grpc.stub.StreamObserver<server.Player> responseObserver) {
      asyncUnimplementedUnaryCall(getWaitForVerifierMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<server.Player> keepAlive(
        io.grpc.stub.StreamObserver<server.Player> responseObserver) {
      return asyncUnimplementedStreamingCall(getKeepAliveMethod(), responseObserver);
    }

    /**
     */
    public void guess(server.Combination request,
        io.grpc.stub.StreamObserver<server.Verification> responseObserver) {
      asyncUnimplementedUnaryCall(getGuessMethod(), responseObserver);
    }

    /**
     */
    public void getGuesses(server.Player request,
        io.grpc.stub.StreamObserver<server.Combination> responseObserver) {
      asyncUnimplementedUnaryCall(getGetGuessesMethod(), responseObserver);
    }

    /**
     */
    public void verify(server.Verification request,
        io.grpc.stub.StreamObserver<server.VerifyAck> responseObserver) {
      asyncUnimplementedUnaryCall(getVerifyMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSayHelloMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                server.HelloRequest,
                server.HelloReply>(
                  this, METHODID_SAY_HELLO)))
          .addMethod(
            getSayHelloAgainMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                server.HelloRequest,
                server.HelloReply>(
                  this, METHODID_SAY_HELLO_AGAIN)))
          .addMethod(
            getCreateGameMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                server.GameDescription,
                server.GameDescription>(
                  this, METHODID_CREATE_GAME)))
          .addMethod(
            getGetGamesByNameMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                server.GameDescription,
                server.GamesByName>(
                  this, METHODID_GET_GAMES_BY_NAME)))
          .addMethod(
            getJoinGameMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                server.Player,
                server.Player>(
                  this, METHODID_JOIN_GAME)))
          .addMethod(
            getWaitForGuesserMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                server.Combination,
                server.Player>(
                  this, METHODID_WAIT_FOR_GUESSER)))
          .addMethod(
            getWaitForVerifierMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                server.Player,
                server.Player>(
                  this, METHODID_WAIT_FOR_VERIFIER)))
          .addMethod(
            getKeepAliveMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                server.Player,
                server.Player>(
                  this, METHODID_KEEP_ALIVE)))
          .addMethod(
            getGuessMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                server.Combination,
                server.Verification>(
                  this, METHODID_GUESS)))
          .addMethod(
            getGetGuessesMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                server.Player,
                server.Combination>(
                  this, METHODID_GET_GUESSES)))
          .addMethod(
            getVerifyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                server.Verification,
                server.VerifyAck>(
                  this, METHODID_VERIFY)))
          .build();
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class GreeterStub extends io.grpc.stub.AbstractStub<GreeterStub> {
    private GreeterStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreeterStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreeterStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void sayHello(server.HelloRequest request,
        io.grpc.stub.StreamObserver<server.HelloReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSayHelloMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sayHelloAgain(server.HelloRequest request,
        io.grpc.stub.StreamObserver<server.HelloReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSayHelloAgainMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createGame(server.GameDescription request,
        io.grpc.stub.StreamObserver<server.GameDescription> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateGameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getGamesByName(server.GameDescription request,
        io.grpc.stub.StreamObserver<server.GamesByName> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetGamesByNameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void joinGame(server.Player request,
        io.grpc.stub.StreamObserver<server.Player> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getJoinGameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void waitForGuesser(server.Combination request,
        io.grpc.stub.StreamObserver<server.Player> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWaitForGuesserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void waitForVerifier(server.Player request,
        io.grpc.stub.StreamObserver<server.Player> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWaitForVerifierMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<server.Player> keepAlive(
        io.grpc.stub.StreamObserver<server.Player> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getKeepAliveMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public void guess(server.Combination request,
        io.grpc.stub.StreamObserver<server.Verification> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGuessMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getGuesses(server.Player request,
        io.grpc.stub.StreamObserver<server.Combination> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetGuessesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void verify(server.Verification request,
        io.grpc.stub.StreamObserver<server.VerifyAck> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getVerifyMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class GreeterBlockingStub extends io.grpc.stub.AbstractStub<GreeterBlockingStub> {
    private GreeterBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreeterBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreeterBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public server.HelloReply sayHello(server.HelloRequest request) {
      return blockingUnaryCall(
          getChannel(), getSayHelloMethod(), getCallOptions(), request);
    }

    /**
     */
    public server.HelloReply sayHelloAgain(server.HelloRequest request) {
      return blockingUnaryCall(
          getChannel(), getSayHelloAgainMethod(), getCallOptions(), request);
    }

    /**
     */
    public server.GameDescription createGame(server.GameDescription request) {
      return blockingUnaryCall(
          getChannel(), getCreateGameMethod(), getCallOptions(), request);
    }

    /**
     */
    public server.GamesByName getGamesByName(server.GameDescription request) {
      return blockingUnaryCall(
          getChannel(), getGetGamesByNameMethod(), getCallOptions(), request);
    }

    /**
     */
    public server.Player joinGame(server.Player request) {
      return blockingUnaryCall(
          getChannel(), getJoinGameMethod(), getCallOptions(), request);
    }

    /**
     */
    public server.Player waitForGuesser(server.Combination request) {
      return blockingUnaryCall(
          getChannel(), getWaitForGuesserMethod(), getCallOptions(), request);
    }

    /**
     */
    public server.Player waitForVerifier(server.Player request) {
      return blockingUnaryCall(
          getChannel(), getWaitForVerifierMethod(), getCallOptions(), request);
    }

    /**
     */
    public server.Verification guess(server.Combination request) {
      return blockingUnaryCall(
          getChannel(), getGuessMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<server.Combination> getGuesses(
        server.Player request) {
      return blockingServerStreamingCall(
          getChannel(), getGetGuessesMethod(), getCallOptions(), request);
    }

    /**
     */
    public server.VerifyAck verify(server.Verification request) {
      return blockingUnaryCall(
          getChannel(), getVerifyMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class GreeterFutureStub extends io.grpc.stub.AbstractStub<GreeterFutureStub> {
    private GreeterFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreeterFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreeterFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreeterFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<server.HelloReply> sayHello(
        server.HelloRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSayHelloMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<server.HelloReply> sayHelloAgain(
        server.HelloRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSayHelloAgainMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<server.GameDescription> createGame(
        server.GameDescription request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateGameMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<server.GamesByName> getGamesByName(
        server.GameDescription request) {
      return futureUnaryCall(
          getChannel().newCall(getGetGamesByNameMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<server.Player> joinGame(
        server.Player request) {
      return futureUnaryCall(
          getChannel().newCall(getJoinGameMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<server.Player> waitForGuesser(
        server.Combination request) {
      return futureUnaryCall(
          getChannel().newCall(getWaitForGuesserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<server.Player> waitForVerifier(
        server.Player request) {
      return futureUnaryCall(
          getChannel().newCall(getWaitForVerifierMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<server.Verification> guess(
        server.Combination request) {
      return futureUnaryCall(
          getChannel().newCall(getGuessMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<server.VerifyAck> verify(
        server.Verification request) {
      return futureUnaryCall(
          getChannel().newCall(getVerifyMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SAY_HELLO = 0;
  private static final int METHODID_SAY_HELLO_AGAIN = 1;
  private static final int METHODID_CREATE_GAME = 2;
  private static final int METHODID_GET_GAMES_BY_NAME = 3;
  private static final int METHODID_JOIN_GAME = 4;
  private static final int METHODID_WAIT_FOR_GUESSER = 5;
  private static final int METHODID_WAIT_FOR_VERIFIER = 6;
  private static final int METHODID_GUESS = 7;
  private static final int METHODID_GET_GUESSES = 8;
  private static final int METHODID_VERIFY = 9;
  private static final int METHODID_KEEP_ALIVE = 10;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GreeterImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GreeterImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SAY_HELLO:
          serviceImpl.sayHello((server.HelloRequest) request,
              (io.grpc.stub.StreamObserver<server.HelloReply>) responseObserver);
          break;
        case METHODID_SAY_HELLO_AGAIN:
          serviceImpl.sayHelloAgain((server.HelloRequest) request,
              (io.grpc.stub.StreamObserver<server.HelloReply>) responseObserver);
          break;
        case METHODID_CREATE_GAME:
          serviceImpl.createGame((server.GameDescription) request,
              (io.grpc.stub.StreamObserver<server.GameDescription>) responseObserver);
          break;
        case METHODID_GET_GAMES_BY_NAME:
          serviceImpl.getGamesByName((server.GameDescription) request,
              (io.grpc.stub.StreamObserver<server.GamesByName>) responseObserver);
          break;
        case METHODID_JOIN_GAME:
          serviceImpl.joinGame((server.Player) request,
              (io.grpc.stub.StreamObserver<server.Player>) responseObserver);
          break;
        case METHODID_WAIT_FOR_GUESSER:
          serviceImpl.waitForGuesser((server.Combination) request,
              (io.grpc.stub.StreamObserver<server.Player>) responseObserver);
          break;
        case METHODID_WAIT_FOR_VERIFIER:
          serviceImpl.waitForVerifier((server.Player) request,
              (io.grpc.stub.StreamObserver<server.Player>) responseObserver);
          break;
        case METHODID_GUESS:
          serviceImpl.guess((server.Combination) request,
              (io.grpc.stub.StreamObserver<server.Verification>) responseObserver);
          break;
        case METHODID_GET_GUESSES:
          serviceImpl.getGuesses((server.Player) request,
              (io.grpc.stub.StreamObserver<server.Combination>) responseObserver);
          break;
        case METHODID_VERIFY:
          serviceImpl.verify((server.Verification) request,
              (io.grpc.stub.StreamObserver<server.VerifyAck>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_KEEP_ALIVE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.keepAlive(
              (io.grpc.stub.StreamObserver<server.Player>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GreeterGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .addMethod(getSayHelloMethod())
              .addMethod(getSayHelloAgainMethod())
              .addMethod(getCreateGameMethod())
              .addMethod(getGetGamesByNameMethod())
              .addMethod(getJoinGameMethod())
              .addMethod(getWaitForGuesserMethod())
              .addMethod(getWaitForVerifierMethod())
              .addMethod(getKeepAliveMethod())
              .addMethod(getGuessMethod())
              .addMethod(getGetGuessesMethod())
              .addMethod(getVerifyMethod())
              .build();
        }
      }
    }
    return result;
  }
}
