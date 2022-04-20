package com.thecout.lox;

import static com.thecout.lox.TokenType.EOF;

import java.util.ArrayList;
import java.util.List;

public class Scanner {

	private final String source;
	private final List<Token> tokens = new ArrayList<>();

	public Scanner(String source) {
		this.source = source;
	}

	public List<Token> scanLine(String line, int lineNumber) {
		List<Token> returnToken = new ArrayList<>();
		char[] chars = line.toCharArray();
		List<Character> memory = new ArrayList<>();
		int state = 0;
		for (int i = 0; i < chars.length; i++) {
			switch (chars[i]) {
			case '(':
				returnToken.add(new Token(TokenType.LEFT_PAREN, "(", '(', lineNumber));
				break;
			case ')':
				returnToken.add(new Token(TokenType.RIGHT_PAREN, ")", ')', lineNumber));
				break;
			case '{':
				returnToken.add(new Token(TokenType.LEFT_BRACE, "{", '{', lineNumber));
				break;
			case '}':
				returnToken.add(new Token(TokenType.RIGHT_BRACE, "}", '}', lineNumber));
				break;
			case ',':
				returnToken.add(new Token(TokenType.COMMA, ",", ',', lineNumber));
				break;
			case ';':
				returnToken.add(new Token(TokenType.SEMICOLON, ";", ';', lineNumber));
				break;
			case '+':
				returnToken.add(new Token(TokenType.PLUS, "+", '+', lineNumber));
				break;
			case '-':
				returnToken.add(new Token(TokenType.MINUS, "-", '-', lineNumber));
				break;
			case '*':
				returnToken.add(new Token(TokenType.STAR, "*", '*', lineNumber));
				break;
			case '/':
				returnToken.add(new Token(TokenType.SLASH, "/", '/', lineNumber));
				break;
			case '.':
				returnToken.add(new Token(TokenType.DOT, ".", '.', lineNumber));
				break;
			case '!':
				if (chars[i + 1] == '=') {
					state = 1;
				} else {
					returnToken.add(new Token(TokenType.BANG, "!", '!', lineNumber));
				}
				break;
			case '=':
				if (state == 1) {
					returnToken.add(new Token(TokenType.BANG_EQUAL, "!=", "!=", lineNumber));
					state = 0;
				} else if (state == 2) {
					returnToken.add(new Token(TokenType.LESS_EQUAL, "<=", "<=", lineNumber));
					state = 0;
				} else if (state == 3) {
					returnToken.add(new Token(TokenType.GREATER_EQUAL, ">=", ">=", lineNumber));
					state = 0;
				} else if (chars[i + 1] == '=') {
					state = 4;
				} else if (state == 4) {
					returnToken.add(new Token(TokenType.EQUAL_EQUAL, "==", "==", lineNumber));
					state = 0;
				} else {
					returnToken.add(new Token(TokenType.EQUAL, "=", '=', lineNumber));
				}
				break;
			case '<':
				if (chars[i + 1] == '=') {
					state = 2;
				} else {
					returnToken.add(new Token(TokenType.LESS, "<", '<', lineNumber));
				}
				break;
			case '>':
				if (chars[i + 1] == '=') {
					state = 3;
				} else {
					returnToken.add(new Token(TokenType.GREATER, ">", '>', lineNumber));
				}
				break;
			case 'f':
				break;
			case 'u':
				break;
			case 'n':
				break;
			case 'p':
				break;
			case 'r':
				break;
			case 'i':
				break;
			case 't':
				break;
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				break;
			case '"':
				break;
			default:
				break;
			}
		}
		System.out.println(returnToken);
		return returnToken;
	}

	private String buildTokenFromMemory(List<Character> characters) {
		StringBuilder builder = new StringBuilder();
		for (Character c : characters) {
			builder.append(c);
		}
		characters.clear();
		return builder.toString();
	}

	public List<Token> scan() {
		String[] lines = source.split("\n");
		for (int i = 0; i < lines.length; i++) {
			tokens.addAll(scanLine(lines[i], i));
		}
		tokens.add(new Token(EOF, "", "", lines.length));
		return tokens;
	}

}
