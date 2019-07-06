// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Hello.proto

package server;

/**
 * Protobuf type {@code helloworld.GameDescription}
 */
public  final class GameDescription extends
    com.google.protobuf.GeneratedMessageLite<
        GameDescription, GameDescription.Builder> implements
    // @@protoc_insertion_point(message_implements:helloworld.GameDescription)
    GameDescriptionOrBuilder {
  private GameDescription() {
    gameName_ = "";
    creationDate_ = "";
    gameId_ = "";
  }
  public static final int GAMENAME_FIELD_NUMBER = 1;
  private java.lang.String gameName_;
  /**
   * <code>optional string gameName = 1;</code>
   */
  public java.lang.String getGameName() {
    return gameName_;
  }
  /**
   * <code>optional string gameName = 1;</code>
   */
  public com.google.protobuf.ByteString
      getGameNameBytes() {
    return com.google.protobuf.ByteString.copyFromUtf8(gameName_);
  }
  /**
   * <code>optional string gameName = 1;</code>
   */
  private void setGameName(
      java.lang.String value) {
    if (value == null) {
    throw new NullPointerException();
  }
  
    gameName_ = value;
  }
  /**
   * <code>optional string gameName = 1;</code>
   */
  private void clearGameName() {
    
    gameName_ = getDefaultInstance().getGameName();
  }
  /**
   * <code>optional string gameName = 1;</code>
   */
  private void setGameNameBytes(
      com.google.protobuf.ByteString value) {
    if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
    
    gameName_ = value.toStringUtf8();
  }

  public static final int CREATIONDATE_FIELD_NUMBER = 3;
  private java.lang.String creationDate_;
  /**
   * <code>optional string creationDate = 3;</code>
   */
  public java.lang.String getCreationDate() {
    return creationDate_;
  }
  /**
   * <code>optional string creationDate = 3;</code>
   */
  public com.google.protobuf.ByteString
      getCreationDateBytes() {
    return com.google.protobuf.ByteString.copyFromUtf8(creationDate_);
  }
  /**
   * <code>optional string creationDate = 3;</code>
   */
  private void setCreationDate(
      java.lang.String value) {
    if (value == null) {
    throw new NullPointerException();
  }
  
    creationDate_ = value;
  }
  /**
   * <code>optional string creationDate = 3;</code>
   */
  private void clearCreationDate() {
    
    creationDate_ = getDefaultInstance().getCreationDate();
  }
  /**
   * <code>optional string creationDate = 3;</code>
   */
  private void setCreationDateBytes(
      com.google.protobuf.ByteString value) {
    if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
    
    creationDate_ = value.toStringUtf8();
  }

  public static final int GAMEID_FIELD_NUMBER = 2;
  private java.lang.String gameId_;
  /**
   * <code>optional string gameId = 2;</code>
   */
  public java.lang.String getGameId() {
    return gameId_;
  }
  /**
   * <code>optional string gameId = 2;</code>
   */
  public com.google.protobuf.ByteString
      getGameIdBytes() {
    return com.google.protobuf.ByteString.copyFromUtf8(gameId_);
  }
  /**
   * <code>optional string gameId = 2;</code>
   */
  private void setGameId(
      java.lang.String value) {
    if (value == null) {
    throw new NullPointerException();
  }
  
    gameId_ = value;
  }
  /**
   * <code>optional string gameId = 2;</code>
   */
  private void clearGameId() {
    
    gameId_ = getDefaultInstance().getGameId();
  }
  /**
   * <code>optional string gameId = 2;</code>
   */
  private void setGameIdBytes(
      com.google.protobuf.ByteString value) {
    if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
    
    gameId_ = value.toStringUtf8();
  }

  public static final int GUESSER_FIELD_NUMBER = 4;
  private server.Player guesser_;
  /**
   * <code>optional .helloworld.Player guesser = 4;</code>
   */
  public boolean hasGuesser() {
    return guesser_ != null;
  }
  /**
   * <code>optional .helloworld.Player guesser = 4;</code>
   */
  public server.Player getGuesser() {
    return guesser_ == null ? server.Player.getDefaultInstance() : guesser_;
  }
  /**
   * <code>optional .helloworld.Player guesser = 4;</code>
   */
  private void setGuesser(server.Player value) {
    if (value == null) {
      throw new NullPointerException();
    }
    guesser_ = value;
    
    }
  /**
   * <code>optional .helloworld.Player guesser = 4;</code>
   */
  private void setGuesser(
      server.Player.Builder builderForValue) {
    guesser_ = builderForValue.build();
    
  }
  /**
   * <code>optional .helloworld.Player guesser = 4;</code>
   */
  private void mergeGuesser(server.Player value) {
    if (guesser_ != null &&
        guesser_ != server.Player.getDefaultInstance()) {
      guesser_ =
        server.Player.newBuilder(guesser_).mergeFrom(value).buildPartial();
    } else {
      guesser_ = value;
    }
    
  }
  /**
   * <code>optional .helloworld.Player guesser = 4;</code>
   */
  private void clearGuesser() {  guesser_ = null;
    
  }

  public static final int VERIFIER_FIELD_NUMBER = 5;
  private server.Player verifier_;
  /**
   * <code>optional .helloworld.Player verifier = 5;</code>
   */
  public boolean hasVerifier() {
    return verifier_ != null;
  }
  /**
   * <code>optional .helloworld.Player verifier = 5;</code>
   */
  public server.Player getVerifier() {
    return verifier_ == null ? server.Player.getDefaultInstance() : verifier_;
  }
  /**
   * <code>optional .helloworld.Player verifier = 5;</code>
   */
  private void setVerifier(server.Player value) {
    if (value == null) {
      throw new NullPointerException();
    }
    verifier_ = value;
    
    }
  /**
   * <code>optional .helloworld.Player verifier = 5;</code>
   */
  private void setVerifier(
      server.Player.Builder builderForValue) {
    verifier_ = builderForValue.build();
    
  }
  /**
   * <code>optional .helloworld.Player verifier = 5;</code>
   */
  private void mergeVerifier(server.Player value) {
    if (verifier_ != null &&
        verifier_ != server.Player.getDefaultInstance()) {
      verifier_ =
        server.Player.newBuilder(verifier_).mergeFrom(value).buildPartial();
    } else {
      verifier_ = value;
    }
    
  }
  /**
   * <code>optional .helloworld.Player verifier = 5;</code>
   */
  private void clearVerifier() {  verifier_ = null;
    
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!gameName_.isEmpty()) {
      output.writeString(1, getGameName());
    }
    if (!gameId_.isEmpty()) {
      output.writeString(2, getGameId());
    }
    if (!creationDate_.isEmpty()) {
      output.writeString(3, getCreationDate());
    }
    if (guesser_ != null) {
      output.writeMessage(4, getGuesser());
    }
    if (verifier_ != null) {
      output.writeMessage(5, getVerifier());
    }
  }

  public int getSerializedSize() {
    int size = memoizedSerializedSize;
    if (size != -1) return size;

    size = 0;
    if (!gameName_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeStringSize(1, getGameName());
    }
    if (!gameId_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeStringSize(2, getGameId());
    }
    if (!creationDate_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeStringSize(3, getCreationDate());
    }
    if (guesser_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(4, getGuesser());
    }
    if (verifier_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(5, getVerifier());
    }
    memoizedSerializedSize = size;
    return size;
  }

  public static server.GameDescription parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static server.GameDescription parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static server.GameDescription parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static server.GameDescription parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static server.GameDescription parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input);
  }
  public static server.GameDescription parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input, extensionRegistry);
  }
  public static server.GameDescription parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return parseDelimitedFrom(DEFAULT_INSTANCE, input);
  }
  public static server.GameDescription parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
  }
  public static server.GameDescription parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input);
  }
  public static server.GameDescription parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input, extensionRegistry);
  }

  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(server.GameDescription prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }

  /**
   * Protobuf type {@code helloworld.GameDescription}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageLite.Builder<
        server.GameDescription, Builder> implements
      // @@protoc_insertion_point(builder_implements:helloworld.GameDescription)
      server.GameDescriptionOrBuilder {
    // Construct using server.GameDescription.newBuilder()
    private Builder() {
      super(DEFAULT_INSTANCE);
    }


    /**
     * <code>optional string gameName = 1;</code>
     */
    public java.lang.String getGameName() {
      return instance.getGameName();
    }
    /**
     * <code>optional string gameName = 1;</code>
     */
    public com.google.protobuf.ByteString
        getGameNameBytes() {
      return instance.getGameNameBytes();
    }
    /**
     * <code>optional string gameName = 1;</code>
     */
    public Builder setGameName(
        java.lang.String value) {
      copyOnWrite();
      instance.setGameName(value);
      return this;
    }
    /**
     * <code>optional string gameName = 1;</code>
     */
    public Builder clearGameName() {
      copyOnWrite();
      instance.clearGameName();
      return this;
    }
    /**
     * <code>optional string gameName = 1;</code>
     */
    public Builder setGameNameBytes(
        com.google.protobuf.ByteString value) {
      copyOnWrite();
      instance.setGameNameBytes(value);
      return this;
    }

    /**
     * <code>optional string creationDate = 3;</code>
     */
    public java.lang.String getCreationDate() {
      return instance.getCreationDate();
    }
    /**
     * <code>optional string creationDate = 3;</code>
     */
    public com.google.protobuf.ByteString
        getCreationDateBytes() {
      return instance.getCreationDateBytes();
    }
    /**
     * <code>optional string creationDate = 3;</code>
     */
    public Builder setCreationDate(
        java.lang.String value) {
      copyOnWrite();
      instance.setCreationDate(value);
      return this;
    }
    /**
     * <code>optional string creationDate = 3;</code>
     */
    public Builder clearCreationDate() {
      copyOnWrite();
      instance.clearCreationDate();
      return this;
    }
    /**
     * <code>optional string creationDate = 3;</code>
     */
    public Builder setCreationDateBytes(
        com.google.protobuf.ByteString value) {
      copyOnWrite();
      instance.setCreationDateBytes(value);
      return this;
    }

    /**
     * <code>optional string gameId = 2;</code>
     */
    public java.lang.String getGameId() {
      return instance.getGameId();
    }
    /**
     * <code>optional string gameId = 2;</code>
     */
    public com.google.protobuf.ByteString
        getGameIdBytes() {
      return instance.getGameIdBytes();
    }
    /**
     * <code>optional string gameId = 2;</code>
     */
    public Builder setGameId(
        java.lang.String value) {
      copyOnWrite();
      instance.setGameId(value);
      return this;
    }
    /**
     * <code>optional string gameId = 2;</code>
     */
    public Builder clearGameId() {
      copyOnWrite();
      instance.clearGameId();
      return this;
    }
    /**
     * <code>optional string gameId = 2;</code>
     */
    public Builder setGameIdBytes(
        com.google.protobuf.ByteString value) {
      copyOnWrite();
      instance.setGameIdBytes(value);
      return this;
    }

    /**
     * <code>optional .helloworld.Player guesser = 4;</code>
     */
    public boolean hasGuesser() {
      return instance.hasGuesser();
    }
    /**
     * <code>optional .helloworld.Player guesser = 4;</code>
     */
    public server.Player getGuesser() {
      return instance.getGuesser();
    }
    /**
     * <code>optional .helloworld.Player guesser = 4;</code>
     */
    public Builder setGuesser(server.Player value) {
      copyOnWrite();
      instance.setGuesser(value);
      return this;
      }
    /**
     * <code>optional .helloworld.Player guesser = 4;</code>
     */
    public Builder setGuesser(
        server.Player.Builder builderForValue) {
      copyOnWrite();
      instance.setGuesser(builderForValue);
      return this;
    }
    /**
     * <code>optional .helloworld.Player guesser = 4;</code>
     */
    public Builder mergeGuesser(server.Player value) {
      copyOnWrite();
      instance.mergeGuesser(value);
      return this;
    }
    /**
     * <code>optional .helloworld.Player guesser = 4;</code>
     */
    public Builder clearGuesser() {  copyOnWrite();
      instance.clearGuesser();
      return this;
    }

    /**
     * <code>optional .helloworld.Player verifier = 5;</code>
     */
    public boolean hasVerifier() {
      return instance.hasVerifier();
    }
    /**
     * <code>optional .helloworld.Player verifier = 5;</code>
     */
    public server.Player getVerifier() {
      return instance.getVerifier();
    }
    /**
     * <code>optional .helloworld.Player verifier = 5;</code>
     */
    public Builder setVerifier(server.Player value) {
      copyOnWrite();
      instance.setVerifier(value);
      return this;
      }
    /**
     * <code>optional .helloworld.Player verifier = 5;</code>
     */
    public Builder setVerifier(
        server.Player.Builder builderForValue) {
      copyOnWrite();
      instance.setVerifier(builderForValue);
      return this;
    }
    /**
     * <code>optional .helloworld.Player verifier = 5;</code>
     */
    public Builder mergeVerifier(server.Player value) {
      copyOnWrite();
      instance.mergeVerifier(value);
      return this;
    }
    /**
     * <code>optional .helloworld.Player verifier = 5;</code>
     */
    public Builder clearVerifier() {  copyOnWrite();
      instance.clearVerifier();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:helloworld.GameDescription)
  }
  protected final Object dynamicMethod(
      com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
      Object arg0, Object arg1) {
    switch (method) {
      case NEW_MUTABLE_INSTANCE: {
        return new server.GameDescription();
      }
      case IS_INITIALIZED: {
        return DEFAULT_INSTANCE;
      }
      case MAKE_IMMUTABLE: {
        return null;
      }
      case NEW_BUILDER: {
        return new Builder();
      }
      case VISIT: {
        Visitor visitor = (Visitor) arg0;
        server.GameDescription other = (server.GameDescription) arg1;
        gameName_ = visitor.visitString(!gameName_.isEmpty(), gameName_,
            !other.gameName_.isEmpty(), other.gameName_);
        creationDate_ = visitor.visitString(!creationDate_.isEmpty(), creationDate_,
            !other.creationDate_.isEmpty(), other.creationDate_);
        gameId_ = visitor.visitString(!gameId_.isEmpty(), gameId_,
            !other.gameId_.isEmpty(), other.gameId_);
        guesser_ = visitor.visitMessage(guesser_, other.guesser_);
        verifier_ = visitor.visitMessage(verifier_, other.verifier_);
        if (visitor == com.google.protobuf.GeneratedMessageLite.MergeFromVisitor
            .INSTANCE) {
        }
        return this;
      }
      case MERGE_FROM_STREAM: {
        com.google.protobuf.CodedInputStream input =
            (com.google.protobuf.CodedInputStream) arg0;
        com.google.protobuf.ExtensionRegistryLite extensionRegistry =
            (com.google.protobuf.ExtensionRegistryLite) arg1;
        try {
          boolean done = false;
          while (!done) {
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                break;
              default: {
                if (!input.skipField(tag)) {
                  done = true;
                }
                break;
              }
              case 10: {
                String s = input.readStringRequireUtf8();

                gameName_ = s;
                break;
              }
              case 18: {
                String s = input.readStringRequireUtf8();

                gameId_ = s;
                break;
              }
              case 26: {
                String s = input.readStringRequireUtf8();

                creationDate_ = s;
                break;
              }
              case 34: {
                server.Player.Builder subBuilder = null;
                if (guesser_ != null) {
                  subBuilder = guesser_.toBuilder();
                }
                guesser_ = input.readMessage(server.Player.parser(), extensionRegistry);
                if (subBuilder != null) {
                  subBuilder.mergeFrom(guesser_);
                  guesser_ = subBuilder.buildPartial();
                }

                break;
              }
              case 42: {
                server.Player.Builder subBuilder = null;
                if (verifier_ != null) {
                  subBuilder = verifier_.toBuilder();
                }
                verifier_ = input.readMessage(server.Player.parser(), extensionRegistry);
                if (subBuilder != null) {
                  subBuilder.mergeFrom(verifier_);
                  verifier_ = subBuilder.buildPartial();
                }

                break;
              }
            }
          }
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw new RuntimeException(e.setUnfinishedMessage(this));
        } catch (java.io.IOException e) {
          throw new RuntimeException(
              new com.google.protobuf.InvalidProtocolBufferException(
                  e.getMessage()).setUnfinishedMessage(this));
        } finally {
        }
      }
      case GET_DEFAULT_INSTANCE: {
        return DEFAULT_INSTANCE;
      }
      case GET_PARSER: {
        if (PARSER == null) {    synchronized (server.GameDescription.class) {
            if (PARSER == null) {
              PARSER = new DefaultInstanceBasedParser(DEFAULT_INSTANCE);
            }
          }
        }
        return PARSER;
      }
    }
    throw new UnsupportedOperationException();
  }


  // @@protoc_insertion_point(class_scope:helloworld.GameDescription)
  private static final server.GameDescription DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new GameDescription();
    DEFAULT_INSTANCE.makeImmutable();
  }

  public static server.GameDescription getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static volatile com.google.protobuf.Parser<GameDescription> PARSER;

  public static com.google.protobuf.Parser<GameDescription> parser() {
    return DEFAULT_INSTANCE.getParserForType();
  }
}
