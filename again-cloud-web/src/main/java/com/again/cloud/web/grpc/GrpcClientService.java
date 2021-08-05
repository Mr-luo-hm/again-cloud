package com.again.cloud.web.grpc;

import com.again.cloud.CommonServiceGrpc;
import com.again.cloud.Request;
import com.again.cloud.Response;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/**
 * @author create by 罗英杰 on 2021/8/5
 * @description: grpc 客户端
 */
@Service
@Slf4j
public class GrpcClientService {

	@GrpcClient("again-search")
	private CommonServiceGrpc.CommonServiceBlockingStub simpleStub;

	@GrpcClient("again-search")
	private CommonServiceGrpc.CommonServiceStub commonServiceStub;

	public String sendMessage(final String name) {
		try {
			final Response response = this.simpleStub.method(Request.newBuilder().setRequest(name).build());
			return response.getResponse();
		}
		catch (final StatusRuntimeException e) {
			return "FAILED with " + e.getStatus().getCode().name();
		}

	}

	public void sendMessage2(final String name) {
		try {
			Iterator<Response> iterator = this.simpleStub.listFeatures(Request.newBuilder().setRequest(name).build());
			iterator.forEachRemaining(ele -> System.out.println(ele.getResponse()));
		}
		catch (final StatusRuntimeException ignored) {

		}
	}

	public void sendMessage3(final String name) throws InterruptedException {
		final CountDownLatch finishLatch = new CountDownLatch(1);
		StreamObserver<Response> requestStreamObserver = new StreamObserver<Response>() {
			@Override
			public void onNext(Response o) {
				log.info(o.getResponse());
			}

			@Override
			public void onError(Throwable throwable) {
				Status status = Status.fromThrowable(throwable);
				log.info(Level.WARNING + "RecordRoute Failed: {0}", status);
				finishLatch.countDown();
			}

			@Override
			public void onCompleted() {
				log.info("Finished RecordRoute");
				finishLatch.countDown();
			}
		};
		StreamObserver<Request> requestObserver = this.commonServiceStub.recordRoute(requestStreamObserver);
		try {
			// Send numPoints points randomly selected from the features list.
			for (int i = 0; i < 100; i++) {
				requestObserver.onNext(Request.newBuilder().setRequest(name + i).build());
			}
		}
		catch (RuntimeException e) {
			// Cancel RPC
			requestObserver.onError(e);
			throw e;
		}
		// Mark the end of requests
		requestObserver.onCompleted();

		// Receiving happens asynchronously
		finishLatch.await(1, TimeUnit.SECONDS);
	}

	public void sendMessage4(final String name) throws InterruptedException {
		final CountDownLatch finishLatch = new CountDownLatch(1);
		StreamObserver<Response> requestStreamObserver = new StreamObserver<Response>() {
			@Override
			public void onNext(Response o) {
				log.info(o.getResponse());
			}

			@Override
			public void onError(Throwable throwable) {
				Status status = Status.fromThrowable(throwable);
				log.info(Level.WARNING + "RecordRoute Failed: {0}", status);
				finishLatch.countDown();
			}

			@Override
			public void onCompleted() {
				log.info("Finished RecordRoute");
				finishLatch.countDown();
			}
		};
		StreamObserver<Request> requestObserver = this.commonServiceStub.routeChat(requestStreamObserver);
		try {
			// Send numPoints points randomly selected from the features list.
			for (int i = 0; i < 10; i++) {
				requestObserver.onNext(Request.newBuilder().setRequest(name + i).build());
			}
		}
		catch (RuntimeException e) {
			// Cancel RPC
			requestObserver.onError(e);
			throw e;
		}
		// Mark the end of requests
		requestObserver.onCompleted();

		// Receiving happens asynchronously
		finishLatch.await(1, TimeUnit.SECONDS);
	}

}
