// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: helloworld.proto

package org.apache.dubbo.remoting.transport.grpc.proto;

/**
 * <pre>
 * The response message containing the greetings
 * </pre>
 *
 * Protobuf type {@code proto.GrpcReply}
 */
public  final class GrpcReply extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:proto.GrpcReply)
    GrpcReplyOrBuilder {
  // Use GrpcReply.newBuilder() to construct.
  private GrpcReply(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GrpcReply() {
    data_ = "";
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private GrpcReply(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
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

            data_ = s;
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return org.apache.dubbo.remoting.transport.grpc.proto.GrpcProto.internal_static_proto_GrpcReply_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return org.apache.dubbo.remoting.transport.grpc.proto.GrpcProto.internal_static_proto_GrpcReply_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply.class, org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply.Builder.class);
  }

  public static final int DATA_FIELD_NUMBER = 1;
  private volatile Object data_;
  /**
   * <code>string data = 1;</code>
   */
  public String getData() {
    Object ref = data_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs =
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      data_ = s;
      return s;
    }
  }
  /**
   * <code>string data = 1;</code>
   */
  public com.google.protobuf.ByteString
      getDataBytes() {
    Object ref = data_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b =
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      data_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getDataBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, data_);
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getDataBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, data_);
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @Override
  public boolean equals(final Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply)) {
      return super.equals(obj);
    }
    org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply other = (org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply) obj;

    boolean result = true;
    result = result && getData()
        .equals(other.getData());
    return result;
  }

  @Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + DATA_FIELD_NUMBER;
    hash = (53 * hash) + getData().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * The response message containing the greetings
   * </pre>
   *
   * Protobuf type {@code proto.GrpcReply}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:proto.GrpcReply)
      org.apache.dubbo.remoting.transport.grpc.proto.GrpcReplyOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.apache.dubbo.remoting.transport.grpc.proto.GrpcProto.internal_static_proto_GrpcReply_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.apache.dubbo.remoting.transport.grpc.proto.GrpcProto.internal_static_proto_GrpcReply_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply.class, org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply.Builder.class);
    }

    // Construct using org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      data_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return org.apache.dubbo.remoting.transport.grpc.proto.GrpcProto.internal_static_proto_GrpcReply_descriptor;
    }

    public org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply getDefaultInstanceForType() {
      return org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply.getDefaultInstance();
    }

    public org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply build() {
      org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply buildPartial() {
      org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply result = new org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply(this);
      result.data_ = data_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply) {
        return mergeFrom((org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply other) {
      if (other == org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply.getDefaultInstance()) return this;
      if (!other.getData().isEmpty()) {
        data_ = other.data_;
        onChanged();
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private Object data_ = "";
    /**
     * <code>string data = 1;</code>
     */
    public String getData() {
      Object ref = data_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        data_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>string data = 1;</code>
     */
    public com.google.protobuf.ByteString
        getDataBytes() {
      Object ref = data_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        data_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string data = 1;</code>
     */
    public Builder setData(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }

      data_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string data = 1;</code>
     */
    public Builder clearData() {

      data_ = getDefaultInstance().getData();
      onChanged();
      return this;
    }
    /**
     * <code>string data = 1;</code>
     */
    public Builder setDataBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);

      data_ = value;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:proto.GrpcReply)
  }

  // @@protoc_insertion_point(class_scope:proto.GrpcReply)
  private static final org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply();
  }

  public static org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GrpcReply>
      PARSER = new com.google.protobuf.AbstractParser<GrpcReply>() {
    public GrpcReply parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new GrpcReply(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GrpcReply> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<GrpcReply> getParserForType() {
    return PARSER;
  }

  public org.apache.dubbo.remoting.transport.grpc.proto.GrpcReply getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

