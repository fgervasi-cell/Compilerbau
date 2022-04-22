package com.felix.dhbwka.compilerbau.scanner;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

import com.thecout.lox.Token;
import com.thecout.lox.TokenType;

public class StackScanner {
	private static final int WHITESPACE = 32;
	private static final int TABSPACE = 9;
	private String source;

	public StackScanner(String source) {
		this.source = source;
	}

	public List<Token> scan() {
		List<Token> tokens = new ArrayList<>();
		List<String> lines = Arrays.asList(source.split(System.lineSeparator()));
		for (int i = 0; i < lines.size(); i++) {
			tokens.addAll(scanLine(lines.get(i), i));
		}
		return tokens;
	}

	private List<Token> scanLine(String line, int lineNumber) {
		List<Token> tokens = new ArrayList<>();
		List<Character> memory = new ArrayList<>();
		Deque<Character> deque = new ArrayDeque<>();
		initializeStack(deque, line);
		while (!deque.isEmpty()) {
			memory.add(deque.pop());
			String possibleToken = buildFromMemory(memory);
			if ("(".equals(possibleToken)) {
				tokens.add(new Token(TokenType.LEFT_PAREN, "(", '(', lineNumber));
				memory.clear();
			} else if (")".equals(possibleToken)) {
				tokens.add(new Token(TokenType.RIGHT_PAREN, ")", ')', lineNumber));
				memory.clear();
			} else if ("{".equals(possibleToken)) {
				tokens.add(new Token(TokenType.LEFT_BRACE, "{", '{', lineNumber));
				memory.clear();
			} else if ("}".equals(possibleToken)) {
				tokens.add(new Token(TokenType.RIGHT_BRACE, "}", '}', lineNumber));
				memory.clear();
			} else if (",".equals(possibleToken)) {
				tokens.add(new Token(TokenType.COMMA, ",", ',', lineNumber));
				memory.clear();
			} else if (";".equals(possibleToken)) {
				tokens.add(new Token(TokenType.SEMICOLON, ";", ';', lineNumber));
				memory.clear();
			} else if ("+".equals(possibleToken)) {
				tokens.add(new Token(TokenType.PLUS, "+", '+', lineNumber));
				memory.clear();
			} else if ("-".equals(possibleToken)) {
				tokens.add(new Token(TokenType.MINUS, "-", '-', lineNumber));
				memory.clear();
			} else if ("*".equals(possibleToken)) {
				tokens.add(new Token(TokenType.STAR, "*", '*', lineNumber));
				memory.clear();
			} else if ("/".equals(possibleToken)) {
				tokens.add(new Token(TokenType.SLASH, "/", '/', lineNumber));
				memory.clear();
			} else if ("!".equals(possibleToken) && (deque.isEmpty() || deque.peek() != '=')) {
				tokens.add(new Token(TokenType.BANG, "!", '!', lineNumber));
				memory.clear();
			} else if ("!=".equals(possibleToken)) {
				tokens.add(new Token(TokenType.BANG_EQUAL, "!=", "!=", lineNumber));
				memory.clear();
			} else if ("=".equals(possibleToken) && (deque.isEmpty() || deque.peek() != '=')) {
				tokens.add(new Token(TokenType.EQUAL, "=", "=", lineNumber));
				memory.clear();
			} else if ("==".equals(possibleToken)) {
				tokens.add(new Token(TokenType.EQUAL_EQUAL, "==", "==", lineNumber));
				memory.clear();
			} else if ("<".equals(possibleToken) && (deque.isEmpty() || deque.peek() != '=')) {
				tokens.add(new Token(TokenType.LESS, "<", '<', lineNumber));
				memory.clear();
			} else if ("<=".equals(possibleToken)) {
				tokens.add(new Token(TokenType.LESS_EQUAL, "<=", "<=", lineNumber));
				memory.clear();
			} else if (">".equals(possibleToken) && (deque.isEmpty() || deque.peek() != '=')) {
				tokens.add(new Token(TokenType.GREATER, ">", '>', lineNumber));
				memory.clear();
			} else if (">=".equals(possibleToken)) {
				tokens.add(new Token(TokenType.GREATER_EQUAL, ">=", ">=", lineNumber));
				memory.clear();
			} else if ("print".equals(possibleToken) && (deque.isEmpty() || !isLetter(deque.peek()))) {
				tokens.add(new Token(TokenType.PRINT, "print", "print", lineNumber));
				memory.clear();
			} else if ("if".equals(possibleToken) && (deque.isEmpty() || !isLetter(deque.peek()))) {
				tokens.add(new Token(TokenType.IF, "if", "if", lineNumber));
				memory.clear();
			} else if ("for".equals(possibleToken) && (deque.isEmpty() || !isLetter(deque.peek()))) {
				tokens.add(new Token(TokenType.FOR, "for", "for", lineNumber));
				memory.clear();
			} else if ("while".equals(possibleToken) && (deque.isEmpty() || !isLetter(deque.peek()))) {
				tokens.add(new Token(TokenType.WHILE, "while", "while", lineNumber));
				memory.clear();
			} else if ("var".equals(possibleToken) && (deque.isEmpty() || !isLetter(deque.peek()))) {
				tokens.add(new Token(TokenType.VAR, "var", "var", lineNumber));
				memory.clear();
			} 
			else if (memory.stream().allMatch(c -> isLetter(c)) && (deque.isEmpty() || !isLetter(deque.peek()))) {
				tokens.add(new Token(TokenType.IDENTIFIER, buildFromMemory(memory), buildFromMemory(memory), lineNumber));
				memory.clear(); // TODO: how to identify identifiers? ;)
			}
		}
		return tokens;
	}

	private void initializeStack(Deque<Character> deque, String line) {
		char[] chars = line.toCharArray();
		for (int i = chars.length - 1; i >= 0; i--) {
			if ((int) chars[i] != WHITESPACE && (int) chars[i] != TABSPACE) {
				deque.push(chars[i]);
			}
		}
	}

	private String buildFromMemory(List<Character> memory) {
		StringBuilder builder = new StringBuilder();
		for (char c : memory) {
			builder.append(c);
		}
		return builder.toString();
	}
	
	private boolean isLetter(char c) {
		return c <= 122 && c >= 65 || c <= 122 && c >= 97;
	}
}
