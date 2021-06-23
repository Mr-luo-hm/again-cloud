package com.again.cloud.gateway.constants;

public interface ErrorCodeConstants {

	int OK = 1;// 正常

	int JWTERROR = -1;

	int FEEERROR = -2;// 费用的错误

	int IDEMPOTENTERROR = -3;

	int LIMITDAYERROR = -4;

	int LIMITHOURERROR = -5;

	int LIMITMINERROR = -6;

	int ROUTTINGERROR = -7;

	int SYSTEMPARAMTERSERROR = -8;

	int SIGNERROR = -9;

	int TIMESTAMPERROR = -10;

	int APPKEYERROR = -11;

}
