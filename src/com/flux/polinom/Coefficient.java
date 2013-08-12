package com.flux.polinom;

public class Coefficient {
	private final int[] powers;
	private double coefficientValue;

	public Coefficient(int[] powers, double coefficientValue) {
		this.powers = powers;
		this.coefficientValue = coefficientValue;
	}

	public long getPowersSum() {
		long result = 0;

		for (int power : powers) {
			result += power;
		}

		return result;
	}

	public double getCoefficientValue() {
		return coefficientValue;
	}

	public void setCoefficientValue(double coefficientValue) {
		this.coefficientValue = coefficientValue;
	}

	public int[] getPowers() {
		return powers;
	}

}
