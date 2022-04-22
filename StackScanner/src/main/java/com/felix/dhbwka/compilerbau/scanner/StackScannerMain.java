package com.felix.dhbwka.compilerbau.scanner;

import java.util.Scanner;

public class StackScannerMain {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			String source = sc.nextLine();
			if ("exit".equals(source)) {
				sc.close();
				break;
			}
			StackScanner scanner = new StackScanner(source);
			System.out.println(scanner.scan());
		}
	}

}
