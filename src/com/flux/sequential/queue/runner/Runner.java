package com.flux.sequential.queue.runner;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

import javax.naming.InsufficientResourcesException;

import com.flux.sequential.queue.QueueManager;

public class Runner {

	private static final int queueCount = 5;

	/**
	 * @param args
	 * @throws InsufficientResourcesException
	 */
	public static void main(String[] args) throws InsufficientResourcesException {
		double newDouble = 7.0;
		long doubleRawBits = Double.doubleToRawLongBits(newDouble);
		long valBits = Double.doubleToLongBits(newDouble);
		int sign = ((valBits >> 63) == 0 ? 1 : -1);
		int exponent = (int) ((valBits >> 52) & 0x7ffL);
		long significand = (exponent == 0 ? (valBits & ((1L << 52) - 1)) << 1 : (valBits & ((1L << 52) - 1)) | (1L << 52));
		exponent -= 1075;
		BigInteger intVal;
		if (significand == 0) {
			intVal = BigInteger.ZERO;
			int intCompact = 0;
			int precision = 1;
			return;
		}

		// Normalize
		while ((significand & 1) == 0) { // i.e., significand is even
			significand >>= 1;
			exponent++;
		}

		// Calculate intVal and scale
		intVal = BigInteger.valueOf(sign * significand);
		if (exponent < 0) {
			intVal = intVal.multiply(BigInteger.valueOf(5).pow(-exponent));
			int scale = -exponent;
		} else if (exponent > 0) {
			intVal = intVal.multiply(BigInteger.valueOf(2).pow(exponent));
		}

		BigDecimal divident = new BigDecimal("1700");
		BigDecimal divisor = new BigDecimal("3.0");
		int prec1 = divident.precision();
		int prec2 = divisor.precision();
		int val1 = divident.intValue();
		int val2 = divisor.intValue();
		long scale1 = divident.scale();
		long scale2 = divisor.scale();
		BigDecimal result = divident.divide(divisor, new MathContext(4));
		System.out.println(result);
		System.out.println(prec1);
		System.out.println(prec2);
		System.out.println(val1);
		System.out.println(val2);
		System.out.println(scale1);
		System.out.println(scale2);
		System.out.println(divident.unscaledValue());
		long new1 = 11111111111111112L;
		int new2 = (int)new1;
		int[] newArray = new int[]{12,13,15};
		int index  = 2;
		boolean res = (newArray[--index] = newArray[index]) == 13;
		System.out.println(newArray[1]);
		System.out.println(new1);
		System.out.println(new2);
		System.out.println(res);
	}

}
