// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Hello.proto

package server;

/**
 * Protobuf type {@code helloworld.Combination}
 */
public  final class Combination extends
    com.google.protobuf.GeneratedMessageLite<
        Combination, Combination.Builder> implements
    // @@protoc_insertion_point(message_implements:helloworld.Combination)
    CombinationOrBuilder {
  private Combination() {
  }
  public static final int PLAYER_FIELD_NUMBER = 1;
  private server.Player player_;
  /**
   * <code>optional .helloworld.Player player = 1;</code>
   */
  public boolean hasPlayer() {
    return player_ != null;
  }
  /**
   * <code>optional .helloworld.Player player = 1;</code>
   */
  public server.Player getPlayer() {
    return player_ == null ? server.Player.getDefaultInstance() : player_;
  }
  /**
   * <code>optional .helloworld.Player player = 1;</code>
   */
  private void setPlayer(server.Player value) {
    if (value == null) {
      throw new NullPointerException();
    }
    player_ = value;
    
    }
  /**
   * <code>optional .helloworld.Player player = 1;</code>
   */
  private void setPlayer(
      server.Player.Builder builderForValue) {
    player_ = builderForValue.build();
    
  }
  /**
   * <code>optional .helloworld.Player player = 1;</code>
   */
  private void mergePlayer(server.Player value) {
    if (player_ != null &&
        player_ != server.Player.getDefaultInstance()) {
      player_ =
        server.Player.newBuilder(player_).mergeFrom(value).buildPartial();
    } else {
      player_ = value;
    }
    
  }
  /**
   * <code>optional .helloworld.Player player = 1;</code>
   */
  private void clearPlayer() {  player_ = null;
    
  }

  public static final int FIRST_FIELD_NUMBER = 2;
  private int first_;
  /**
   * <code>optional .helloworld.Color first = 2;</code>
   */
  public int getFirstValue() {
    return first_;
  }
  /**
   * <code>optional .helloworld.Color first = 2;</code>
   */
  public server.Color getFirst() {
    server.Color result = server.Color.forNumber(first_);
    return result == null ? server.Color.UNRECOGNIZED : result;
  }
  /**
   * <code>optional .helloworld.Color first = 2;</code>
   */
  private void setFirstValue(int value) {
      first_ = value;
  }
  /**
   * <code>optional .helloworld.Color first = 2;</code>
   */
  private void setFirst(server.Color value) {
    if (value == null) {
      throw new NullPointerException();
    }
    
    first_ = value.getNumber();
  }
  /**
   * <code>optional .helloworld.Color first = 2;</code>
   */
  private void clearFirst() {
    
    first_ = 0;
  }

  public static final int SECOND_FIELD_NUMBER = 3;
  private int second_;
  /**
   * <code>optional .helloworld.Color second = 3;</code>
   */
  public int getSecondValue() {
    return second_;
  }
  /**
   * <code>optional .helloworld.Color second = 3;</code>
   */
  public server.Color getSecond() {
    server.Color result = server.Color.forNumber(second_);
    return result == null ? server.Color.UNRECOGNIZED : result;
  }
  /**
   * <code>optional .helloworld.Color second = 3;</code>
   */
  private void setSecondValue(int value) {
      second_ = value;
  }
  /**
   * <code>optional .helloworld.Color second = 3;</code>
   */
  private void setSecond(server.Color value) {
    if (value == null) {
      throw new NullPointerException();
    }
    
    second_ = value.getNumber();
  }
  /**
   * <code>optional .helloworld.Color second = 3;</code>
   */
  private void clearSecond() {
    
    second_ = 0;
  }

  public static final int THIRD_FIELD_NUMBER = 4;
  private int third_;
  /**
   * <code>optional .helloworld.Color third = 4;</code>
   */
  public int getThirdValue() {
    return third_;
  }
  /**
   * <code>optional .helloworld.Color third = 4;</code>
   */
  public server.Color getThird() {
    server.Color result = server.Color.forNumber(third_);
    return result == null ? server.Color.UNRECOGNIZED : result;
  }
  /**
   * <code>optional .helloworld.Color third = 4;</code>
   */
  private void setThirdValue(int value) {
      third_ = value;
  }
  /**
   * <code>optional .helloworld.Color third = 4;</code>
   */
  private void setThird(server.Color value) {
    if (value == null) {
      throw new NullPointerException();
    }
    
    third_ = value.getNumber();
  }
  /**
   * <code>optional .helloworld.Color third = 4;</code>
   */
  private void clearThird() {
    
    third_ = 0;
  }

  public static final int FOURTH_FIELD_NUMBER = 5;
  private int fourth_;
  /**
   * <code>optional .helloworld.Color fourth = 5;</code>
   */
  public int getFourthValue() {
    return fourth_;
  }
  /**
   * <code>optional .helloworld.Color fourth = 5;</code>
   */
  public server.Color getFourth() {
    server.Color result = server.Color.forNumber(fourth_);
    return result == null ? server.Color.UNRECOGNIZED : result;
  }
  /**
   * <code>optional .helloworld.Color fourth = 5;</code>
   */
  private void setFourthValue(int value) {
      fourth_ = value;
  }
  /**
   * <code>optional .helloworld.Color fourth = 5;</code>
   */
  private void setFourth(server.Color value) {
    if (value == null) {
      throw new NullPointerException();
    }
    
    fourth_ = value.getNumber();
  }
  /**
   * <code>optional .helloworld.Color fourth = 5;</code>
   */
  private void clearFourth() {
    
    fourth_ = 0;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (player_ != null) {
      output.writeMessage(1, getPlayer());
    }
    if (first_ != server.Color.RED.getNumber()) {
      output.writeEnum(2, first_);
    }
    if (second_ != server.Color.RED.getNumber()) {
      output.writeEnum(3, second_);
    }
    if (third_ != server.Color.RED.getNumber()) {
      output.writeEnum(4, third_);
    }
    if (fourth_ != server.Color.RED.getNumber()) {
      output.writeEnum(5, fourth_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSerializedSize;
    if (size != -1) return size;

    size = 0;
    if (player_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getPlayer());
    }
    if (first_ != server.Color.RED.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(2, first_);
    }
    if (second_ != server.Color.RED.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(3, second_);
    }
    if (third_ != server.Color.RED.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(4, third_);
    }
    if (fourth_ != server.Color.RED.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(5, fourth_);
    }
    memoizedSerializedSize = size;
    return size;
  }

  public static server.Combination parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static server.Combination parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static server.Combination parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data);
  }
  public static server.Combination parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, data, extensionRegistry);
  }
  public static server.Combination parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input);
  }
  public static server.Combination parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input, extensionRegistry);
  }
  public static server.Combination parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return parseDelimitedFrom(DEFAULT_INSTANCE, input);
  }
  public static server.Combination parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return parseDelimitedFrom(DEFAULT_INSTANCE, input, extensionRegistry);
  }
  public static server.Combination parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input);
  }
  public static server.Combination parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageLite.parseFrom(
        DEFAULT_INSTANCE, input, extensionRegistry);
  }

  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(server.Combination prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }

  /**
   * Protobuf type {@code helloworld.Combination}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageLite.Builder<
        server.Combination, Builder> implements
      // @@protoc_insertion_point(builder_implements:helloworld.Combination)
      server.CombinationOrBuilder {
    // Construct using server.Combination.newBuilder()
    private Builder() {
      super(DEFAULT_INSTANCE);
    }


    /**
     * <code>optional .helloworld.Player player = 1;</code>
     */
    public boolean hasPlayer() {
      return instance.hasPlayer();
    }
    /**
     * <code>optional .helloworld.Player player = 1;</code>
     */
    public server.Player getPlayer() {
      return instance.getPlayer();
    }
    /**
     * <code>optional .helloworld.Player player = 1;</code>
     */
    public Builder setPlayer(server.Player value) {
      copyOnWrite();
      instance.setPlayer(value);
      return this;
      }
    /**
     * <code>optional .helloworld.Player player = 1;</code>
     */
    public Builder setPlayer(
        server.Player.Builder builderForValue) {
      copyOnWrite();
      instance.setPlayer(builderForValue);
      return this;
    }
    /**
     * <code>optional .helloworld.Player player = 1;</code>
     */
    public Builder mergePlayer(server.Player value) {
      copyOnWrite();
      instance.mergePlayer(value);
      return this;
    }
    /**
     * <code>optional .helloworld.Player player = 1;</code>
     */
    public Builder clearPlayer() {  copyOnWrite();
      instance.clearPlayer();
      return this;
    }

    /**
     * <code>optional .helloworld.Color first = 2;</code>
     */
    public int getFirstValue() {
      return instance.getFirstValue();
    }
    /**
     * <code>optional .helloworld.Color first = 2;</code>
     */
    public Builder setFirstValue(int value) {
      copyOnWrite();
      instance.setFirstValue(value);
      return this;
    }
    /**
     * <code>optional .helloworld.Color first = 2;</code>
     */
    public server.Color getFirst() {
      return instance.getFirst();
    }
    /**
     * <code>optional .helloworld.Color first = 2;</code>
     */
    public Builder setFirst(server.Color value) {
      copyOnWrite();
      instance.setFirst(value);
      return this;
    }
    /**
     * <code>optional .helloworld.Color first = 2;</code>
     */
    public Builder clearFirst() {
      copyOnWrite();
      instance.clearFirst();
      return this;
    }

    /**
     * <code>optional .helloworld.Color second = 3;</code>
     */
    public int getSecondValue() {
      return instance.getSecondValue();
    }
    /**
     * <code>optional .helloworld.Color second = 3;</code>
     */
    public Builder setSecondValue(int value) {
      copyOnWrite();
      instance.setSecondValue(value);
      return this;
    }
    /**
     * <code>optional .helloworld.Color second = 3;</code>
     */
    public server.Color getSecond() {
      return instance.getSecond();
    }
    /**
     * <code>optional .helloworld.Color second = 3;</code>
     */
    public Builder setSecond(server.Color value) {
      copyOnWrite();
      instance.setSecond(value);
      return this;
    }
    /**
     * <code>optional .helloworld.Color second = 3;</code>
     */
    public Builder clearSecond() {
      copyOnWrite();
      instance.clearSecond();
      return this;
    }

    /**
     * <code>optional .helloworld.Color third = 4;</code>
     */
    public int getThirdValue() {
      return instance.getThirdValue();
    }
    /**
     * <code>optional .helloworld.Color third = 4;</code>
     */
    public Builder setThirdValue(int value) {
      copyOnWrite();
      instance.setThirdValue(value);
      return this;
    }
    /**
     * <code>optional .helloworld.Color third = 4;</code>
     */
    public server.Color getThird() {
      return instance.getThird();
    }
    /**
     * <code>optional .helloworld.Color third = 4;</code>
     */
    public Builder setThird(server.Color value) {
      copyOnWrite();
      instance.setThird(value);
      return this;
    }
    /**
     * <code>optional .helloworld.Color third = 4;</code>
     */
    public Builder clearThird() {
      copyOnWrite();
      instance.clearThird();
      return this;
    }

    /**
     * <code>optional .helloworld.Color fourth = 5;</code>
     */
    public int getFourthValue() {
      return instance.getFourthValue();
    }
    /**
     * <code>optional .helloworld.Color fourth = 5;</code>
     */
    public Builder setFourthValue(int value) {
      copyOnWrite();
      instance.setFourthValue(value);
      return this;
    }
    /**
     * <code>optional .helloworld.Color fourth = 5;</code>
     */
    public server.Color getFourth() {
      return instance.getFourth();
    }
    /**
     * <code>optional .helloworld.Color fourth = 5;</code>
     */
    public Builder setFourth(server.Color value) {
      copyOnWrite();
      instance.setFourth(value);
      return this;
    }
    /**
     * <code>optional .helloworld.Color fourth = 5;</code>
     */
    public Builder clearFourth() {
      copyOnWrite();
      instance.clearFourth();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:helloworld.Combination)
  }
  protected final Object dynamicMethod(
      com.google.protobuf.GeneratedMessageLite.MethodToInvoke method,
      Object arg0, Object arg1) {
    switch (method) {
      case NEW_MUTABLE_INSTANCE: {
        return new server.Combination();
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
        server.Combination other = (server.Combination) arg1;
        player_ = visitor.visitMessage(player_, other.player_);
        first_ = visitor.visitInt(first_ != 0, first_,    other.first_ != 0, other.first_);
        second_ = visitor.visitInt(second_ != 0, second_,    other.second_ != 0, other.second_);
        third_ = visitor.visitInt(third_ != 0, third_,    other.third_ != 0, other.third_);
        fourth_ = visitor.visitInt(fourth_ != 0, fourth_,    other.fourth_ != 0, other.fourth_);
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
                server.Player.Builder subBuilder = null;
                if (player_ != null) {
                  subBuilder = player_.toBuilder();
                }
                player_ = input.readMessage(server.Player.parser(), extensionRegistry);
                if (subBuilder != null) {
                  subBuilder.mergeFrom(player_);
                  player_ = subBuilder.buildPartial();
                }

                break;
              }
              case 16: {
                int rawValue = input.readEnum();

                first_ = rawValue;
                break;
              }
              case 24: {
                int rawValue = input.readEnum();

                second_ = rawValue;
                break;
              }
              case 32: {
                int rawValue = input.readEnum();

                third_ = rawValue;
                break;
              }
              case 40: {
                int rawValue = input.readEnum();

                fourth_ = rawValue;
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
        if (PARSER == null) {    synchronized (server.Combination.class) {
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


  // @@protoc_insertion_point(class_scope:helloworld.Combination)
  private static final server.Combination DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new Combination();
    DEFAULT_INSTANCE.makeImmutable();
  }

  public static server.Combination getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static volatile com.google.protobuf.Parser<Combination> PARSER;

  public static com.google.protobuf.Parser<Combination> parser() {
    return DEFAULT_INSTANCE.getParserForType();
  }
}

