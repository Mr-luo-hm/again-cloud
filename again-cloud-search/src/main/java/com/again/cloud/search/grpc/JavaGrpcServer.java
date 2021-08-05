package com.again.cloud.search.grpc;

import com.again.cloud.CommonServiceGrpc;
import com.again.cloud.Request;
import com.again.cloud.Response;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.logging.Level;

@GrpcService
@Slf4j
public class JavaGrpcServer extends CommonServiceGrpc.CommonServiceImplBase {

	@Override
	public void method(Request req, StreamObserver<Response> responseObserver) {
		Response reply = Response.newBuilder().setResponse("Hello ==>").build();
		responseObserver.onNext(reply);
		responseObserver.onCompleted();
	}

	@Override
	public void listFeatures(Request request, StreamObserver<Response> responseObserver) {
		for (int i = 0; i < 100; i++) {
			Response reply = Response.newBuilder().setResponse("Hello ==>" + request.getRequest()).build();
			responseObserver.onNext(reply);
		}
		responseObserver.onCompleted();
	}

	@Override
	public StreamObserver<Request> recordRoute(StreamObserver<Response> responseObserver) {
		return new StreamObserver<Request>() {
			int pointCount;

			Request previous;

			@Override
			public void onNext(Request request) {
				pointCount++;
				previous = request;
				System.out.println(previous.getRequest());
			}

			@Override
			public void onError(Throwable throwable) {
				log.error(Level.WARNING + "Encountered error in recordRoute", throwable);
			}

			@Override
			public void onCompleted() {
				System.out.println(previous.getRequest());
				responseObserver.onNext(Response.newBuilder().setResponse("Hello ==>" + previous.getRequest()).build());
				responseObserver.onCompleted();
			}
		};
	}

	@Override
	public StreamObserver<Request> routeChat(StreamObserver<Response> responseObserver) {
		return new StreamObserver<Request>() {
			@Override
			public void onNext(Request request) {
				System.out.println(request.getRequest());
				responseObserver.onNext(Response.newBuilder().setResponse("Hello ==>" + request.getRequest()).build());
			}

			@Override
			public void onError(Throwable throwable) {
				log.error("throwable error:{}", throwable);
			}

			@Override
			public void onCompleted() {
				responseObserver.onCompleted();
			}
		};
	}

}
