package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.Collection;
import java.util.Iterator;

import java_cup.runtime.Symbol;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Scope;
import rs.etf.pp1.symboltable.concepts.Struct;

import org.apache.log4j.*;

public class Compiler {		
	
	private static Logger log = Logger.getLogger(Compiler.class);
	private static boolean addTab = false;
	
	public static void main(String[] args) throws Exception {	
		File f = new File(args[0]);
		if (f.exists()) {
			log.info("Obrada ulaznog fajla " + f.getPath());
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			MJLexer myLexer = new MJLexer(br);
			MJParser myParser = new MJParser(myLexer);
			Symbol sym = myParser.parse();
			SyntaxNode prog = (SyntaxNode) sym.value;														
			if (myParser.isErrorDetected()) {
				log.info("Ulazni fajl ima sintaksne greske!");
			} else {
				log.info("=====================SINTAKSNO_STABLO=========================");
				log.info("\n" + prog.toString());
				log.info("==============================================================");
				
				Tab.init();
				SemanticAnalyzer semanticCheck = new SemanticAnalyzer();			
				prog.traverseBottomUp(semanticCheck);			
				//Tab.dump();
				
				log.info("======================TABELA_SIMBOLA==========================");
				tsDump();
				log.info("==============================================================");
				
				if (semanticCheck.isErrorDetected()) {
					log.error("Ulazni fajl ima semanticke greske!");
				} else {
					log.info("Sintaksna i semanticka analiza uspesno zavrsena!");								
					if (args.length > 1) {						
						log.info("==============================================================");
						log.info("======================GENERISANJE_KODA========================");
						CodeGenerator cg = new CodeGenerator(semanticCheck.getGlobl()); 
						prog.traverseBottomUp(cg);
						
						File f2 = new File(args[1]); 
						if (f2.exists()) {
							f2.delete();
						} 
						
						FileOutputStream fos = new FileOutputStream(f2);						
						
						if (cg.isErrorDetected()) {
							log.error ("Generisanje koda neuspesno!");
						} else {
							log.info("Generisanje koda uspesno zavrseno!");
							Code.write(fos);
							log.info("Generisanje izlaznog fajla " + f2.getPath());
						}
						
						log.info("==============================================================");
					}					
				}										
			}						
			br.close();
		} else {
			log.error("Nema ulaznog fajla!");
		}				
	}
	
	
	private static String structDump(Struct struct) {
		StringBuilder sb = new StringBuilder();
		if (struct.getKind() == Struct.Array) {
			sb.append("array of ");
			sb.append(structDump(struct.getElemType()));
		} else {
			switch (struct.getKind()) {
				case Struct.Int:
					sb.append("int");
					break;
				case Struct.Bool:
					sb.append("bool");
					break;
				case Struct.Char:
					sb.append("char");
					break;
				case Struct.None:
					sb.append("none");
					break;
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	private static String collOfObjDump(Collection<Obj> coll) {
		StringBuilder sb = new StringBuilder();
		Iterator<Obj> i = coll.iterator();
		Obj obj;
		while (i.hasNext()) {
			obj = (Obj) i.next();
			sb.append(objDump(obj));
		}
		return sb.toString();
	}
	
	private static String objDump(Obj obj) {
		StringBuilder sb = new StringBuilder();		
		switch (obj.getKind()) {			
			case Obj.Type:		
				sb.append("Type " + obj.getName() + ": ");
				sb.append(structDump(obj.getType()));
				break;
			case Obj.Prog:
				addTab = true;
				sb.append("\n");
				sb.append("Prog " + obj.getName() + ": ");				
				sb.append(structDump(obj.getType()));						
				Collection<Obj> globlSyms = obj.getLocalSymbols();				
				sb.append(collOfObjDump(globlSyms));
				break;
			case Obj.Meth:
				addTab = true;
				sb.append("Meth " + obj.getName() + ": ");				
				sb.append(structDump(obj.getType()));
				//sb.append("(br param:" + obj.getLevel() + ", br lok: " + (obj.getLocalSymbols().size() - obj.getLevel()) + ")");
				Collection<Obj> localSyms = obj.getLocalSymbols();				
				sb.append(collOfObjDump(localSyms));
				break;
			case Obj.Var:
				if (addTab) {
					sb.append("\t");
				}				
				if (obj.getLevel() == 0) {
					sb.append("Globl ");
				} else if (obj.getLevel() == 1) {
					if (obj.getFpPos() == -1) {
						sb.append("Local ");
					} else {
						sb.append("Param" + obj.getFpPos() + " ");
					}					  
				}
				sb.append("Var " + obj.getName() + ": ");				
				sb.append(structDump(obj.getType()));				
				break;
			case Obj.Con:
				if (addTab) {
					sb.append("\t");
				}
				sb.append("Con " + obj.getName() + ": ");				
				sb.append(structDump(obj.getType()));				
				break;			
		}		
		return sb.toString();
	}
				
	public static String tsdump() {
		StringBuilder sb = new StringBuilder();
		for (Scope s = Tab.currentScope; s != null; s = s.getOuter()) {
			Collection<Obj> coll = s.values();
			sb.append(collOfObjDump(coll));
		}
		return sb.toString();
	}	    

	public static void tsDump() {
		log.info("\n" + tsdump());
	}
	
}
