package com.felix.dhbw.compilerbau;

public class DivExpression implements IExpression {
	private IExpression e1;
	private IExpression e2;
	
	public DivExpression(IExpression e1, IExpression e2)
	{
		this.e1 = e1;
		this.e2 = e2;
	}

	public IExpression getE2() {
		return e2;
	}

	public IExpression getE1() {
		return e1;
	}

	@Override
	public double eval() {
		return e1.eval() / e2.eval();
	}
	
}
