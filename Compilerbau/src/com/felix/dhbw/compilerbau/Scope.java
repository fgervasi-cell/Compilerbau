package com.felix.dhbw.compilerbau;

import java.util.HashMap;
import java.util.Map;

public class Scope implements IExpression {
	public static Map<String, Double> memory = new HashMap<>();
	private IExpression expr;
	
	
	public Scope(String id, IExpression assignment, IExpression expr) {
		this.expr = expr;
		Scope.memory.put(id, assignment.eval());
	}

	@Override
	public double eval() {
		return expr.eval();
	}
	
}
