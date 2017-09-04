package data;

public class Data {
	public static String line, label, opCode, operand, comment, errorMsg;
	public static boolean errorMsgFlag = true;
	public static Integer locCtr, startingAdrr, programLen;

	public static enum DATA {
		START, END, WORD, BYTE, RESB, RESW
	}
}
