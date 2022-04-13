package com.felix.dhbw.compilerbau;

public class Evaluator {
	public static double eval(IExpression e) {
		return e.eval();
	}

	public static void main(String[] args) {
		System.out.println(Evaluator.eval(new MultExpression(new PlusExpression(new Literal(2), 
				new Literal(3)), new Literal(5))));
		System.out.println(Evaluator.eval(new DivExpression(
				new PlusExpression(new Literal(5), new Literal(13)), 
				new MultExpression(new Literal(2), new Literal(9)))));
	}
}
