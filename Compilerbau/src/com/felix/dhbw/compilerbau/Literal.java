package com.felix.dhbw.compilerbau;

public class Literal implements IExpression {
	private double value;
	
	public Literal(double value)
	{
		this.value = value;
	}

	@Override
	public double eval() {
		return value;
	}

}
