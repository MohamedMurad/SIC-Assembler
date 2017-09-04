package sicAssembler;

import java.util.ArrayList;
import java.util.Scanner;

import data.Addresses;
import data.Data;
import validation.Parser;

public class Pass1 {

	private StringBuilder intermedFileContent;
	Parser parser;
	private Integer locCtrCpy;

	public Pass1() {
		// TODO Auto-generated constructor stub
		intermedFileContent = new StringBuilder();
		parser = new Parser();
		Data.locCtr = 0;
	}

	public String run(ArrayList<String> lines) {

		for (int i = 0; i < lines.size() && Data.errorMsgFlag; i++) {
			String line = new String(lines.get(i));
			Data.line = line;
			Data.errorMsgFlag = parser.parseUnCheckedDataLine();
			if (!Data.errorMsgFlag) {
				intermedFileContent.append(Data.errorMsg);
			} else {
				if (Data.opCode.equalsIgnoreCase("start")) {
					Data.startingAdrr = Integer.parseInt(Data.operand);
					Data.startingAdrr = new Scanner(Data.startingAdrr.toString()).nextInt(16);
					Data.locCtr = Data.startingAdrr;
					intermedFileContent.append(Integer.toHexString(Data.locCtr));
					intermedFileContent.append(" " + line + '\n');
					locCtrCpy = Data.locCtr;
				} else {
					if (!Data.opCode.equalsIgnoreCase("end")) {
						if (line.charAt(0) != '.') {
							if (!Data.label.equalsIgnoreCase("")) {
								Addresses.getInstance().addLabelAddr(Data.label.toUpperCase(), Data.locCtr);
							}
						}
						if (Addresses.getInstance().hasOpCode(Data.opCode.toUpperCase())) {
							locCtrCpy += 3;
						} else if (Data.opCode.equalsIgnoreCase("word")) {
							locCtrCpy += 3;
						} else if (Data.opCode.equalsIgnoreCase("resw")) {
							locCtrCpy += (3 * Integer.parseInt(Data.operand));
						} else if (Data.opCode.equalsIgnoreCase("resb")) {
							locCtrCpy += Integer.parseInt(Data.operand);
						} else if (Data.opCode.equalsIgnoreCase("byte")) {
							Character suffix = new Character(Data.operand.charAt(0));
							Data.operand = Data.operand.substring(2, Data.operand.length() - 1);
							if (suffix == 'C') {
								locCtrCpy += (Data.operand.length());
							} else if (suffix == 'X') {
								locCtrCpy += (Data.operand.length() / 2);
							}

						}
						intermedFileContent.append(Integer.toHexString(Data.locCtr));
						intermedFileContent.append(" " + line + '\n');
					} else {
						intermedFileContent.append(Integer.toHexString(Data.locCtr));
						intermedFileContent.append(" " + line + '\n');
						Data.programLen = Data.locCtr - Data.startingAdrr;
						intermedFileContent.append("program length = " + Integer.toHexString(Data.programLen));
					}
				}
			}
			Data.locCtr = locCtrCpy;
		}
		return intermedFileContent.toString();
	}

}
