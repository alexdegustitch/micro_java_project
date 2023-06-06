package rs.ac.bg.etf.pp1;

import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;

public class CodeGenerator extends VisitorAdaptor {
	private enum AddOp {opAdd, opSub};
	private enum MulOp {opMul, opDiv, opMod};							
	
	private LinkedList<AddOp> addOpLoader = new LinkedList<CodeGenerator.AddOp>();
	private LinkedList<MulOp> mulOpLoader = new LinkedList<CodeGenerator.MulOp>();	
	
	private int currRelOp = -1;	
	
	private boolean errorDetected = false;
	private Obj globl = null;
	private Obj currentMethod = null;	
	private ArrayList<Var> myVars = new ArrayList<Var>();	// lista promenljivih je neophodna, jer se prvo dohvataju imena odvojena zarezom pa onda tip kad se kompletira smena!
	
	private Var myVar = null;					
	private Logger log = Logger.getLogger(getClass());				
	
	private LinkedList<ArrayList<MarkedCond>> jccMarkedCondsStack = new LinkedList<ArrayList<MarkedCond>>(); // ovo je lista adresa pc-ova gde su smesteni jumpovi iz uslova za if i if-else	
	private int serialCounter = 0; // serijski brojac, broji sve do pojave sledeceg OR prelazeci (inkrementirajuci se) preko AND-ova
	
	private LinkedList<Integer> absMarkerPcStack = new LinkedList<Integer>(); // mesto apsolutnog skoka koji razdvaja if i else granu (da se iz if dela bezuslovno preskoci else)
																	// svaki redMarkedPc je mesto gde je smesten skok iz uslova do-while, treba nam!
	private LinkedList<Integer> retMarkedPcStack = new LinkedList<Integer>(); //povratna adresa do do-while;
	private LinkedList<ArrayList<Integer>> breakMarkedPcsStack = new LinkedList<ArrayList<Integer>>();	
	//----------------------------------------------------------------------------------------------------------------
	//1. KONSTRUKTOR - GLOBL SE PROSLEDJUJE IZ SEMANTICKE ANALIZE, SADRZI SIMBOLE PROGRAMA KOJI SMO PISALI
	public CodeGenerator(Obj globl) {
		super();
		this.globl = globl;
	}
	//----------------------------------------------------------------------------------------------------------------
	//2. DOHVATANJE OBJEKATA IZ TABELE SIMBOLA ILI GLOBL-A, BILO DA SU ONI METODE ILI TO NISU
	public Obj getMethObj(String methName) { // potrazi metodu ako je poznato njeno ime
		Obj obj = Tab.find(methName);
		if (obj == Tab.noObj) {
			obj = null;
			Collection<Obj> coll = globl.getLocalSymbols();
			Iterator<Obj> i = coll.iterator();			
			while (i.hasNext()) {
				obj = i.next();
				if (obj.getKind() == Obj.Meth && obj.getName().equals(methName)) {
					return obj;
				}
			}
		} else {
			return obj;
		}
		return null;
	}
		
	public Obj getNonMethObj(String objName) { // potrazi objekat ako je poznata tekuca (current) metoda
		Obj obj = Tab.find(objName);
		if (obj == Tab.noObj) {
			obj = null;
			Collection<Obj> coll = globl.getLocalSymbols();
			Iterator<Obj> i = coll.iterator();			
			while (i.hasNext()) {
				obj = i.next();
				if (obj.getKind() != Obj.Meth && obj.getName().equals(objName)) {
					return obj;
				}
			}
			
			if (currentMethod != null) {
				coll = currentMethod.getLocalSymbols();
				i = coll.iterator();		
				while (i.hasNext()) {
					obj = i.next();
					if (obj.getName().equals(objName)) {
						return obj;
					}
				}
			}		
		} else {
			return obj;
		}
		return null;
	}
		
	//----------------------------------------------------------------------------------------------------------------
	//3. PRIJAVA GRESKA, POKUPLJENO SA LAB VEZBI, A ISTO SE KORISTI I U SEMANTICKOJ ANALIZI
	public void report_error(String message, SyntaxNode info) { // prilagodjen kodu sa lab vezbi
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0) {
			msg.append(" na liniji ").append(line).append("!");
		}
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0) {
			msg.append(" na liniji ").append(line);
		}
		log.info(msg.toString());
	}
	//----------------------------------------------------------------------------------------------------------------
	//4. GETTER - DA LI JE UCINJENA GRESKA
	public boolean isErrorDetected() {
		return errorDetected;
	}		
	//----------------------------------------------------------------------------------------------------------------
	//5. IZLAZNE TACKE PROGRAMA, VELICINA PODATAKA JE BROJ SIMBOLA IZ NASEG PROGRAMA TJ GLOBL-A
	@Override	    			
	public void visit(Program_0 Program_0) {		
		Code.dataSize = globl.getLocalSymbols().size();
		errorDetected = errorDetected || Code.greska;		
	}
	
	@Override	    			
	public void visit(Program_1 Program_1) {		
		Code.dataSize = globl.getLocalSymbols().size();
		errorDetected = errorDetected || Code.greska;		
	}
	
	@Override	    			
	public void visit(Program_2 Program_2) {		
		Code.dataSize = globl.getLocalSymbols().size();
		errorDetected = errorDetected || Code.greska;		
	}
	
	@Override	    			
	public void visit(Program_3 Program_3) {		
		Code.dataSize = globl.getLocalSymbols().size();
		errorDetected = errorDetected || Code.greska;		
	}				
	//----------------------------------------------------------------------------------------------------------------
	//6. IZLAZNE TACKE METODA, JEDNA ZA ONE KOJE NE VRACAJU VREDNOST (TIPA VOID) I ZA ONE KOJE VRACAJU VREDNOST
	@Override
    public void visit(Meth_void_decl meth_void_decl) {		
		// generisanje koda		
		Code.put(Code.exit); 
		Code.put(Code.return_);
		currentMethod = null;
	}
	
	@Override
    public void visit(Meth_type_decl meth_type_decl) {
		// end of function reached without a return statement
		Code.put(Code.trap); 
		Code.put(1);
		currentMethod = null;
	}
	
	//7.ULAZNE TACKE METODA KOJE NE VRACAJU VREDNOST
	@Override
	public void visit(Method_void_name Method_void_name) { 		
		currentMethod = getMethObj(Method_void_name.getMethodName());			
		if (currentMethod.getName().equals("main")) {
			Code.mainPc = Code.pc;			
		}
        currentMethod.setAdr(Code.pc);
        Code.put(Code.enter);
        Code.put(currentMethod.getLevel());
        Code.put(currentMethod.getLocalSymbols().size()); // velicina locals liste metoda                
	}				
	
	//8.ULAZNE TACKE METODA KOJI VRACAJU VREDNOST
	@Override
	public void visit(Method_type_name Method_type_name) {		
		currentMethod = getMethObj(Method_type_name.getMethodName());
		if (currentMethod.getName().equals("main")) {
			Code.mainPc = Code.pc;			
		}		
        currentMethod.setAdr(Code.pc);
        Code.put(Code.enter);
        Code.put(currentMethod.getLevel());
        Code.put(currentMethod.getLocalSymbols().size()); // velicina locals liste metoda        
	}
	//----------------------------------------------------------------------------------------------------------------
	//9. UCITAVANJE KONSTANTI
	@Override
	public void visit(Const_declaration Const_declaration) {		
		for (int i = 0; i < myVars.size(); i++) {			
			Obj o = getNonMethObj(myVars.get(i).getName());			
			if (o != null) {
				Object val = myVars.get(i).getValue();
				if (val instanceof Integer) {
					o.setAdr((Integer) val);
				} else if (val instanceof Character) {
					o.setAdr((Character) val);
				} else if (val instanceof Boolean) {				
					o.setAdr((Boolean) val ? 1 : 0);
				} else {
					o.setAdr(0);
				}
				Code.load(o);
			}
		}
		myVars.clear();
	}	
	
	//10. SETOVANJE IMENA KONSTANTI
	@Override
	public void visit(Const_part Const_part) {
		myVar.setName(Const_part.getConstName());
		myVars.add(myVar);
		myVar = null;
	}
	
	//----------------------------------------------------------------------------------------------------------------
	@Override
    public void visit(Type Type) { // prilagodjen kodu sa lab vezbi 
    	// ..
    }
	
	//11. POTREBAN ZA UCITVANJE KONSTANTI JER SMO REKLI DA SE PRVO DOHVATA VREDNOST PA TEK ONDA IME PA TEK ONDA TIP KONSTANTI
	@Override
	public void visit(Val_Num_const Val_Num_const) { 
		myVar = new Var(Val_Num_const.getN1());
	}
	
	@Override
	public void visit(Val_Char_const Val_Char_const) { 
		myVar = new Var(Val_Char_const.getC1());
	}    
	
	@Override
	public void visit(Val_Bool_const Val_Bool_const) { 
		myVar = new Var(Val_Bool_const.getB1());
	}			    	
	//----------------------------------------------------------------------------------------------------------------
	//12. SUVISAN KOD - JER NE TREBA PROMENLJIVE UNAPRED UCITAVATI ZA RAZLIKU OD KONSTANTI
	@Override
    public void visit(Var_declaration Var_declaration) {     	
//    	for (int i = 0; i < myVars.size(); i++) {
//    		Obj o = null;
//    		o = getNonMethObj(myVars.get(i).getName());
//			if (o != null) {			
////				Code.load(o);
//			}
//    	}
//    	myVars.clear();
    }    		
	
	//----------------------------------------------------------------------------------------------------------------				
	@Override
    public void visit(Var_array Var_array) { 
//		myVar = new Var(Var_array.getVarName(), true);
//		myVars.add(myVar);
//		myVar = null;
    }
    
	@Override
    public void visit(Var_normal Var_normal) {
//		myVar = new Var(Var_normal.getVarName());
//		myVars.add(myVar);
//		myVar = null;
	}		
	
	@Override
    public void visit(Var_error Var_error) { visit(); }
	
	//----------------------------------------------------------------------------------------------------------------
	//13. VEZANO JE ZA FAKTOR, A FAKTOR ZA RACUNANJE (ODNOSNO UCITAVANJE) IZRAZA
	// SADA SE ZA FAKTOR MOZE UCITATI BROJ, KARAKTER ILI BOOLEAN KONSTANTA
	@Override
	public void visit(Factor_num_const Factor_num_const) {				
		Code.loadConst(Factor_num_const.getN1());				
	}
	
	@Override
	public void visit(Factor_char_const Factor_char_const) {		
		Code.loadConst(Factor_char_const.getC1());			
	}
	
	@Override
	public void visit(Factor_expr Factor_expr) { 
		// ..
	}
	
	//14. MIKROJAVA JEDINO RADI SA NUMERICKIM VREDNOSTIMA DAKLE TRUE JE 1 A FALSE JE 0
	@Override
	public void visit(Factor_bool_const Factor_bool_const) {				
		int value = Factor_bool_const.getB1() ? 1 : 0;
		Code.loadConst(value);		
	}
	
	
	//15. NE RADI NISTA
	@Override
	public void visit(Factor_new Factor_new) {
		// ..
	} 		
	
	//16. ALOCIRANJE NIZA, NA STEKU OSTAJE ADRESA NIZA KADA SE OVAKVA INSTRUKCIJA IZVRSI
	@Override
	public void visit(Factor_new_array Factor_new_array) {		
		Code.put(Code.newarray); // prilagodjenu kodu sa mini_domaceg3		
        if (Factor_new_array.getType().struct == Tab.charType) {
			Code.put(0); 
        } else { 
			Code.put(1);
        }        
	}
	
	//17. AKO JE FAKTOR DESIGNATOR ODNOSNO NEKA PROMENLJIVA, TREBA JU JE UCITATI
	@Override
	public void visit(Factor_designator Factor_designator) {
		Obj o = getNonMethObj(Factor_designator.getDesignator().obj.getName());
		if (o != null) {
			Code.load(Factor_designator.getDesignator().obj);
		}
	}
	//----------------------------------------------------------------------------------------------------------------
	//18. OBRADA POZIVE METODA, ISTI KOD SE KOPIRA 4 PUTA
	@Override
	public void visit(Factor_meth_call Factor_meth_call) {
		Obj method = Factor_meth_call.getDesignator().obj;
		if (globl.getLocalSymbols().contains(method)) {
			int offset = method.getAdr() - Code.pc;
			Code.put(Code.call);
			Code.put2(offset);
		}
	}
	
	@Override
	public void visit(Method_call_0 Method_call_0) {
		Obj method = Method_call_0.getDesignator().obj;
		if (globl.getLocalSymbols().contains(method)) {
			int offset = method.getAdr() - Code.pc;
			Code.put(Code.call);
			Code.put2(offset);
		}
	}
	
	@Override
	public void visit(Method_call_1 Method_call_1) { 
		Obj method = Method_call_1.getDesignator().obj;
		if (globl.getLocalSymbols().contains(method)) {
			int offset = method.getAdr() - Code.pc;
			Code.put(Code.call);
			Code.put2(offset);
		}
	}
	
	@Override
	public void visit(Expr_meth_call Expr_meth_call) { 
		Obj method = Expr_meth_call.getDesignator().obj;
		if (globl.getLocalSymbols().contains(method)) {
			int offset = method.getAdr() - Code.pc;
			Code.put(Code.call);
			Code.put2(offset);
		}
	}
	//----------------------------------------------------------------------------------------------------------------
	//19. NE RADITI NISTA - VISAK
	@Override
	public void visit(Stmt_design Stmt_design) {		
		//..
	}
	
	//20. NAREDBA READ KOJA VEC LOADOVANO NA STEKU STORUJE U NEKU PROMENLJIVU (VAR)
	@Override
	public void visit(Stmt_Read Stmt_Read) {		
		Code.put(Code.read);
		Code.store(Stmt_Read.getDesignator().obj);		
	}		
	
	//21. OBICNO PRITANJE SA ZADATOM SIRINOM
	@Override
	public void visit(Stmt_print_0 Stmt_print_0) {		
		Struct struct = Stmt_print_0.getExpr().struct;
		if (struct.equals(Tab.intType) || struct.equals(SemanticAnalyzer.getBoolType())) {
			Code.loadConst(Stmt_print_0.getN2());
			Code.put(Code.print);
		} else if (struct.equals(Tab.charType)) {
			Code.loadConst(1);
			Code.put(Code.bprint);
		}		
	}
	
	//22. PRITANJE GDE JE SIRINA NULA
	@Override
	public void visit(Stmt_print_1 Stmt_print_1) {		
		Struct struct = Stmt_print_1.getExpr().struct;
		if (struct.equals(Tab.intType) || struct.equals(SemanticAnalyzer.getBoolType())) {
			Code.loadConst(0);
			Code.put(Code.print);
		} else if (struct.equals(Tab.charType)) {
			Code.loadConst(1);
			Code.put(Code.bprint);
		}		
	} 		
	
	//----------------------------------------------------------------------------------------------------------------
	//23. IZLAZNA TACKA IF NAREDBE
	@Override
	public void visit(Stmt_if Stmt_if) {
		ArrayList<MarkedCond> jccMarkedConds = jccMarkedCondsStack.removeLast(); // VADI SA STEKA SPISAK (LISTU) USLOVA, STEK JER SE IF-OVI MOGU GNEZDITI
		for (int i = 0; i < jccMarkedConds.size(); i++) {
			if (!jccMarkedConds.get(i).isModified()) {
				Code.put2(jccMarkedConds.get(i).getPc(), Code.pc - jccMarkedConds.get(i).getPc() + 1); // SVIMA AZURIRA SKOKOVE
			}
		}		
		jccMarkedConds.clear();
	}		
	
	//24. IZLAZNA TACKA IF-ELSE NAREDBE
	@Override
	public void visit(Stmt_if_else Stmt_if_else) {
		ArrayList<MarkedCond> jccMarkedConds = jccMarkedCondsStack.removeLast();
		int absMarkerPc = absMarkerPcStack.removeLast(); // LIFO
		for (int i = 0; i < jccMarkedConds.size(); i++) {
			if (!jccMarkedConds.get(i).isModified()) {
				Code.put2(jccMarkedConds.get(i).getPc(),  absMarkerPc - jccMarkedConds.get(i).getPc() + 3);
			}
		}						
		jccMarkedConds.clear();
		Code.put2(absMarkerPc, Code.pc - absMarkerPc + 1);		
	}				
	
	//25. STAVI NA STEK NOVU ARRAY LISTU USLOVA ZA IF U KOJI SE ULAZI - MORA STEK DA SE RESI PROBLEM GNEZEDENJA IF NAREDBI
	public void visit(Stmt_kw_if Stmt_kw_if) { 
		jccMarkedCondsStack.add(new ArrayList<MarkedCond>());
	}	
	
	//26. OK SADA TREBA POSTAVITI INSTRUKCIJU SKOKA KOJA RAZDAVANJA IF OD ELSE A NE ZNAMO JOJ ADRESU, ADRESU RACUNAMO POTOM U IZLAZNOJ TACKI
	@Override
	public void visit(Stmt_if_body Stmt_if_body) {				
		if (Stmt_if_body.getParent().getClass() == Stmt_if_else.class) {
			Code.put(Code.jmp);
//			absMarkerPc = Code.pc;
			absMarkerPcStack.addLast(Code.pc);
			Code.pc += 2; // i opet ostavimo dva prazna mesta jer je adresa skoka dva bajta;
		}
	}
	
	//27. ULAZNA TACKA DO-WHILE-A, MARKIRATI POCETAK DO-WHILE PETLJE, OVDE SE USLOVNO VRACAMO IZ USLOVA DO-WHILE PETLJE, I NE SAMO TO
	// TREBA DODATI NOVU LISTU BREAKOVA RELEVANTNU ZA TAJ DO-WHILE
	@Override
	public void visit(Stmt_kw_do Stmt_kw_do) {		
//		retMarkedPc = Code.pc; //LIFO	
		retMarkedPcStack.addLast(Code.pc);
		breakMarkedPcsStack.add(new ArrayList<Integer>());
	}
	
	//28. IZLAZNA TACKA DO-WHILE-A SVI BREAKOVI SU SADA SAZNALI SVOJU ADRESU
	@Override
	public void visit(Stmt_do_while Stmt_do_while) {								
		//azuriramo sve breakove iz do-while-a iz kojeg trenutno izlazimo
		ArrayList<Integer> breakMarkedPcs = breakMarkedPcsStack.removeLast();
		for (int i = 0; i < breakMarkedPcs.size(); i++) {			
			Code.put2(breakMarkedPcs.get(i), Code.pc - breakMarkedPcs.get(i) + 1); //POPRAVITI ADRESU							
		}		
	}
	
	//29. NAREDBA BREAK, POSTAVITI APSOLUTAN RELATIVAN SKOK CIJU ADRESU SAZNAJEMO KADA SE ZAVRSI PETLJA
	@Override
    public void visit(Stmt_break Stmt_break) {    	
    	Code.put(Code.jmp); //ne vadimo, samo dohvatamo referencu   	
    	breakMarkedPcsStack.getLast().add(Code.pc);
    	Code.pc += 2; //ostavili smo 2B za adresu skoka
    }
	
	//30. CONTINUE - SLEDECA ITERACIJA - TO JE APSOLUTAN RELATIVNI SKOK NA UVEK VRH PETLJE
	@Override
    public void visit(Stmt_continue Stmt_continue) {     	 
    	Code.put(Code.jmp); //ne uklanja, jer ce trebati dole na while da se ukloni
		Code.put2(retMarkedPcStack.getLast() - Code.pc + 1);
    }    	
	//----------------------------------------------------------------------------------------------------------------
	//31. NAREDBE RETURN - NISTA POSEBNO
	@Override
	public void visit(Stmt_return_val Stmt_return_val) {
		Code.put(Code.exit); // OZNACAVA KRAJ METODE
		Code.put(Code.return_); // TEKUCI PC JE POP PC SA STEKA
	}
	
	@Override
	public void visit(Stmt_return Stmt_return) { 
		Code.put(Code.exit);
		Code.put(Code.return_);
	}    
	//----------------------------------------------------------------------------------------------------------------
	//32. NAREDBA DODELE - STORE-OVANJE U UCITANU PROMENLJIVU
	@Override
	public void visit(Assignment Assignment) {		
		Code.store(Assignment.getDesignator().obj);									
	}
	
	//33. INKREMENT - UCITAJ, DODAJ 1, STORUJ
	@Override
	public void visit(Increment Increment) {		
		Code.load(Increment.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(Increment.getDesignator().obj);		
	}
	
	//34. DEKREMENT - UCITAJ, ODUZMI 1, STORUJ
	@Override
	public void visit(Decrement Decrement) {		
		Code.load(Decrement.getDesignator().obj);
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(Decrement.getDesignator().obj);		
	}
	//----------------------------------------------------------------------------------------------------------------
	//35. UCITVANJE NIZA - KORISTI SE TRIK OKRETANJA DVEJU VREDNOSTI NA STEKU DA BE SE NA VRHU STEKA NASAO INDEKS I ISPOD NJEGA ADRESA NIZA
	@Override
	public void visit(Designator_0 Designator_0) {		
		Obj o = null;
		o = getNonMethObj(Designator_0.getID());	
		if (o != null) { // ovaj nacin nam je potreban zbog problema niza, ucitava se indeks pre njegove adrese
//			Obj temp = new Obj(Obj.Var, "temp", Tab.intType);
//			Code.store(temp);
//			Code.load(o);
//			Code.load(temp);
			Code.load(o);
			Code.put(Code.dup_x1);
			Code.put(Code.pop); // dakle ovaj metod okrece vrednosti na steku, zameni se adresa niza sa njegovim indeksom
		}				
	} 
	
	//36. NE RADI NISTA - VISAK KOD-A
	@Override
	public void visit(Designator_1 Designator_1) {
		// ..
	}
	//----------------------------------------------------------------------------------------------------------------
	//37. NEGIRANJE - NISTA POSEBNO
	@Override
	public void visit(Term_multi Term_multi) {				
		SyntaxNode parent = (SyntaxNode) Term_multi.getParent();
		if (parent.getClass() == Expr_neg_term_multi.class 
				|| parent.getClass() == Expr_neg_term_single.class) {
			Code.put(Code.neg);
		}
	}
	
	@Override
	public void visit(Term_single Term_single) { 		
		SyntaxNode parent = (SyntaxNode) Term_single.getParent();
		if (parent.getClass() == Expr_neg_term_multi.class 
				|| parent.getClass() == Expr_neg_term_single.class) {
			Code.put(Code.neg);
		}
	} 
	//----------------------------------------------------------------------------------------------------------------
	//38. AZURIRANJE MUL-OP OPERATORA, OPET TREBA STEK ZBOG SLOZENIH IZRAZA, VISE MNOZENJA ILI DELJENJA, TO TREBA PAMTITI
	@Override
	public void visit(Mul_op_mul Mul_op_mul) {		
		mulOpLoader.addLast(MulOp.opMul);
	}
	@Override
	public void visit(Mul_op_div Mul_op_div) {
		mulOpLoader.addLast(MulOp.opDiv);
	}
	
	@Override
	public void visit(Mul_op_mod Mul_op_mod) {
		mulOpLoader.addLast(MulOp.opMod);
	}
	//39. AZURIRANJE ADD-OP OPERATORA, OPET TREBA STEK ZBOG SLOZENIH IZRAZA, VISE SABIRANJA ILI ODUZIMANJA, TO TREBA PAMTITI
	@Override
	public void visit(Add_op_plus Add_op_plus) {
		addOpLoader.addLast(AddOp.opAdd);
	}
	
	@Override
	public void visit(Add_op_minus Add_op_minus) {
		addOpLoader.addLast(AddOp.opSub);
	}
	
	//40. RACUNANJE IZRAZA, SKIDANJE ADD-OP SA STEKA
	@Override
	public void visit(More_term_part More_term_part) {
		AddOp currAddOp = addOpLoader.removeLast();
		if (currAddOp != null) {			
			switch (currAddOp) {
				case opAdd: 
					Code.put(Code.add);
					break;
				case opSub: 
					Code.put(Code.sub);
					break;
			}
		}
		currAddOp = null;
	}
	
	//41. RACUNANJE IZRAZA, SKIDANJE MUL-OP SA STEKA
	@Override
	public void visit(More_factor_part More_factor_part) {
		MulOp currMulOp = mulOpLoader.removeLast();
		if (currMulOp != null) {						
			switch (currMulOp) {
				case opMul: 
					Code.put(Code.mul);
					break;
				case opDiv:
					Code.put(Code.div);
					break;
				case opMod: 
					Code.put(Code.rem);
					break;
			}
		}		
		currMulOp = null;
	}			
	//----------------------------------------------------------------------------------------------------------------
	//42. TRAZENJE RODITELJA ZA COND_FACT_TWO ILI COND_FACT_ONE, KOJA JE JE TO NAREDBA?
	private SyntaxNode prepare(SyntaxNode syntaxNode) {	// priprema za JumpTrick pronadji roditelja Stmt_if, Stmt_if_else ili stmt_do_while	
		while (syntaxNode.getClass() != Stmt_if.class
				&& syntaxNode.getClass() != Stmt_if_else.class
				&& syntaxNode.getClass() != Stmt_do_while.class
				&& syntaxNode != null) {
			syntaxNode = syntaxNode.getParent();
		}		
		return syntaxNode;
	}								
	
	//43. POSTAVLJANJE SKOKA SA UPAMCIVANJEM DA TREBA TREBA RETROAKTIVNO DODATI TU ADRESU
	private void jumpTrick(boolean isDoWhile, int serialNum, int relOp) {
		if (!isDoWhile) {
			if (relOp == - 1) { // ako je rel. op - 1 nema relacionih operatora, poslato je true ili false
				Code.loadConst(0);
				Code.put(Code.jcc + Code.eq);
			} else {
				Code.put(Code.jcc + Code.inverse[relOp]);
			}
			ArrayList<MarkedCond> jccMarkedConds = jccMarkedCondsStack.getLast();
			jccMarkedConds.add(new MarkedCond(Code.pc, serialNum, relOp)); // ne znamo adresu skoka pa ne mozemo ni da je stavimo ali je stavljamo na spisak za dodati
			Code.pc += 2;
		} else {
			if (relOp == - 1) { // ako je rel. op - 1 nema relacionih operatora, poslato je true ili false
				Code.loadConst(0);
				Code.put(Code.jcc + Code.ne);
			} else {
				Code.put(Code.jcc + relOp);
			}			
			Code.put2(retMarkedPcStack.removeLast() - Code.pc + 1); // mesto u kodu gde se vracamo (jedno jedino, gore) oznaceno sa retMarkerPc	
		}
	}
	
	//44. SETOVANJE ADRESA SKOKA ZA NE PROVERAVANJE DALJE USLOVA, VEC DA SE USKOCI DIREKTNO U IF NAREDBU
	private void repairTrick(MarkedCond markedCond) {
		int oldPc = Code.pc;
		Code.pc = markedCond.getPc() - 1;
		if (markedCond.getRelOp() == -1) {
			Code.put(Code.jcc + Code.ne);
		} else {
			Code.put(Code.jcc + markedCond.getRelOp());
		}
		
		Code.pc = oldPc;
		Code.put2(markedCond.getPc(), Code.pc - markedCond.getPc() + 1);
		markedCond.setModified(true);
	}
	//----------------------------------------------------------------------------------------------------------------
	//45. OBRADA USLOVA
	@Override
	public void visit(Condition Condition) {
//		if (!jccMarkedCondsStack.isEmpty()) {
//			ArrayList<MarkedCond> markedConds = jccMarkedCondsStack.getLast();
//			for (int i = 0; i < markedConds.size(); i++) {
//				if (markedConds.get(i).getSerialID() != 0) {
//					repairTrick(markedConds.get(i));
//				}
//			}
//		}
	}
	
	@Override
	public void visit(Condition_two Condition_two) { // .. = condition OR cond_term		
		serialCounter = 0;
		ArrayList<MarkedCond> markedConds = jccMarkedCondsStack.getLast();
		repairTrick(markedConds.get(markedConds.size() - 1));
	}		
	
	@Override
	public void visit(Condition_one Condition_one) {
		serialCounter = 0;
	}
		
	@Override
    public void visit(Cond_term_two Cond_term_two) { // .. = cond_term AND cond_fact
		serialCounter++; 
    }
    
	@Override
	public void visit(Cond_term_one Cond_term_one) {
		serialCounter++; 
	}
	
	@Override
	public void visit(Cond_fact_two Cond_fact_two) {		
		SyntaxNode stmt = prepare(Cond_fact_two);
		boolean isDoWhile = stmt.getClass() == Stmt_do_while.class; 
		jumpTrick(isDoWhile, serialCounter, currRelOp);
	}
	
	@Override
	public void visit(Cond_fact_one Cond_fact_one) {		
		SyntaxNode stmt = prepare(Cond_fact_one);
		boolean isDoWhile = stmt.getClass() == Stmt_do_while.class; 
		jumpTrick(isDoWhile, serialCounter, -1);
	}
	
	//----------------------------------------------------------------------------------------------------------------
	//46. SETOVANJE TEKUCEG RELACIONOG OPERATORA
	@Override
	public void visit(Rel_op_gt Rel_op_gt) { 
		currRelOp = Code.gt; 		
	}
	
	@Override
	public void visit(Rel_op_eq Rel_op_eq) { 
		currRelOp = Code.eq;		
	}
	
	@Override
	public void visit(Rel_op_lt Rel_op_lt) { 
		currRelOp = Code.lt;		
	}
	
	@Override
	public void visit(Rel_op_ge Rel_op_ge) { 
		currRelOp = Code.ge;		
	}
	
	@Override
	public void visit(Rel_op_le Rel_op_le) { 
		currRelOp = Code.le;		
	}
	
	@Override
    public void visit(Rel_op_ne Rel_op_ne) { 
    	currRelOp = Code.ne;
    }
    	
}
