// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Hello.proto

package server;

/**
 * Protobuf enum {@code helloworld.Color}
 */
public enum Color
    implements com.google.protobuf.Internal.EnumLite {
  /**
   * <code>RED = 0;</code>
   */
  RED(0),
  /**
   * <code>BLUE = 1;</code>
   */
  BLUE(1),
  /**
   * <code>GREEN = 2;</code>
   */
  GREEN(2),
  /**
   * <code>YELLOW = 3;</code>
   */
  YELLOW(3),
  /**
   * <code>PURPLE = 4;</code>
   */
  PURPLE(4),
  /**
   * <code>ORANGE = 5;</code>
   */
  ORANGE(5),
  UNRECOGNIZED(-1),
  ;

  /**
   * <code>RED = 0;</code>
   */
  public static final int RED_VALUE = 0;
  /**
   * <code>BLUE = 1;</code>
   */
  public static final int BLUE_VALUE = 1;
  /**
   * <code>GREEN = 2;</code>
   */
  public static final int GREEN_VALUE = 2;
  /**
   * <code>YELLOW = 3;</code>
   */
  public static final int YELLOW_VALUE = 3;
  /**
   * <code>PURPLE = 4;</code>
   */
  public static final int PURPLE_VALUE = 4;
  /**
   * <code>ORANGE = 5;</code>
   */
  public static final int ORANGE_VALUE = 5;


  public final int getNumber() {
    return value;
  }

  /**
   * @deprecated Use {@link #forNumber(int)} instead.
   */
  @java.lang.Deprecated
  public static Color valueOf(int value) {
    return forNumber(value);
  }

  public static Color forNumber(int value) {
    switch (value) {
      case 0: return RED;
      case 1: return BLUE;
      case 2: return GREEN;
      case 3: return YELLOW;
      case 4: return PURPLE;
      case 5: return ORANGE;
      default: return null;
    }
  }

  public static com.google.protobuf.Internal.EnumLiteMap<Color>
      internalGetValueMap() {
    return internalValueMap;
  }
  private static final com.google.protobuf.Internal.EnumLiteMap<
      Color> internalValueMap =
        new com.google.protobuf.Internal.EnumLiteMap<Color>() {
          public Color findValueByNumber(int number) {
            return Color.forNumber(number);
          }
        };

  private final int value;

  private Color(int value) {
    this.value = value;
  }

  // @@protoc_insertion_point(enum_scope:helloworld.Color)
}

