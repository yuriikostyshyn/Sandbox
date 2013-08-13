package com.flux.polinom;

public class Coefficient implements Comparable<Coefficient> {
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

	public void addCoefficientValue(double coefficientValue) {
		this.coefficientValue += coefficientValue;
	}

	@Override
	public int compareTo(Coefficient o) {
		int result = 0;

		if (this.getPowersSum() != o.getPowersSum()) {
			result = this.getPowersSum() > o.getPowersSum() ? 1 : -1;
		} else {
			for (int i = 0; i < this.getPowers().length; i++) { // we assume that this and o have equal number of variables. FIXME
				int difference = this.getPowers()[i] - o.getPowers()[i];
				if (difference != 0) {
					result = difference;
					break;
				}
			}
		}

		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		return false;
	}

}
