package data;

import java.util.Hashtable;

public class Addresses {

	private Hashtable<String, Integer> opTable;
	private Hashtable<String, Integer> symTable;

	private Addresses() {
		// TODO Auto-generated constructor stub
		opTable = new Hashtable<>();
		symTable = new Hashtable<>();
		fillOpTable();
	}

	private static Addresses instance = null;

	public static Addresses getInstance() {
		if (instance == null) {
			instance = new Addresses();
		}
		return instance;
	}

	public Integer getOpCodeAddr(String opCode) {
		return opTable.get(opCode);
	}

	public void addLabelAddr(String label, Integer addr) {
		symTable.put(label.toUpperCase(), addr);
	}

	public Integer getOperandAddr(String operand) {
		return symTable.get(operand);
	}

	public boolean hasOpCode(String opCode) {
		return opTable.containsKey(opCode);
	}

	public boolean hasLabel(String label) {
		return symTable.containsKey(label);
	}

	private void fillOpTable() {
		opTable.put("ADD", 24);
		opTable.put("AND", 64);
		opTable.put("COMP", 40);
		opTable.put("DIV", 36);
		opTable.put("J", 60);
		opTable.put("JEQ", 48);
		opTable.put("JGT", 52);
		opTable.put("JLT", 56);
		opTable.put("JSUB", 72);
		opTable.put("LDA", 0);
		opTable.put("LDCH", 80);
		opTable.put("LDL", 8);
		opTable.put("LDX", 4);
		opTable.put("MUL", 32);
		opTable.put("OR", 68);
		opTable.put("RD", 216);
		opTable.put("RSUB", 76);
		opTable.put("STA", 12);
		opTable.put("STCH", 84);
		opTable.put("STL", 20);
		opTable.put("STX", 16);
		opTable.put("SUB", 28);
		opTable.put("TD", 224);
		opTable.put("TIX", 44);
		opTable.put("WD", 220);
	}
}