package data;

public class Accessories {

	private static Accessories instance = null;

	private Accessories() {
		// TODO Auto-generated constructor stub
	}

	public static Accessories getInstance() {
		if (instance == null) {
			instance = new Accessories();
		}
		return instance;
	}

	public String leadingZeros(String number, int numOfDigits) {
		StringBuilder builder = new StringBuilder();
		int numOfZeros = numOfDigits - number.length();
		for (int i = 0; i < numOfZeros; i++) {
			builder.append("0");
		}
		builder.append(number);
		return builder.toString();
	}
}