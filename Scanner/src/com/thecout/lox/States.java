package com.thecout.lox;

public final class States {
	private States() {}
	
	public static final int START = 0;
	public static final int READING_BANG_EQUAL = 1;
	public static final int READING_LESS_EQUAL = 2;
	public static final int READING_GREATER_EQUAL = 3;
	public static final int READING_EQUAL_EQUAL = 4;
	public static final int READING_FUN = 5;
	public static final int READING_IDENTIFIER = 6;
	public static final int READING_NUMBER = 7;
	public static final int READING_STRING = 8;
	public static final int READING_PRINT = 9;
}
