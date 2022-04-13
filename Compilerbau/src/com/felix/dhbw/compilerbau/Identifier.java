package com.felix.dhbw.compilerbau;

public class Identifier implements IExpression {
	private String id;
	
	
	public Identifier(String identifier) {
		this.id = identifier;
	}
	
	@Override
	public double eval() {
		return Scope.memory.get(id);
	}

}
