package com.thecout.lox;

import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			String input = sc.nextLine();
			if (input.equals("exit")) {
				sc.close();
				break;
			}
			com.thecout.lox.Scanner scanner = new com.thecout.lox.Scanner(input);
			scanner.scan();
		}
	}

}
