package numerosIrmaos.main;

import java.util.InputMismatchException;
import java.util.Scanner;

import numerosIrmaos.Number.GetNumber;

public class Main {

	public static void main(String[] args) {
		System.out.println("Digite o valor para obeter o maior irmão da famila deste número:\n"
				+ "O valor deve ser um inteiro entre 0 e 100.000.000");

		try {
			Long number = new Scanner(System.in).nextLong();
			Integer maiorNumeroIrmao = GetNumber.validNumber(number);

			System.out.println(maiorNumeroIrmao);
		} catch (InputMismatchException e) {
			System.out.println("Valor passado não é aceito pela entrada.\n"
					+ "O Valor aceito deve ser um número inteiro entre 0 e 100.000.000.");
		}

	}
}
