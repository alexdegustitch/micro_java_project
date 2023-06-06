package rs.ac.bg.etf.pp1;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class SemanticAnalyzer extends VisitorAdaptor {		
	
	private static Struct boolType = Tab.insert(Obj.Type, "bool", new Struct(Struct.Bool)).getType();	 // treba nam bool jer nedostaje Tab.boolType!
	
	private boolean errorDetected = false;
	private Obj globl = null;
	private Obj currentMethod = null;	
	private boolean returnValFound = false;
	
	private ArrayList<Var> myVars = new ArrayList<Var>();	// lista promenljivih je neophodna, jer se prvo dohvataju imena odvojena zarezom pa onda tip kad se kompletira smena!
	private ArrayList<Method> myMethods = new ArrayList<Method>(); // lista mojih metoda sa (formalnim) parametrima
	private ArrayList<Struct> tempArgs = new ArrayList<Struct>(); // list argumenata odnosno stvarnih parametara pri pozivu  
	
	private Logger log = Logger.getLogger(getClass());	
	
	//----------------------------------------------------------------------------------------------------------------
	//1. DOHVATI ZELJENU METODU IZ LISTE MOJIH METOTDA
	private int getMyMethod(String desiredName) { // dohvata zeljenu metodu iz array list-e mojih metoda
		for (int i = 0; i < myMethods.size(); i++) {
			if (myMethods.get(i).getName().equals(desiredName)) {
				return i;
			}
		}
		return -1;
	}	
	//----------------------------------------------------------------------------------------------------------------
	//2. OBRADA PORUKA ILI GRESAKA
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
	//3. GETTERI I SETTERI	
	public static Struct getBoolType() {
		return boolType;
	}
	
	public boolean isErrorDetected() {
		return errorDetected;
	}				
	
	public Obj getGlobl() {
		return globl;
	}			
	//----------------------------------------------------------------------------------------------------------------
	//4. IZLAZNE TACKE PROGRAMA: 0..3
	@Override	    			
	public void visit(Program_0 Program_0) { 
		Tab.chainLocalSymbols(globl);
		report_info("Zavrsetak obrade programa " + globl.getName() , Program_0);
		Tab.closeScope();
	}
	
	@Override	    			
	public void visit(Program_1 Program_1) { 
		Tab.chainLocalSymbols(globl);
		report_info("Zavrsetak obrade programa " + globl.getName() , Program_1);
		Tab.closeScope();
	}
	
	@Override	    			
	public void visit(Program_2 Program_2) { 
		Tab.chainLocalSymbols(globl);
		report_info("Zavrsetak obrade programa " + globl.getName() , Program_2);
		Tab.closeScope();
	}
	
	@Override	    			
	public void visit(Program_3 Program_3) { 
		Tab.chainLocalSymbols(globl);
		report_info("Zavrsetak obrade programa " + globl.getName() , Program_3);
		Tab.closeScope();
	}
	//5. ULAZNA TACKA PROGRAMA
	@Override
	public void visit(Program_name Program_name) { 		
		globl = Tab.insert(Obj.Prog, Program_name.getProgramName(), Tab.noType);
		report_info("Pocetak obrade programa " + globl.getName() , Program_name);
		Tab.openScope();
	}
	
	//----------------------------------------------------------------------------------------------------------------	
	//6. OBRADA DEKLARACRIONE LISTE KONSANTI
	@Override
	public void visit(Const_declaration Const_declaration) {		
		Struct type = Const_declaration.getType().struct;
		for (int i = 0; i < myVars.size(); i++) {
			report_info("Deklarisana konstanta " + myVars.get(i).getName(), Const_declaration);
			Tab.insert(Obj.Con, myVars.get(i).getName(), type);		
		}
		myVars.clear();
	}	
	//7. OBRADA JEDNE KONSTANTE
	@Override
	public void visit(Const_part Const_part) { 
		if (Tab.find(Const_part.getConstName()) == Tab.noObj) {
			myVars.add(new Var(Const_part.getConstName()));
		} else {			
			report_error("Semanticka greska - ime " + Const_part.getConstName() + " vec postoji", Const_part);
		}
	}
	
	//8. OBRADA DEKLARACTIONE LISTE PROMENLJIVIH
	@Override
    public void visit(Var_declaration Var_declaration) { 
    	Struct type = Var_declaration.getType().struct;
    	for (int i = 0; i < myVars.size(); i++) {
	    	if (myVars.get(i).isArray()) {
	    		report_info("Deklarisan niz " + myVars.get(i).getName(), Var_declaration); 
	    		Obj obj = Tab.insert(Obj.Var, myVars.get(i).getName(), new Struct(Struct.Array, type));
	    		obj.setFpPos(-1);
	    	} else {
	    		report_info("Deklarisana promenljiva " + myVars.get(i).getName(), Var_declaration);
	    		Obj obj = Tab.insert(Obj.Var, myVars.get(i).getName(), type);
	    		obj.setFpPos(-1);
	    	}    	
    	}
    	myVars.clear();
    }    			    	
    
	//8. OBRADA JEDNE PROMENLJIVE - NIZA
	@Override
    public void visit(Var_array Var_array) { 
		if (Tab.find(Var_array.getVarName()) == Tab.noObj) {
			myVars.add(new Var(Var_array.getVarName(), true));			
		} else { 
			report_error("Semanticka greska - ime " + Var_array.getVarName() + " vec postoji", Var_array);
		}
    }
    
	//9. OBRADA JEDNE PROMENLJIVE - OBICNE
	@Override
    public void visit(Var_normal Var_normal) {
		if (Tab.find(Var_normal.getVarName()) == Tab.noObj) {
			myVars.add(new Var(Var_normal.getVarName()));			
		} else { 			
			report_error("Semanticka greska - ime" + Var_normal.getVarName() + " je vec postoji", Var_normal);
		}
	}
    
	@Override
    public void visit(Var_error Var_error) { visit(); }
	
    //----------------------------------------------------------------------------------------------------------------
	//10. OBRADA TIPA - DA LI ON POSTOJI U TABELI SIMBOLA?
	@Override
    public void visit(Type Type) { // prilagodjen kodu sa lab vezbi 
    	Obj typeNode = Tab.find(Type.getTypeName());
		if (typeNode == Tab.noObj) {
			report_error("Semanticka greska - nije pronadjen tip " + Type.getTypeName() + " u tabeli simbola!", null);
			Type.struct = Tab.noType;
		} 
		else {
			if (Obj.Type == typeNode.getKind()) {
				report_info("Pronadjen tip " + Type.getTypeName() + " u tabeli simbola", Type);
				Type.struct = typeNode.getType();
			} 
			else {
				report_error("Semanticka greska - ime " + Type.getTypeName() + " ne predstavlja tip", Type);
				Type.struct = Tab.noType;
			}
		}
    }	
	//----------------------------------------------------------------------------------------------------------------
	//11. OBRADA VREDNOSTI KONSANTI, TO JE VALUE.
	@Override
	public void visit(Val_Num_const Val_Num_const) { 
		Val_Num_const.struct = Tab.intType;
	}
	
	@Override
	public void visit(Val_Char_const Val_Char_const) { 
		Val_Char_const.struct = Tab.charType;
	}    
	
	@Override
	public void visit(Val_Bool_const Val_Bool_const) { 
		Val_Bool_const.struct = boolType;
	}
    //----------------------------------------------------------------------------------------------------------------
	//12. ZAJEDNICKA IZLAZNA TACKA VOID METODA; ONA NE TREBA DA VRACA IKAKVU VREDNOST!
	@Override
	public void visit(Meth_void_decl Meth_void_decl) { 
		if (returnValFound) {
			report_error("Semanticka greska - metoda (procedura) " + currentMethod.getName() + " ne treba da ima povratnu vrednost!", null);
		}
		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();
		report_info("Zavrsetak obrade metode (procedure) " + currentMethod.getName(), Meth_void_decl);
		currentMethod = null;
		returnValFound = false;
	}
	
	//13. ZAJEDNICKA IZLAZNA TACKA METODA NEKOG TIPA; ONA TREBA DA VRACA NEKAKVU VREDNOST!
	public void visit(Meth_type_decl Meth_type_decl) { 
		if (!returnValFound) {
			report_error("Semanticka greska - metoda (funkcija) " + currentMethod.getName() + " treba da ima povratnu vrednost!", null);
		}
		Tab.chainLocalSymbols(currentMethod);
		Tab.closeScope();		
		report_info("Zavrsetak obrade metode (funkcije) " + currentMethod.getName(), Meth_type_decl);
		currentMethod = null;
		returnValFound = false;
	}    		
	
	//14. ULAZNA TACKA VOID METODA
	@Override
	public void visit(Method_void_name Method_void_name) { 
		currentMethod = Tab.insert(Obj.Meth, Method_void_name.getMethodName(), Tab.noType);
		myMethods.add(new Method(Method_void_name.getMethodName()));		
		Tab.openScope();
		report_info("Pocetak obrade metode " + Method_void_name.getMethodName(), Method_void_name);
	}				
	//15. ULAZNA TACKA METODA NEKOG TIPA
	@Override
	public void visit(Method_type_name Method_type_name) { 
		currentMethod = Tab.insert(Obj.Meth, Method_type_name.getMethodName(), Method_type_name.getType().struct);		
		myMethods.add(new Method(Method_type_name.getMethodName()));		
		Tab.openScope();
		report_info("Pocetak obrade metode " + Method_type_name.getMethodName(), Method_type_name);
	}
    
	//15. OBRADA NIZOVNOG PARAMETRA METODE
	@Override
	public void visit(Param_array Param_array) {
		if (Tab.find(Param_array.getParamName()) == Tab.noObj){
			Obj obj = Tab.insert(Obj.Var, Param_array.getParamName(), new Struct(Struct.Array, Param_array.getType().struct));
			if (myMethods.size() > 0) {
				myMethods.get(myMethods.size() - 1).getParams().add(obj.getType());
			}			
			obj.setFpPos(currentMethod.getLevel());
			currentMethod.setLevel(currentMethod.getLevel() + 1);
		} else {
			report_error("Semanticka greska - ime " + Param_array.getParamName() + " vec postoji", Param_array);
		}
	}
	
	//16. OBRADA OBICNOG PARAMETRA METODE
	@Override
    public void visit(Param_normal Param_normal) {
		if (Tab.find(Param_normal.getParamName()) == Tab.noObj) {
	    	Obj obj = Tab.insert(Obj.Var, Param_normal.getParamName(), Param_normal.getType().struct);
	    	if (myMethods.size() > 0) {
	    		myMethods.get(myMethods.size() - 1).getParams().add(obj.getType());
	    	}
	    	obj.setFpPos(currentMethod.getLevel());
			currentMethod.setLevel(currentMethod.getLevel() + 1);
		} else {
			report_error("Semanticka greska - ime " + Param_normal.getParamName() + " vec postoji", Param_normal);
		}
    }    
    
	@Override
    public void visit(Param_error Param_error) { visit(); }
	//----------------------------------------------------------------------------------------------------------------
	//17. OBRADA NAREDBE READ
	@Override
	public void visit(Stmt_Read Stmt_Read) {
		Obj obj = Stmt_Read.getDesignator().obj;
		if (obj.getKind() == Obj.Var || obj.getKind() == Obj.Elem) {
			if (!obj.getType().equals(Tab.intType) && !obj.getType().equals(Tab.charType) && !obj.getType().equals(boolType)) {
				report_error("Semanticka greska - designator read naredbe nije int, char ili bool tipa", Stmt_Read);
			}
		} else {
			report_error("Semanticka greska - designator read naredbe nije promenljiva ili element niza", Stmt_Read);
		}
	}
	
	//18. OBRADA JEDNOG OBLIKA NAREDBE PRINT
	@Override
	public void visit(Stmt_print_0 Stmt_print_0) {
		Struct expr = Stmt_print_0.getExpr().struct;
		if (expr != null) {
			if (!expr.equals(Tab.intType) && !expr.equals(Tab.charType) && !expr.equals(boolType)) {
				report_error("Semanticka greska - izraz print naredbe nije int, char ili bool tipa", Stmt_print_0);
			}
		}
	}
	
	//19. OBRADA DRUGOG OBLIKA NAREDBE PRINT
	@Override
	public void visit(Stmt_print_1 Stmt_print_1) {	
		Struct expr = Stmt_print_1.getExpr().struct;
			if (expr != null) {
			if (!expr.equals(Tab.intType) && !expr.equals(Tab.charType) && !expr.equals(boolType)) {
				report_error("Semanticka greska - izraz print naredbe nije int, char ili bool tipa", Stmt_print_1);
			}		
		}
	} 
	
	//20. OBRADA NAREDBE RETURN NEKE VREDNOSTI
	public void visit(Stmt_return_val Stmt_return_val) { 
		 returnValFound = true;
		 if (!currentMethod.getType().compatibleWith(Stmt_return_val.getExpr().struct)) {
			 report_error("Semanticka greska - tip povratne vrednosti metode i tip vrednosti izraza u return nardebi se ne slazu", Stmt_return_val);
		 }
	}			
	//----------------------------------------------------------------------------------------------------------------
	//21. OBRADE IZRAZA
	@Override
	public void visit(Expr_neg_term_multi Expr_neg_term_multi) { 
		Expr_neg_term_multi.struct = Expr_neg_term_multi.getTerm().struct;
	}
	
	@Override
	public void visit(Expr_neg_term_single Expr_neg_term_single) {
		Expr_neg_term_single.struct = Expr_neg_term_single.getTerm().struct;
	}
	
	@Override
	public void visit(Expr_term_multi Expr_term_multi) { 
		Expr_term_multi.struct = Expr_term_multi.getTerm().struct;
	}
	
	@Override
	public void visit(Expr_term_single Expr_term_single) { 
		Expr_term_single.struct = Expr_term_single.getTerm().struct;
	}
	
	//22. E OVO JE VAZNO - OBRADA POZIVA METODE (FUNKCIJE) CIJA SE VREDNOST DODELJUJE IZRAZU
	@Override
	public void visit(Expr_meth_call Expr_meth_call) { 
		Expr_meth_call.struct = Expr_meth_call.getDesignator().obj.getType();
		if (Expr_meth_call.getDesignator().obj.getKind() == Obj.Meth) {
			report_info("Pronadjen poziv metode (funkcije) " + Expr_meth_call.getDesignator().obj.getName(), Expr_meth_call);
			int index = getMyMethod(Expr_meth_call.getDesignator().obj.getName());
			if (index != -1) { 
				Method meth = myMethods.get(index);
				if (!meth.getParams().equals(tempArgs)) {
					report_error("Semanticka greska - invalidni argumenti za metodu (funkciju) " + Expr_meth_call.getDesignator().obj.getName(), Expr_meth_call);
				}
			}
		} else {
			report_error("Semanticka greska - " + Expr_meth_call.getDesignator().obj.getName() + " nije metoda (funkcija)", Expr_meth_call);
		}
		tempArgs.clear();
	}
	
	public void visit(Expr_error Expr_error) { visit(); }                                
	//----------------------------------------------------------------------------------------------------------------
	//23. TERMI.. NISTA POSEBNO
	@Override
	public void visit(Term_multi Term_multi) { 
		Term_multi.struct = Term_multi.getFactor().struct;
	}
	
	@Override
	public void visit(Term_single Term_single) { 
		Term_single.struct = Term_single.getFactor().struct;
	}    
	//----------------------------------------------------------------------------------------------------------------
	//24. OBRADA FAKTORA, NUM_CONST, CHAR_CONST I BOOL_CONST
	@Override
	public void visit(Factor_num_const Factor_num_const) { 
		Factor_num_const.struct = Tab.intType;
	}
	
	@Override
	public void visit(Factor_char_const Factor_char_const) { 
		Factor_char_const.struct = Tab.charType;
	}
	
	@Override
	public void visit(Factor_expr Factor_expr) { 
		Factor_expr.struct = Factor_expr.getExpr().struct;
	}
	
	@Override
	public void visit(Factor_bool_const Factor_bool_const) { 
		Factor_bool_const.struct = boolType;
	}
	
	//25. OBICAN FACTOR NEW - NE KORISTI SE!
	@Override
	public void visit(Factor_new Factor_new) {
		Factor_new.struct = Factor_new.getType().struct;
	} 
	
	//26. OBICAN FACTOR NEW ARRAY - JAKO VAZAN ZA ALOCIRANJE (STATICKOG) NIZA
	@Override
	public void visit(Factor_new_array Factor_new_array) {
		if (Factor_new_array.getExpr().struct == Tab.intType) {
			Factor_new_array.struct = new Struct(Struct.Array, Factor_new_array.getType().struct);			
		} else {
			report_error("Semanticka greska - izraz [expr] za new naredbu moze biti jedino int", Factor_new_array);
		}
	}
	
	//27. POZIV FUNKCIJE CIJA SE VREDNOST SMESTA U FAKTOR
	@Override
	public void visit(Factor_meth_call Factor_meth_call) {
		Factor_meth_call.struct = Factor_meth_call.getDesignator().obj.getType();
		if (Factor_meth_call.getDesignator().obj.getKind() == Obj.Meth) {
			report_info("Pronadjen poziv metode (funkcije) " + Factor_meth_call.getDesignator().obj.getName(), Factor_meth_call);
			int index = getMyMethod(Factor_meth_call.getDesignator().obj.getName());
			if (index != -1) { 
				Method meth = myMethods.get(index);
				if (!meth.getParams().equals(tempArgs)) {
					report_error("Semanticka greska - invalidni argumenti za metodu (funkciju) " + Factor_meth_call.getDesignator().obj.getName(), Factor_meth_call);
				}
			}
		} else {
			report_error("Semanticka greska - " + Factor_meth_call.getDesignator().obj.getName() + " nije metoda (funkcija)", Factor_meth_call);
		}
		tempArgs.clear();
	} 
	
	//28. FAKTOR MOZE BITI DESIGNATOR, A DESIGNATORI SU PROMENLJIVE (OBICNE ILI ELEMENTI NIZA) ILI KONSTANTE
	@Override
	public void visit(Factor_designator Factor_designator) { 
		Factor_designator.struct = Factor_designator.getDesignator().obj.getType();
	}
          
	//----------------------------------------------------------------------------------------------------------------
	//29. DESIGNATOR - ELEMENT NIZA
	@Override
	public void visit(Designator_0 Designator_0) {
		Obj obj = Tab.find(Designator_0.getID());
		if (obj == Tab.noObj) { 
			report_error("Semanticka greska - ime " + Designator_0.getID() + " nije deklarisano", Designator_0);
		}
		if (Designator_0.getExpr().struct != Tab.intType) {
			report_error("Semanticka greska - invalidan pristup elementu niza " + Designator_0.getID(), Designator_0);
		} else if (obj.getType().getKind() != Struct.Array){
			report_error("Semanticka greska - ime " + Designator_0.getID() + " se ocekuje da bude niz", Designator_0);
		}
		Designator_0.obj = new Obj(Obj.Elem, obj.getName(), obj.getType().getElemType());		 
	} 
	//30. DESIGNATOR - MOZE BITI PROMENLJIVA ILI KONSTANTA KOJOJ SE OBRACAMO
	@Override
	public void visit(Designator_1 Designator_1) { 
		Obj obj = Tab.find(Designator_1.getID());
		if (obj == Tab.noObj) { 
			report_error("Semanticka greska - ime " + Designator_1.getID() + " nije deklarisano", Designator_1);
		}		
		Designator_1.obj = obj;
	}
	//----------------------------------------------------------------------------------------------------------------
	//31. NAREDBA DODELA VREDNOSTI, LEVA I DESNA STRANA MORAJU BITI PO TIPU KOMPATIBILNE
	@Override
	public void visit(Assignment Assignment) {		
		Struct left = Assignment.getDesignator().obj.getType();
		Struct right = Assignment.getExpr().struct;		
		if (!right.assignableTo(left)) {
			report_error("Semanticka greska - nekompatibilni tipovi za dodelu vrednosti", Assignment);
		}
	}
	
	//32. NAREDBA INKREMENT, INKREMENTIRATI JEDINO INT
	@Override
	public void visit(Increment Increment) { 
		if (Increment.getDesignator().obj.getType() != Tab.intType) {
			report_error("Semanticka greska - tip za inkrement moze biti jedino int", Increment);
		}
	}
	
	//33. NAREDBA INKREMENT, DEKREMENTIRATI JEDINO INT
	@Override
	public void visit(Decrement Decrement) { 
		if (Decrement.getDesignator().obj.getType() != Tab.intType) {
			report_error("Semanticka greska - tip za inkrement moze biti jedino int", Decrement);
		}
	}
	
	//----------------------------------------------------------------------------------------------------------------
	//34. ARGUMENTI FUNCIJE - DODATI U PRIVREMENTU LISTU
	@Override
	public void visit(Act_Pars_Single Act_Pars_Single) { 
		tempArgs.add(Act_Pars_Single.getExpr().struct);
	}
	
	@Override
	public void visit(Act_Pars_Multi Act_Pars_Multi) { 
		tempArgs.add(Act_Pars_Multi.getExpr().struct);
	}    	
	//----------------------------------------------------------------------------------------------------------------
	//35. POZIV PROCEDURE
	@Override
	public void visit(Method_call_0 Method_call_0) {
		if (Method_call_0.getDesignator().obj.getKind() == Obj.Meth) {
			report_info("Pronadjen poziv metode (procedure) " + Method_call_0.getDesignator().obj.getName(), Method_call_0);
			int index = getMyMethod(Method_call_0.getDesignator().obj.getName());
			if (index != -1) { 
				Method meth = myMethods.get(index);
				if (!meth.getParams().equals(tempArgs)) {
					report_error("Semanticka greska - invalidni argumenti za metodu (proceduru) " + Method_call_0.getDesignator().obj.getName(), Method_call_0);
				}
			}
		} else {
			report_error("Semanticka greska - " + Method_call_0.getDesignator().obj.getName() + " nije metoda (procedura) ", Method_call_0);
		}
		tempArgs.clear();
	}
	//36. I OPET POZIV PROCEDURE
	@Override
	public void visit(Method_call_1 Method_call_1) { 
		if (Method_call_1.getDesignator().obj.getKind() == Obj.Meth) {   
			report_info("Pronadjen poziv metode (procedure) " + Method_call_1.getDesignator().obj.getName(), Method_call_1);
			int index = getMyMethod(Method_call_1.getDesignator().obj.getName());
			if (index != -1) { 
				Method meth = myMethods.get(index);
				if (!meth.getParams().equals(tempArgs)) {
					report_error("Semanticka greska - invalidni argumenti za metodu (proceduru) " + Method_call_1.getDesignator().obj.getName(), Method_call_1);
				}
			}
		} else {
			report_error("Semanticka greska - " + Method_call_1.getDesignator().obj.getName() + " nije metoda (procedura)", Method_call_1);
		}
		tempArgs.clear();
	}
	//----------------------------------------------------------------------------------------------------------------
    
}
