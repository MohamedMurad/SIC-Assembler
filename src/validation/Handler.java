package validation;

import data.Addresses;
import data.Data;

public class Handler {

	private Handler() {
		// TODO Auto-generated constructor stub
	}

	private static Handler instance = null;

	public static Handler getInstance() {
		if (instance == null) {
			instance = new Handler();
		}
		return instance;
	}

	public void validLineLen() {
		if (Data.line.length() > 66) {
			Exceptions.getInstance().throwValidLineLenException();
		}
	}

	public void tabNotExists() {
		if (Data.line.contains("\t")) {
			Exceptions.getInstance().throwTabException();
		}
	}

	public void blankExists() {
		if (Data.line.charAt(8) == ' ') {
			if (Data.line.length() > 16) {
				if (Data.line.charAt(15) == ' ' && Data.line.charAt(16) == ' ') {	
				}else{
					Exceptions.getInstance().throwBlankException();
				}
			}
		}else{
			Exceptions.getInstance().throwBlankException();
		}
	}

	public void validData() {
		validLabel();
		validOpcode();
	}

	private void validOpcode() {
		if (Data.opCode.equalsIgnoreCase("")) {
			Exceptions.getInstance().throwOpCodeNotFoundException();
		}
		if (Data.opCode.charAt(0) == ' ') {
			Exceptions.getInstance().throwInvalidSpacePrefixException();
		}
	}

	private void validLabel() {
		if (!Data.label.equalsIgnoreCase("") && Data.label.trim().equalsIgnoreCase(null)) {
			if (Data.label.charAt(0) == ' ') {
				Exceptions.getInstance().throwInvalidSpacePrefixException();
			}
			if (Character.isDigit(Data.label.charAt(0))) {
				Exceptions.getInstance().throwInvalidPrefixLabelException();
			}
		}
	}
	
	public void validTrimedData(){
		validTrimedLabel();
		validTrimedOpcode();
	}

	private void validTrimedLabel() {
		if (Addresses.getInstance().hasLabel(Data.label)) {
			Exceptions.getInstance().throwDuplicatedLabelException();
		}
	}

	private void validTrimedOpcode() {
		if (!Addresses.getInstance().hasOpCode(Data.opCode)) {
			boolean valid = false;
				for(Data.DATA data : Data.DATA.values()){
					if (Data.opCode.equalsIgnoreCase(data.toString())) {
						valid = true;
					}
				}
			if (!valid) {
				Exceptions.getInstance().throwOpCodeInvalidException();
			}
		}
	}
}