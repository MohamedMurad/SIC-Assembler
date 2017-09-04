package sicAssembler;

import java.util.ArrayList;
import java.util.Formatter;

import data.Accessories;
import data.Addresses;
import data.Data;
import validation.Parser;

public class Pass2 {

	private StringBuilder objFileContent, listingFileContent, textBuilder;

	private String objCode;
	Parser parser;
	private boolean cut;
	private int recLen;

	public Pass2() {
		// TODO Auto-generated constructor stub
		objFileContent = new StringBuilder();
		listingFileContent = new StringBuilder();
		textBuilder = new StringBuilder();
		objCode = new String();
		parser = new Parser();
		Data.locCtr = 0;
		cut = false;
		recLen = 0;
	}

	public StringBuilder getListingFileContent() {
		return listingFileContent;
	}

	public String headerBuilder() {
		StringBuilder header = new StringBuilder();
		header.append("H");
		header.append(Data.label);
		for (int i = header.length(); i < 7; i++) {
			header.append(" ");
		}
		header.append(Accessories.getInstance().leadingZeros(Integer.toHexString(Data.startingAdrr).toUpperCase(), 6));
		header.append(Accessories.getInstance().leadingZeros(Integer.toHexString(Data.programLen).toUpperCase(), 6));
		return header.toString();
	}

	public String run(ArrayList<String> lines) {
		boolean flag = true;
		for (int i = 0; i < lines.size() && flag; i++) {
			String line = new String(lines.get(i));

			objCode = new String();
			StringBuilder listingFileLineBuilder = new StringBuilder();
			Formatter formatter = new Formatter(listingFileLineBuilder);
			formatter.format("%-71s", line);
			formatter.close();

			String listingAdrr = new String(line.substring(0, 4));
			line = line.substring(5);
			Data.line = line;
			parser.parseCheckedDataLine();
			if (Data.opCode.equalsIgnoreCase("start")) {
				objFileContent.append(headerBuilder() + '\n');
				setNewRecord(listingAdrr);
			} else {
				if (!Data.opCode.equalsIgnoreCase("end")) {
					if (line.charAt(0) != '.') {
						if (Addresses.getInstance().hasOpCode(Data.opCode)) {
							Data.opCode = Integer.toHexString(Addresses.getInstance().getOpCodeAddr(Data.opCode));
							Data.opCode = Data.opCode.toUpperCase();
							Data.opCode = Accessories.getInstance().leadingZeros(Data.opCode, 2);
							if (!Data.operand.equalsIgnoreCase("")) {
								flag = checkOperandLabel();
							} else {
								Data.operand = "0";
							}
							Data.operand = Accessories.getInstance().leadingZeros(Data.operand, 4);
							objCode = new String(Data.opCode + Data.operand);

						} else if (Data.opCode.equalsIgnoreCase("word") || Data.opCode.equalsIgnoreCase("byte")) {
							if (Data.opCode.equalsIgnoreCase("byte")) {
								flag = checkOperandByte();
							} else if (Data.opCode.equalsIgnoreCase("word")) {
								checkOperandWord();
							}
							objCode = new String(Data.operand);
						} else if (Data.opCode.equalsIgnoreCase("resw") || Data.opCode.equalsIgnoreCase("resb")) {
							cut = true;
							addToListingFile(listingFileLineBuilder);
							continue;
						}
						if ((textBuilder.length() + objCode.length()) > 69 || cut) {
							setRecordSize();
							objFileContent.append(textBuilder.toString() + '\n');
							setNewRecord(listingAdrr);
						}
						if (!flag) {
							objFileContent.append(Data.errorMsg);
						} else {
							textBuilder.append(objCode);
							recLen += (objCode.length() / 2);
						}
					}
				} else {
					setRecordSize();
					objFileContent.append(textBuilder);
					objFileContent.append('\n' + "E"
							+ Accessories.getInstance().leadingZeros(Integer.toHexString(Data.startingAdrr), 6));
					flag = false;
				}
			}
			addToListingFile(listingFileLineBuilder);
		}
		return objFileContent.toString();
	}

	private void addToListingFile(StringBuilder listingFileLineBuilder) {
		listingFileLineBuilder.append(objCode + "\n");
		listingFileContent.append(listingFileLineBuilder.toString());
	}

	private void setNewRecord(String listingAdrr) {
		textBuilder = new StringBuilder("T");
		textBuilder.append(Accessories.getInstance().leadingZeros(listingAdrr, 6));
		textBuilder.append("00");
		recLen = 0;
		cut = false;
	}

	private void setRecordSize() {
		String s = new String(Integer.toHexString(recLen));
		s = Accessories.getInstance().leadingZeros(s, 2);
		textBuilder.setCharAt(7, s.charAt(0));
		textBuilder.setCharAt(8, s.charAt(1));
	}

	protected boolean checkOperandByte() {
		if (Data.operand.substring(0, 2).equalsIgnoreCase("X'")) {
			if (Data.operand.substring(2).contains("'")) {
				Data.operand = Data.operand.substring(2, Data.operand.length() - 1);
			} else {
				Data.errorMsg = "Missing single quote";
				return false;
			}
		} else if (Data.operand.substring(0, 2).equalsIgnoreCase("C'")) {
			if (Data.operand.substring(2).contains("'")) {
				Data.operand = Data.operand.substring(2, Data.operand.length() - 1);
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < Data.operand.length(); i++) {
					int asc = Data.operand.charAt(i);
					String hex = Integer.toHexString(asc);
					sb.append(Accessories.getInstance().leadingZeros(hex, 2));
				}
				Data.operand = sb.toString();
			} else {
				Data.errorMsg = "Missing single quotes";
				return false;
			}
		}
		return true;
	}

	protected void checkOperandWord() {
		Data.operand = Integer.toHexString(Integer.parseInt(Data.operand));
		Data.operand = Data.operand.toUpperCase();
		Data.operand = Accessories.getInstance().leadingZeros(Data.operand, 6);
	}

	private boolean checkOperandLabel() {
		if (Data.operand.contains(",X") || Data.operand.contains(",x")) {
			Data.operand = Data.operand.substring(0, Data.operand.indexOf(","));
		}
		if (Addresses.getInstance().hasLabel(Data.operand)) {
			Data.operand = Integer.toHexString(Addresses.getInstance().getOperandAddr(Data.operand));
		} else {
			Data.operand = "0";
			Data.errorMsg = "undefined symbol";
			return false;
		}
		return true;
	}
}