package com.thecout.lox;

import static com.thecout.lox.TokenType.EOF;

import java.util.ArrayList;
import java.util.List;

public class Scanner {

	private static final int WHITESPACE = 32;
	private final String source;
	private final List<Token> tokens = new ArrayList<>();

	public Scanner(String source) {
		this.source = source;
	}

	public List<Token> scanLine(String line, int lineNumber) {
		List<Token> returnToken = new ArrayList<>();
		List<Character> memory = new ArrayList<>();
		char[] chars = line.toCharArray();
		int state = States.START;
		for (int i = 0; i < chars.length; i++) {
			switch (chars[i]) {
			case '(':
				if (state == States.READING_IDENTIFIER) {
					String token = buildTokenFromMemory(memory);
					returnToken.add(new Token(TokenType.IDENTIFIER, token, token, lineNumber));
					state = States.START;
				}
				returnToken.add(new Token(TokenType.LEFT_PAREN, "(", '(', lineNumber));
				break;
			case ')':
				if (state == States.READING_IDENTIFIER) {
					String token = buildTokenFromMemory(memory);
					returnToken.add(new Token(TokenType.IDENTIFIER, token, token, lineNumber));
					state = States.START;
				} else if (state == States.READING_NUMBER) {
					String token = buildTokenFromMemory(memory);
					returnToken.add(new Token(TokenType.NUMBER, token, Double.parseDouble(token), lineNumber));
					state = States.START;
				}
				returnToken.add(new Token(TokenType.RIGHT_PAREN, ")", ')', lineNumber));
				break;
			case '{':
				if (state == States.READING_IDENTIFIER) {
					String token = buildTokenFromMemory(memory);
					returnToken.add(new Token(TokenType.IDENTIFIER, token, token, lineNumber));
					state = States.START;
				}
				returnToken.add(new Token(TokenType.LEFT_BRACE, "{", '{', lineNumber));
				break;
			case '}':
				if (state == States.READING_IDENTIFIER) {
					String token = buildTokenFromMemory(memory);
					returnToken.add(new Token(TokenType.IDENTIFIER, token, token, lineNumber));
					state = States.START;
				} else if (state == States.READING_NUMBER) {
					String token = buildTokenFromMemory(memory);
					returnToken.add(new Token(TokenType.NUMBER, token, Double.parseDouble(token), lineNumber));
					state = States.START;
				}
				returnToken.add(new Token(TokenType.RIGHT_BRACE, "}", '}', lineNumber));
				break;
			case ',':
				if (state == States.READING_IDENTIFIER) {
					String token = buildTokenFromMemory(memory);
					returnToken.add(new Token(TokenType.IDENTIFIER, token, token, lineNumber));
					state = States.START;
				} else if (state == States.READING_NUMBER) {
					String token = buildTokenFromMemory(memory);
					returnToken.add(new Token(TokenType.NUMBER, token, Double.parseDouble(token), lineNumber));
					state = States.START;
				}
				returnToken.add(new Token(TokenType.COMMA, ",", ',', lineNumber));
				break;
			case ';':
				if (state == States.READING_IDENTIFIER) {
					String token = buildTokenFromMemory(memory);
					returnToken.add(new Token(TokenType.IDENTIFIER, token, token, lineNumber));
					state = States.START;
				} else if (state == States.READING_NUMBER) {
					String token = buildTokenFromMemory(memory);
					returnToken.add(new Token(TokenType.NUMBER, token, Double.parseDouble(token), lineNumber));
					state = States.START;
				}
				returnToken.add(new Token(TokenType.SEMICOLON, ";", ';', lineNumber));
				break;
			case '+':
				if (state == States.READING_IDENTIFIER) {
					String token = buildTokenFromMemory(memory);
					returnToken.add(new Token(TokenType.IDENTIFIER, token, token, lineNumber));
				} else if (state == States.READING_NUMBER) {
					String token = buildTokenFromMemory(memory);
					returnToken.add(new Token(TokenType.NUMBER, token, Double.parseDouble(token), lineNumber));
					state = States.START;
				}
				returnToken.add(new Token(TokenType.PLUS, "+", '+', lineNumber));
				break;
			case '-':
				if (state == States.READING_IDENTIFIER) {
					String token = buildTokenFromMemory(memory);
					returnToken.add(new Token(TokenType.IDENTIFIER, token, token, lineNumber));
					state = States.START;
				} else if (state == States.READING_NUMBER) {
					String token = buildTokenFromMemory(memory);
					returnToken.add(new Token(TokenType.NUMBER, token, Double.parseDouble(token), lineNumber));
					state = States.START;
				}
				returnToken.add(new Token(TokenType.MINUS, "-", '-', lineNumber));
				break;
			case '*':
				if (state == States.READING_IDENTIFIER) {
					String token = buildTokenFromMemory(memory);
					returnToken.add(new Token(TokenType.IDENTIFIER, token, token, lineNumber));
					state = States.START;
				} else if (state == States.READING_NUMBER) {
					String token = buildTokenFromMemory(memory);
					returnToken.add(new Token(TokenType.NUMBER, token, Double.parseDouble(token), lineNumber));
					state = States.START;
				}
				returnToken.add(new Token(TokenType.STAR, "*", '*', lineNumber));
				break;
			case '/':
				if (state == States.READING_IDENTIFIER) {
					String token = buildTokenFromMemory(memory);
					returnToken.add(new Token(TokenType.IDENTIFIER, token, token, lineNumber));
					state = States.START;
				} else if (state == States.READING_NUMBER) {
					String token = buildTokenFromMemory(memory);
					returnToken.add(new Token(TokenType.NUMBER, token, Double.parseDouble(token), lineNumber));
					state = States.START;
				}
				returnToken.add(new Token(TokenType.SLASH, "/", '/', lineNumber));
				break;
			case '.':
				if (state == States.READING_NUMBER) {
					memory.add(chars[i]);
				} else {
					returnToken.add(new Token(TokenType.DOT, ".", '.', lineNumber));
				}
				break;
			case '!':
				if (state == States.READING_IDENTIFIER) {
					String token = buildTokenFromMemory(memory);
					returnToken.add(new Token(TokenType.IDENTIFIER, token, token, lineNumber));
					state = States.START;
				} else if (state == States.READING_NUMBER) {
					String token = buildTokenFromMemory(memory);
					returnToken.add(new Token(TokenType.NUMBER, token, Double.parseDouble(token), lineNumber));
					state = States.START;
				}
				if (chars[i + 1] == '=') {
					state = States.READING_BANG_EQUAL;
				} else {
					returnToken.add(new Token(TokenType.BANG, "!", '!', lineNumber));
				}
				break;
			case '=':
				if (state == States.READING_IDENTIFIER) {
					String token = buildTokenFromMemory(memory);
					returnToken.add(new Token(TokenType.IDENTIFIER, token, token, lineNumber));
					state = States.START;
				} else if (state == States.READING_NUMBER) {
					String token = buildTokenFromMemory(memory);
					returnToken.add(new Token(TokenType.NUMBER, token, Double.parseDouble(token), lineNumber));
				}
				if (state == States.READING_BANG_EQUAL) {
					returnToken.add(new Token(TokenType.BANG_EQUAL, "!=", "!=", lineNumber));
					state = States.START;
				} else if (state == States.READING_LESS_EQUAL) {
					returnToken.add(new Token(TokenType.LESS_EQUAL, "<=", "<=", lineNumber));
					state = States.START;
				} else if (state == States.READING_GREATER_EQUAL) {
					returnToken.add(new Token(TokenType.GREATER_EQUAL, ">=", ">=", lineNumber));
					state = States.START;
				} else if (chars[i + 1] == '=') {
					state = States.READING_EQUAL_EQUAL;
				} else if (state == States.READING_EQUAL_EQUAL) {
					returnToken.add(new Token(TokenType.EQUAL_EQUAL, "==", "==", lineNumber));
					state = States.START;
				} else {
					returnToken.add(new Token(TokenType.EQUAL, "=", '=', lineNumber));
				}
				break;
			case '<':
				if (state == States.READING_IDENTIFIER) {
					String token = buildTokenFromMemory(memory);
					returnToken.add(new Token(TokenType.IDENTIFIER, token, token, lineNumber));
					state = States.START;
				} else if (state == States.READING_NUMBER) {
					String token = buildTokenFromMemory(memory);
					returnToken.add(new Token(TokenType.NUMBER, token, Double.parseDouble(token), lineNumber));
					state = States.START;
				}
				if (chars[i + 1] == '=') {
					state = States.READING_LESS_EQUAL;
				} else {
					returnToken.add(new Token(TokenType.LESS, "<", '<', lineNumber));
				}
				break;
			case '>':
				if (state == States.READING_IDENTIFIER) {
					String token = buildTokenFromMemory(memory);
					returnToken.add(new Token(TokenType.IDENTIFIER, token, token, lineNumber));
					state = States.START;
				} else if (state == States.READING_NUMBER) {
					String token = buildTokenFromMemory(memory);
					returnToken.add(new Token(TokenType.NUMBER, token, Double.parseDouble(token), lineNumber));
					state = States.START;
				}
				if (chars[i + 1] == '=') {
					state = States.READING_GREATER_EQUAL;
				} else {
					returnToken.add(new Token(TokenType.GREATER, ">", '>', lineNumber));
				}
				break;
			case 'f':
				if (state == States.READING_STRING) {
					memory.add(chars[i]);
				} else if (chars[i + 1] == 'u' && chars[i + 2] == 'n' && (int) chars[i + 3] == WHITESPACE) {
					state = States.READING_FUN;
					returnToken.add(new Token(TokenType.FUN, "fun", "fun", lineNumber));
				} else if (chars[i + 1] == 'a' && chars[i + 2] == 'l' && chars[i + 3] == 's' && chars[i + 4] == 'e'
						&& (int) chars[i + 5] != WHITESPACE) {
					state = States.READING_FALSE;
					returnToken.add(new Token(TokenType.FALSE, "false", "false", lineNumber));
				} else if (chars[i + 1] == 'o' && chars[i + 2] == 'r' && (int) chars[i + 3] == WHITESPACE) {
					state = States.READING_FOR;
					returnToken.add(new Token(TokenType.FOR, "for", "for", lineNumber));
				} else if (state == States.READING_IDENTIFIER || state == States.START) {
					state = States.READING_IDENTIFIER;
					memory.add(chars[i]);
				} else if (state == States.READING_IF) {
					state = States.START;
				}
				break;
			case 'n':
				if (state == States.READING_FUN) {
					state = States.START;
				} else if (state == States.READING_STRING) {
					memory.add(chars[i]);
				} else if (state == States.START || state == States.READING_IDENTIFIER) {
					state = States.READING_IDENTIFIER;
					memory.add(chars[i]);
				} else if (state == States.READING_RETURN) {
					state = States.START;
				}
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
				state = States.READING_NUMBER;
				memory.add(chars[i]);
				break;
			case '"':
				if (state == 0) {
					state = States.READING_STRING;
				} else if (state == States.READING_STRING) {
					String s = buildTokenFromMemory(memory);
					returnToken.add(new Token(TokenType.STRING, s, s, lineNumber));
					state = States.START;
				}
				break;
			case 'p':
				if (chars[i + 1] == 'r' && chars[i + 2] == 'i' && chars[i + 3] == 'n' && chars[i + 4] == 't'
						&& (int) chars[i + 5] == WHITESPACE) {
					state = States.READING_PRINT;
					returnToken.add(new Token(TokenType.PRINT, "print", "print", lineNumber));
				} else if (state == States.START || state == States.READING_IDENTIFIER) {
					state = States.READING_IDENTIFIER;
					memory.add(chars[i]);
				} else if (state == States.READING_STRING) {
					memory.add(chars[i]);
				}
				break;
			case 't':
				if (state == States.READING_STRING) {
					memory.add(chars[i]);
				} else if (chars[i + 1] == 'r' && chars[i + 2] == 'u' && chars[i + 3] == 'e'
						/*&& (int) chars[i + 4] != WHITESPACE*/) {
					state = States.READING_TRUE;
					returnToken.add(new Token(TokenType.TRUE, "true", "true", lineNumber));
				} else if (state == States.READING_PRINT) {
					state = States.START;
				} else if (state == States.START || state == States.READING_IDENTIFIER) {
					state = States.READING_IDENTIFIER;
					memory.add(chars[i]);
				}
				break;
			case 'v':
				if (chars[i + 1] == 'a' && chars[i + 2] == 'r' && chars[i + 3] == WHITESPACE) {
					state = States.READING_VAR;
					returnToken.add(new Token(TokenType.VAR, "var", "var", lineNumber));
				} else if (state == States.START || state == States.READING_IDENTIFIER) {
					state = States.READING_IDENTIFIER;
					memory.add(chars[i]);
				} else if (state == States.READING_STRING) {
					memory.add(chars[i]);
				}
				break;
			case 'r':
				if (chars[i + 1] == 'e' && chars[i + 2] == 't' && chars[i + 3] == 'u' && chars[i + 4] == 'r'
						&& chars[i + 5] == 'n' && chars[i + 6] == WHITESPACE) {
					state = States.READING_RETURN;
					returnToken.add(new Token(TokenType.RETURN, "return", "return", lineNumber));
				} else if (state == States.READING_VAR) {
					state = States.START;
				} else if (state == States.START || state == States.READING_IDENTIFIER) {
					state = States.READING_IDENTIFIER;
					memory.add(chars[i]);
				} else if (state == States.READING_STRING) {
					memory.add(chars[i]);
				} else if (state == States.READING_FOR) {
					state = States.START;
				}
				break;
			case 'i':
				if (chars[i + 1] == 'f' && (int) chars[i + 2] == WHITESPACE) {
					state = States.READING_IF;
					returnToken.add(new Token(TokenType.IF, "if", "if", lineNumber));
				} else if (state == States.START || state == States.READING_IDENTIFIER) {
					state = States.READING_IDENTIFIER;
					memory.add(chars[i]);
				} else if (state == States.READING_STRING) {
					memory.add(chars[i]);
				}
				break;
			case 'e':
				if (chars[i + 1] == 'l' && chars[i + 2] == 's' && chars[i + 3] == 'e'
						&& (int) chars[i + 4] == WHITESPACE) {
					state = States.READING_ELSE;
					returnToken.add(new Token(TokenType.ELSE, "else", "else", lineNumber));
				} else if (state == States.START || state == States.READING_IDENTIFIER) {
					state = States.READING_IDENTIFIER;
					memory.add(chars[i]);
				} else if (state == States.READING_STRING) {
					memory.add(chars[i]);
				} else if (state == States.READING_ELSE) {
					state = States.START;
				} else if (state == States.READING_TRUE) {
					state = States.START;
				} else if (state == States.READING_FALSE) {
					state = States.START;
				} else if (state == States.READING_WHILE) {
					state = States.START;
				}
				break;
			case 'w':
				if (chars[i + 1] == 'h' && chars[i + 2] == 'i' && chars[i + 3] == 'l' && chars[i + 4] == 'e'
						&& (int) chars[i + 5] == WHITESPACE) {
					state = States.READING_WHILE;
					returnToken.add(new Token(TokenType.WHILE, "while", "while", lineNumber));
				} else if (state == States.READING_IDENTIFIER || state == States.START) {
					state = States.READING_IDENTIFIER;
					memory.add(chars[i]);
				} else if (state == States.READING_STRING) {
					memory.add(chars[i]);
				}
				break;
			case '&':
				returnToken.add(new Token(TokenType.AND, "&", '&', lineNumber));
				break;
			case '|':
				returnToken.add(new Token(TokenType.OR, "|", '|', lineNumber));
				break;
			default:
				if ((state == States.START || state == States.READING_IDENTIFIER) && (int) chars[i] != WHITESPACE) {
					state = States.READING_IDENTIFIER;
					memory.add(chars[i]);
				} else if (state == States.READING_STRING) {
					memory.add(chars[i]);
				}
				break;
			}
		}
		if (state == States.READING_NUMBER) {
			String number = buildTokenFromMemory(memory);
			returnToken.add(new Token(TokenType.NUMBER, number, Double.parseDouble(number), lineNumber));
		}
		System.out.println(returnToken);
		return returnToken;
	}

	private String buildTokenFromMemory(List<Character> memory) {
		StringBuilder builder = new StringBuilder();
		for (Character c : memory) {
			builder.append(c);
		}
		memory.clear();
		return builder.toString().trim();
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
