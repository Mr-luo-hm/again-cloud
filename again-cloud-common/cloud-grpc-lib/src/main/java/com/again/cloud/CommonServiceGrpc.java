package com.again.cloud;

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
 * 定义通用的 Grpc 服务
 * </pre>
 */
@javax.annotation.Generated(value = "by gRPC proto compiler (version 1.15.1)", comments = "Source: message.proto")
public final class CommonServiceGrpc {

	private CommonServiceGrpc() {
	}

	public static final String SERVICE_NAME = "CommonService";

	// Static method descriptors that strictly reflect the proto.
	private static volatile io.grpc.MethodDescriptor<com.again.cloud.Request, com.again.cloud.Response> getMethodMethod;

	@io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "method",
			requestType = com.again.cloud.Request.class, responseType = com.again.cloud.Response.class,
			methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
	public static io.grpc.MethodDescriptor<com.again.cloud.Request, com.again.cloud.Response> getMethodMethod() {
		io.grpc.MethodDescriptor<com.again.cloud.Request, com.again.cloud.Response> getMethodMethod;
		if ((getMethodMethod = CommonServiceGrpc.getMethodMethod) == null) {
			synchronized (CommonServiceGrpc.class) {
				if ((getMethodMethod = CommonServiceGrpc.getMethodMethod) == null) {
					CommonServiceGrpc.getMethodMethod = getMethodMethod = io.grpc.MethodDescriptor.<com.again.cloud.Request, com.again.cloud.Response>newBuilder()
							.setType(io.grpc.MethodDescriptor.MethodType.UNARY)
							.setFullMethodName(generateFullMethodName("CommonService", "method"))
							.setSampledToLocalTracing(true)
							.setRequestMarshaller(io.grpc.protobuf.ProtoUtils
									.marshaller(com.again.cloud.Request.getDefaultInstance()))
							.setResponseMarshaller(io.grpc.protobuf.ProtoUtils
									.marshaller(com.again.cloud.Response.getDefaultInstance()))
							.setSchemaDescriptor(new CommonServiceMethodDescriptorSupplier("method")).build();
				}
			}
		}
		return getMethodMethod;
	}

	private static volatile io.grpc.MethodDescriptor<com.again.cloud.Request, com.again.cloud.Response> getListFeaturesMethod;

	@io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "ListFeatures",
			requestType = com.again.cloud.Request.class, responseType = com.again.cloud.Response.class,
			methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
	public static io.grpc.MethodDescriptor<com.again.cloud.Request, com.again.cloud.Response> getListFeaturesMethod() {
		io.grpc.MethodDescriptor<com.again.cloud.Request, com.again.cloud.Response> getListFeaturesMethod;
		if ((getListFeaturesMethod = CommonServiceGrpc.getListFeaturesMethod) == null) {
			synchronized (CommonServiceGrpc.class) {
				if ((getListFeaturesMethod = CommonServiceGrpc.getListFeaturesMethod) == null) {
					CommonServiceGrpc.getListFeaturesMethod = getListFeaturesMethod = io.grpc.MethodDescriptor.<com.again.cloud.Request, com.again.cloud.Response>newBuilder()
							.setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
							.setFullMethodName(generateFullMethodName("CommonService", "ListFeatures"))
							.setSampledToLocalTracing(true)
							.setRequestMarshaller(io.grpc.protobuf.ProtoUtils
									.marshaller(com.again.cloud.Request.getDefaultInstance()))
							.setResponseMarshaller(io.grpc.protobuf.ProtoUtils
									.marshaller(com.again.cloud.Response.getDefaultInstance()))
							.setSchemaDescriptor(new CommonServiceMethodDescriptorSupplier("ListFeatures")).build();
				}
			}
		}
		return getListFeaturesMethod;
	}

	private static volatile io.grpc.MethodDescriptor<com.again.cloud.Request, com.again.cloud.Response> getRecordRouteMethod;

	@io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "RecordRoute",
			requestType = com.again.cloud.Request.class, responseType = com.again.cloud.Response.class,
			methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
	public static io.grpc.MethodDescriptor<com.again.cloud.Request, com.again.cloud.Response> getRecordRouteMethod() {
		io.grpc.MethodDescriptor<com.again.cloud.Request, com.again.cloud.Response> getRecordRouteMethod;
		if ((getRecordRouteMethod = CommonServiceGrpc.getRecordRouteMethod) == null) {
			synchronized (CommonServiceGrpc.class) {
				if ((getRecordRouteMethod = CommonServiceGrpc.getRecordRouteMethod) == null) {
					CommonServiceGrpc.getRecordRouteMethod = getRecordRouteMethod = io.grpc.MethodDescriptor.<com.again.cloud.Request, com.again.cloud.Response>newBuilder()
							.setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
							.setFullMethodName(generateFullMethodName("CommonService", "RecordRoute"))
							.setSampledToLocalTracing(true)
							.setRequestMarshaller(io.grpc.protobuf.ProtoUtils
									.marshaller(com.again.cloud.Request.getDefaultInstance()))
							.setResponseMarshaller(io.grpc.protobuf.ProtoUtils
									.marshaller(com.again.cloud.Response.getDefaultInstance()))
							.setSchemaDescriptor(new CommonServiceMethodDescriptorSupplier("RecordRoute")).build();
				}
			}
		}
		return getRecordRouteMethod;
	}

	private static volatile io.grpc.MethodDescriptor<com.again.cloud.Request, com.again.cloud.Response> getRouteChatMethod;

	@io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "RouteChat",
			requestType = com.again.cloud.Request.class, responseType = com.again.cloud.Response.class,
			methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
	public static io.grpc.MethodDescriptor<com.again.cloud.Request, com.again.cloud.Response> getRouteChatMethod() {
		io.grpc.MethodDescriptor<com.again.cloud.Request, com.again.cloud.Response> getRouteChatMethod;
		if ((getRouteChatMethod = CommonServiceGrpc.getRouteChatMethod) == null) {
			synchronized (CommonServiceGrpc.class) {
				if ((getRouteChatMethod = CommonServiceGrpc.getRouteChatMethod) == null) {
					CommonServiceGrpc.getRouteChatMethod = getRouteChatMethod = io.grpc.MethodDescriptor.<com.again.cloud.Request, com.again.cloud.Response>newBuilder()
							.setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
							.setFullMethodName(generateFullMethodName("CommonService", "RouteChat"))
							.setSampledToLocalTracing(true)
							.setRequestMarshaller(io.grpc.protobuf.ProtoUtils
									.marshaller(com.again.cloud.Request.getDefaultInstance()))
							.setResponseMarshaller(io.grpc.protobuf.ProtoUtils
									.marshaller(com.again.cloud.Response.getDefaultInstance()))
							.setSchemaDescriptor(new CommonServiceMethodDescriptorSupplier("RouteChat")).build();
				}
			}
		}
		return getRouteChatMethod;
	}

	/**
	 * Creates a new async stub that supports all call types for the service
	 */
	public static CommonServiceStub newStub(io.grpc.Channel channel) {
		return new CommonServiceStub(channel);
	}

	/**
	 * Creates a new blocking-style stub that supports unary and streaming output calls on
	 * the service
	 */
	public static CommonServiceBlockingStub newBlockingStub(io.grpc.Channel channel) {
		return new CommonServiceBlockingStub(channel);
	}

	/**
	 * Creates a new ListenableFuture-style stub that supports unary calls on the service
	 */
	public static CommonServiceFutureStub newFutureStub(io.grpc.Channel channel) {
		return new CommonServiceFutureStub(channel);
	}

	/**
	 * <pre>
	 * 定义通用的 Grpc 服务
	 * </pre>
	 */
	public static abstract class CommonServiceImplBase implements io.grpc.BindableService {

		/**
		 * <pre>
		 * 处理请求
		 * </pre>
		 */
		public void method(com.again.cloud.Request request,
				io.grpc.stub.StreamObserver<com.again.cloud.Response> responseObserver) {
			asyncUnimplementedUnaryCall(getMethodMethod(), responseObserver);
		}

		/**
		 * <pre>
		 * 单向流
		 * </pre>
		 */
		public void listFeatures(com.again.cloud.Request request,
				io.grpc.stub.StreamObserver<com.again.cloud.Response> responseObserver) {
			asyncUnimplementedUnaryCall(getListFeaturesMethod(), responseObserver);
		}

		/**
		 * <pre>
		 * 单向流
		 * </pre>
		 */
		public io.grpc.stub.StreamObserver<com.again.cloud.Request> recordRoute(
				io.grpc.stub.StreamObserver<com.again.cloud.Response> responseObserver) {
			return asyncUnimplementedStreamingCall(getRecordRouteMethod(), responseObserver);
		}

		/**
		 * <pre>
		 * 双向流
		 * </pre>
		 */
		public io.grpc.stub.StreamObserver<com.again.cloud.Request> routeChat(
				io.grpc.stub.StreamObserver<com.again.cloud.Response> responseObserver) {
			return asyncUnimplementedStreamingCall(getRouteChatMethod(), responseObserver);
		}

		@java.lang.Override
		public final io.grpc.ServerServiceDefinition bindService() {
			return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
					.addMethod(getMethodMethod(),
							asyncUnaryCall(new MethodHandlers<com.again.cloud.Request, com.again.cloud.Response>(this,
									METHODID_METHOD)))
					.addMethod(getListFeaturesMethod(),
							asyncServerStreamingCall(
									new MethodHandlers<com.again.cloud.Request, com.again.cloud.Response>(this,
											METHODID_LIST_FEATURES)))
					.addMethod(getRecordRouteMethod(),
							asyncClientStreamingCall(
									new MethodHandlers<com.again.cloud.Request, com.again.cloud.Response>(this,
											METHODID_RECORD_ROUTE)))
					.addMethod(getRouteChatMethod(),
							asyncBidiStreamingCall(
									new MethodHandlers<com.again.cloud.Request, com.again.cloud.Response>(this,
											METHODID_ROUTE_CHAT)))
					.build();
		}

	}

	/**
	 * <pre>
	 * 定义通用的 Grpc 服务
	 * </pre>
	 */
	public static final class CommonServiceStub extends io.grpc.stub.AbstractStub<CommonServiceStub> {

		private CommonServiceStub(io.grpc.Channel channel) {
			super(channel);
		}

		private CommonServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
			super(channel, callOptions);
		}

		@java.lang.Override
		protected CommonServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
			return new CommonServiceStub(channel, callOptions);
		}

		/**
		 * <pre>
		 * 处理请求
		 * </pre>
		 */
		public void method(com.again.cloud.Request request,
				io.grpc.stub.StreamObserver<com.again.cloud.Response> responseObserver) {
			asyncUnaryCall(getChannel().newCall(getMethodMethod(), getCallOptions()), request, responseObserver);
		}

		/**
		 * <pre>
		 * 单向流
		 * </pre>
		 */
		public void listFeatures(com.again.cloud.Request request,
				io.grpc.stub.StreamObserver<com.again.cloud.Response> responseObserver) {
			asyncServerStreamingCall(getChannel().newCall(getListFeaturesMethod(), getCallOptions()), request,
					responseObserver);
		}

		/**
		 * <pre>
		 * 单向流
		 * </pre>
		 */
		public io.grpc.stub.StreamObserver<com.again.cloud.Request> recordRoute(
				io.grpc.stub.StreamObserver<com.again.cloud.Response> responseObserver) {
			return asyncClientStreamingCall(getChannel().newCall(getRecordRouteMethod(), getCallOptions()),
					responseObserver);
		}

		/**
		 * <pre>
		 * 双向流
		 * </pre>
		 */
		public io.grpc.stub.StreamObserver<com.again.cloud.Request> routeChat(
				io.grpc.stub.StreamObserver<com.again.cloud.Response> responseObserver) {
			return asyncBidiStreamingCall(getChannel().newCall(getRouteChatMethod(), getCallOptions()),
					responseObserver);
		}

	}

	/**
	 * <pre>
	 * 定义通用的 Grpc 服务
	 * </pre>
	 */
	public static final class CommonServiceBlockingStub extends io.grpc.stub.AbstractStub<CommonServiceBlockingStub> {

		private CommonServiceBlockingStub(io.grpc.Channel channel) {
			super(channel);
		}

		private CommonServiceBlockingStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
			super(channel, callOptions);
		}

		@java.lang.Override
		protected CommonServiceBlockingStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
			return new CommonServiceBlockingStub(channel, callOptions);
		}

		/**
		 * <pre>
		 * 处理请求
		 * </pre>
		 */
		public com.again.cloud.Response method(com.again.cloud.Request request) {
			return blockingUnaryCall(getChannel(), getMethodMethod(), getCallOptions(), request);
		}

		/**
		 * <pre>
		 * 单向流
		 * </pre>
		 */
		public java.util.Iterator<com.again.cloud.Response> listFeatures(com.again.cloud.Request request) {
			return blockingServerStreamingCall(getChannel(), getListFeaturesMethod(), getCallOptions(), request);
		}

	}

	/**
	 * <pre>
	 * 定义通用的 Grpc 服务
	 * </pre>
	 */
	public static final class CommonServiceFutureStub extends io.grpc.stub.AbstractStub<CommonServiceFutureStub> {

		private CommonServiceFutureStub(io.grpc.Channel channel) {
			super(channel);
		}

		private CommonServiceFutureStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
			super(channel, callOptions);
		}

		@java.lang.Override
		protected CommonServiceFutureStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
			return new CommonServiceFutureStub(channel, callOptions);
		}

		/**
		 * <pre>
		 * 处理请求
		 * </pre>
		 */
		public com.google.common.util.concurrent.ListenableFuture<com.again.cloud.Response> method(
				com.again.cloud.Request request) {
			return futureUnaryCall(getChannel().newCall(getMethodMethod(), getCallOptions()), request);
		}

	}

	private static final int METHODID_METHOD = 0;

	private static final int METHODID_LIST_FEATURES = 1;

	private static final int METHODID_RECORD_ROUTE = 2;

	private static final int METHODID_ROUTE_CHAT = 3;

	private static final class MethodHandlers<Req, Resp> implements io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
			io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
			io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
			io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {

		private final CommonServiceImplBase serviceImpl;

		private final int methodId;

		MethodHandlers(CommonServiceImplBase serviceImpl, int methodId) {
			this.serviceImpl = serviceImpl;
			this.methodId = methodId;
		}

		@java.lang.Override
		@java.lang.SuppressWarnings("unchecked")
		public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
			switch (methodId) {
			case METHODID_METHOD:
				serviceImpl.method((com.again.cloud.Request) request,
						(io.grpc.stub.StreamObserver<com.again.cloud.Response>) responseObserver);
				break;
			case METHODID_LIST_FEATURES:
				serviceImpl.listFeatures((com.again.cloud.Request) request,
						(io.grpc.stub.StreamObserver<com.again.cloud.Response>) responseObserver);
				break;
			default:
				throw new AssertionError();
			}
		}

		@java.lang.Override
		@java.lang.SuppressWarnings("unchecked")
		public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
			switch (methodId) {
			case METHODID_RECORD_ROUTE:
				return (io.grpc.stub.StreamObserver<Req>) serviceImpl
						.recordRoute((io.grpc.stub.StreamObserver<com.again.cloud.Response>) responseObserver);
			case METHODID_ROUTE_CHAT:
				return (io.grpc.stub.StreamObserver<Req>) serviceImpl
						.routeChat((io.grpc.stub.StreamObserver<com.again.cloud.Response>) responseObserver);
			default:
				throw new AssertionError();
			}
		}

	}

	private static abstract class CommonServiceBaseDescriptorSupplier
			implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {

		CommonServiceBaseDescriptorSupplier() {
		}

		@java.lang.Override
		public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
			return com.again.cloud.GrpcService.getDescriptor();
		}

		@java.lang.Override
		public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
			return getFileDescriptor().findServiceByName("CommonService");
		}

	}

	private static final class CommonServiceFileDescriptorSupplier extends CommonServiceBaseDescriptorSupplier {

		CommonServiceFileDescriptorSupplier() {
		}

	}

	private static final class CommonServiceMethodDescriptorSupplier extends CommonServiceBaseDescriptorSupplier
			implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {

		private final String methodName;

		CommonServiceMethodDescriptorSupplier(String methodName) {
			this.methodName = methodName;
		}

		@java.lang.Override
		public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
			return getServiceDescriptor().findMethodByName(methodName);
		}

	}

	private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

	public static io.grpc.ServiceDescriptor getServiceDescriptor() {
		io.grpc.ServiceDescriptor result = serviceDescriptor;
		if (result == null) {
			synchronized (CommonServiceGrpc.class) {
				result = serviceDescriptor;
				if (result == null) {
					serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
							.setSchemaDescriptor(new CommonServiceFileDescriptorSupplier()).addMethod(getMethodMethod())
							.addMethod(getListFeaturesMethod()).addMethod(getRecordRouteMethod())
							.addMethod(getRouteChatMethod()).build();
				}
			}
		}
		return result;
	}

}
