package rs.ac.bg.etf.pp1;

public class MarkedCond { // pomocna klasa za klasu Code generator, da se zapamti uslov gde je postavljena instrukcija skoka za neki if,
						// da se retroaktivno vratimo na nju i postavimo dva bajta skoka
	private int pc = 0;	// pc na koji se treba vratiti
	private int serialID = 0; // redni broj u seriji - koji je po redu uslov u seriji uslova odvojenih AND-ovima, kada dodje sledeci OR resetuje se brojanje
							// npr. usl0 && usl1 && usl2 || usl3 && usl4
							// ......0........1.......2.......0......1..
	private int relOp = -1; // koji je relacioni operator u pitanju, ako je -1 poslato je true ili false
	
	private boolean modified = false; // da li je adresa skoka setovana, repair trick ga postavlja ranije a inace se postavlja kasnije kad se zavrsi if
								// da ne bismo dirali one za koje je adresa setovana, oni su modifikovani (modified = true)
	
	public MarkedCond(int pc, int serialID, int relOp) {
		super();
		this.pc = pc;
		this.serialID = serialID;
		this.relOp = relOp;
	}

	public int getPc() {
		return pc;
	}

	public void setPc(int pc) {
		this.pc = pc;
	}

	public int getSerialID() {
		return serialID;
	}

	public void setSerialID(int serialID) {
		this.serialID = serialID;
	}

	public int getRelOp() {
		return relOp;
	}

	public void setRelOp(int relOp) {
		this.relOp = relOp;
	}

	public boolean isModified() {
		return modified;
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}	
		
}
