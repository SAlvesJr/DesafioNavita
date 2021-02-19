package numerosIrmaos.util;

import java.util.regex.Pattern;

public class NumberUtil {

	public static final int VALOR_MAXIMO = 100000000;
	public static final int VALOR_MINIMO = 0;

	/**
	 * Valida o número passado.
	 * 
	 * @param valorNumerico valor inteiro
	 * @return true caso o numero seja valido 
	 */
	public static final Boolean isNumberValid(Integer valorNumerico) {

		Pattern pattern = Pattern.compile("^[0-9]{1,9}$");

		Boolean retorno = Boolean.FALSE;

		if (valorNumerico != null 
				&& valorNumerico <= VALOR_MAXIMO 
				&& valorNumerico > VALOR_MINIMO) {
			return pattern.matcher(String.valueOf(valorNumerico)).matches();
		}

		return retorno;
	}

}
