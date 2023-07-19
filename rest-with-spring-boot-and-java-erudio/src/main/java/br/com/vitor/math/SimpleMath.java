package br.com.vitor.math;

public class SimpleMath {
	public Double sum(Double numberOne, Double numberTwo) {
		return numberOne + numberTwo;
	}

	public Double minus(Double numberOne, Double numberTwo) {
		return numberOne - numberTwo;
	}

	public Double mult(Double numberOne, Double numberTwo) {
		return numberOne * numberTwo;
	}

	public Double div(Double numberOne, Double numberTwo) {
		return numberOne / numberTwo;
	}

	public Double med(Double numberOne, Double numberTwo) {
		return (numberOne + numberTwo) / 2;
	}

	public Double squareRoot(Double number) {
		return Math.sqrt(number);
	}

}
