
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.channels.WritePendingException;
import java.util.LinkedList;

import javax.swing.text.NavigationFilter;


public class CompilationEngine {

	String className;
	String[] parts;
	Writer writer;
	LinkedList<Node2> list=new LinkedList<Node2>();
	SymbolTable symbol;
	String sName,sKind,sType;
	VMWriter vm;
	int counter=0;
	int numOfEx=0;
	int numOfWhile=0;
	int numOfIf=0;
	
	//String [] keyword = {"class" , "constructor" , "function" , "method" , "field" , "static" , "var" , "int" , "char" , "boolean" , "void" , "true" , "false" , "null" , "this" , "let" , "do" , "if" , "else" , "while", "return"}; 
	//char [] symbol = {'{','}','(',')','[',']','.',',',';','+','-','*','/','&','|','<','>','=','~'};

	//----------------------------------------------------------------


	public CompilationEngine(String nameOfFile)
	{
		symbol=new SymbolTable();
		vm= new VMWriter(nameOfFile);
		parts = nameOfFile.split("T.xml");
		nameOfFile=parts[0]+".xml";

		counter=0;
		list.clear();
	}



	//-----------------------------------------------------------------

	public void CompileClass()
	{

		//class
		counter++;
		className=list.get(counter).symbol;//className
		counter++;
		//'{'
		counter++;

		if(list.get(counter).symbol.equals("static") || list.get(counter).symbol.equals("field"))
		{
			while(list.get(counter).symbol.equals("static") || list.get(counter).symbol.equals("field"))
			{
				CompileClassVarDec();
			}
		}

		if(list.get(counter).symbol.equals("constructor") || list.get(counter).symbol.equals("method")||list.get(counter).symbol.equals("function"))
		{
			while(list.get(counter).symbol.equals("constructor") || list.get(counter).symbol.equals("method")||list.get(counter).symbol.equals("function"))
			{
				CompileSubroutineDec();
			}
		}


		//'}'


	}
	public void CompileClassVarDec()
	{


		sKind=list.get(counter).symbol;//static/field
		counter++;

		sType=list.get(counter).symbol;	//type
		counter++;

		sName=list.get(counter).symbol;//varName
		counter++;

		symbol.define(sName, sType, sKind);

		if(list.get(counter).symbol.equals(","))
		{
			while((list.get(counter).symbol.equals(",")))
			{
				//','
				counter++;
				sName=list.get(counter).symbol;//varName
				counter++;

				symbol.define(sName, sType, sKind);

			}
		}

		//';'
		counter++;




	}

	public void CompileSubroutineDec()
	{
		numOfWhile=0;
		numOfIf=0;
		String NameOfFunction="";
		boolean isConstructor=false,isFunction=false,isMethod=false;

		//('constructor' | 'function' | 'method')	
		if(list.get(counter).symbol.equals("constructor"))
			isConstructor=true;
		if(list.get(counter).symbol.equals("function"))
		{
			symbol.methodList.clear();
			isFunction=true;
		}
		if(list.get(counter).symbol.equals("method"))
		{
			symbol.methodList.clear();
			symbol.define("this", className, "argument");
			isMethod=true;		
		}

		counter++;

		if(list.get(counter).symbol.equals("void"))
		{
			//void
			counter++;

		}
		else 
		{	
			//type
			counter++;
		}

		NameOfFunction=className+"."+list.get(counter).symbol;//subroutineName
		counter++;

		//'('
		counter++;

		//-------------parameterList

		if(list.get(counter).symbol.equals("int")|| list.get(counter).symbol.equals("char")||list.get(counter).symbol.equals("boolean")||list.get(counter).symbol.equals(className)||list.get(counter).type.equals("identifier"))
		{
			
			compileParameterList();
		}		

		//')'
		counter++;
		//-------------/parameterList


		//--------------subroutineBody


		if(list.get(counter).symbol.equals("{"))
		{
			//'{'
			counter++;

			if(list.get(counter).symbol.equals("var"))
			{
				while(list.get(counter).symbol.equals("var"))
					compileVarDec();
			}





			vm.writeFunction(NameOfFunction,symbol.varCount("var"));
			if(isConstructor)
			{
				//vm.writeFunction(NameOfFunction,symbol.varCount("var"));
				int numField=symbol.varCount("field"); //number of field in symbolTable
				vm.writePush("constant", numField);
				vm.wtf("call Memory.alloc 1");
				vm.wtf("pop pointer 0");
				isConstructor=false;
			}

			else if(isFunction)
			{
				int numArgs=symbol.varCount("argument");
				/*for (int j = 0; j < numArgs; j++)
				{
					vm.writePush("argument", j);
				}*/

			}
			else if(isMethod)
			{
				int numArgs=symbol.varCount("argument");
				vm.writePush("argument", 0);
				/*for (int j = 0; j < numArgs; j++)
				{
					vm.writePush("argument", j);
				}*/
				vm.writePop("pointer", 0);	
				isMethod=false;

			}

			compileStatements();


			//'}'
			counter++;
		}
		//--------------/subroutineBody

	}


	public void compileParameterList()
	{

		//type
		sType=list.get(counter).symbol;
		counter++;

		//varName
		
		sName=list.get(counter).symbol;
		counter++;

		symbol.define(sName, sType, "argument");//***********argument*********** 
		
		if(list.get(counter).symbol.equals(","))
		{
			while(list.get(counter).symbol.equals(","))
			{
				//','
				counter++;

				//type
				sType=list.get(counter).symbol;
				counter++;

				//varName
				sName=list.get(counter).symbol;
				counter++;

				symbol.define(sName, sType, "argument");//***********argument*********** 
			}
		}
		//symbol.printMethodList();
	}



	public void compileVarDec()
	{

		//var
		sKind=list.get(counter).symbol;
		counter++;

		//type
		sType=list.get(counter).symbol;
		counter++;

		//varName
		sName=list.get(counter).symbol;
		counter++;

		symbol.define(sName, sType, sKind);
		
		if(list.get(counter).symbol.equals(","))
		{
			while(list.get(counter).symbol.equals(","))
			{
				
				//','
				counter++;
				
				sName=list.get(counter).symbol;	//varName		
				counter++;

				symbol.define(sName, sType, sKind);

			}
		}

		//';'
		counter++;




	}

	public void compileStatements()
	{


		while(list.get(counter).symbol.equals("let")||list.get(counter).symbol.equals("if")||list.get(counter).symbol.equals("while")||list.get(counter).symbol.equals("do")||list.get(counter).symbol.equals("return"))
		{



			if(list.get(counter).symbol.equals("let"))
			{
				compileLet();
			}
			else if(list.get(counter).symbol.equals("if"))
			{
				compileIf();
			}
			else if(list.get(counter).symbol.equals("while"))
			{
				compileWhile();
			}
			else if(list.get(counter).symbol.equals("do"))
			{
				compileDo();
			}
			else if(list.get(counter).symbol.equals("return"))
			{
				compileReturn();
			}



		}


	}

	public void compileDo()
	{


		//do
		counter++;

		//--------------------------------CompileSubroutineCall
		CompileSubroutineCall();
		//--------------------------------CompileSubroutineCall
		//';'
		counter++;

		vm.writePop("temp", 0);

	}

	//----------------------------------------------------------let

	public void compileLet()
	{
		String varName,type,kind;
		int index;
		boolean isArray=false;
		//let
		counter++;

		varName=list.get(counter).symbol;//varName
		kind=symbol.kindOf(varName);
		type=symbol.typeOf(varName);
		index=symbol.indexOf(varName);

		counter++;

		//---------------------------expression
		if(list.get(counter).symbol.equals("["))
		{
			isArray=true;
			//'['
			counter++;
			CompileExpression();
			//']'
			counter++;
			
			if(kind.equals("field"))
			{
				vm.writePush("this", index);

			}
			else if(kind.equals("var"))
			{
				vm.writePush("local", index);
			}
			else if(kind.equals("static"))
			{
				vm.writePush("static", index);
			}
			else if(kind.equals("argument"))
			{
				vm.writePush("argument", index);
			}
			
			vm.WriteArithmetic("add");
			
		}
		//---------------------------expression

		//'='
		counter++;
		//---------------------------expression
		CompileExpression();
		//---------------------------expression

		if(isArray)
		{
			vm.writePop("temp", 0);
			vm.writePop("pointer",1);
			vm.writePush("temp", 0);
			vm.writePop("that",0);
			isArray=false;
			
		}
			
		else if(kind.equals("field"))
		{
			vm.writePop("this", index);

		}
		else if(kind.equals("var"))
		{
			vm.writePop("local", index);
		}
		else if(kind.equals("static"))
		{
			vm.writePop("static", index);
		}
		else if(kind.equals("argument"))
		{
			vm.writePop("argument", index);
		}



		//';'
		counter++;



	}

	//-------------------------------------------------while

	public void compileWhile()
	{
		int localNumOfWhile=numOfWhile;
		vm.WriteLabel("label WHILE_EXP"+Integer.toString(localNumOfWhile));
		//while
		counter++;
		//'('
		counter++;

		//---------------------------expression
		CompileExpression();
		//---------------------------expression

		//')'
		counter++;



		vm.wtf("not");
		vm.WriteIf("if-goto WHILE_END"+Integer.toString(localNumOfWhile));

		//'{'
		counter++;


		numOfWhile++;
		//---------------------------Statements
		compileStatements();
		//---------------------------Statements



		//'}'
		counter++;

		vm.WriteGoto("goto WHILE_EXP"+Integer.toString(localNumOfWhile));
		vm.WriteLabel("label WHILE_END"+Integer.toString(localNumOfWhile));




	}

	public void compileReturn()
	{


		//return
		counter++;
		//---------------------------Expression
		if(list.get(counter+1).symbol.equals("[") || list.get(counter).symbol.equals("(") || equalsUnaryOp(list.get(counter).symbol)||
				(list.get(counter).type.equals("identifier") && (list.get(counter+1).symbol.equals("(") | list.get(counter+1).symbol.equals(".")))||
				equalsKeyWordConstant(list.get(counter).symbol)||list.get(counter).type.equals("integerConstant")||list.get(counter).type.equals("stringConstant")||
				list.get(counter+1).symbol.equals("(")||list.get(counter+1).symbol.equals(".")||list.get(counter).type.equals("identifier"))
		{
			CompileExpression();
		}
		//---------------------------Expression
		else
			vm.writePush("constant", 0);
		vm.writeReturn();

		//';'
		counter++;
		//************************************
		symbol.varCounter=0;
		symbol.argCounter=0;
		//symbol.printMethodList();
		//symbol.methodList.clear();
		//numOfWhile=0;
		//numOfIf=0;
		
		//******************************************
	}

	public void compileIf()
	{

		int localNumOfIf=numOfIf;
		//if
		counter++;

		//'('
		counter++;

		//---------------------------expression
		CompileExpression();
		//---------------------------expression

		//')'
		counter++;


		vm.WriteIf("if-goto IF_TRUE"+localNumOfIf);
		vm.WriteGoto("goto IF_FALSE"+localNumOfIf);
		vm.WriteLabel("label IF_TRUE"+localNumOfIf);


		//'{'
		counter++;
		
		//---------------------------Statements
		numOfIf++;
		compileStatements();
		//---------------------------Statements

		//'}'
		
		//vm.WriteLabel("label IF_FALSE"+localNumOfIf);
		counter++;

		if(list.get(counter).symbol.equals("else"))
		{
			//else
			counter++;

			//'{'
			counter++;
			
			vm.WriteGoto("goto IF_END"+localNumOfIf);
			vm.WriteLabel("label IF_FALSE"+localNumOfIf);
			
			
			//---------------------------Statements
				
				compileStatements();
			//---------------------------Statements

			//'}'
			counter++;
			vm.WriteLabel("label IF_END"+localNumOfIf);
			return;
		}
		vm.WriteLabel("label IF_FALSE"+localNumOfIf);
		
	}

	public void CompileExpression()
	{

		//----------------------CompileTerm

		CompileTerm();
		if(equalsOp(list.get(counter).symbol)) //if symbol==op
		{
			while(equalsOp(list.get(counter).symbol))
			{

				String op=list.get(counter).symbol;	//op

				counter++;
				CompileTerm();	//term
				writeOpToVM(op);
			}
		}

		//----------------------CompileTerm


	}

	public void CompileTerm()
	{
		String varName,kind,stringConstant;
		int index;
		if(list.get(counter+1).symbol.equals("["))// varName '['expression']'
		{
			varName=list.get(counter).symbol;	// varName 
			kind=symbol.kindOf(varName);
			index=symbol.indexOf(varName);
			
			counter++;
			// '[' 
			counter++;

			//---------------------CompileExpression
			CompileExpression();
			//---------------------CompileExpression

			// ']' 
			counter++;
			
			if(kind.equals("field"))
			{
				vm.writePush("this", index);

			}
			else if(kind.equals("var"))
			{
				vm.writePush("local", index);
			}
			else if(kind.equals("static"))
			{
				vm.writePush("static", index);
			}
			else if(kind.equals("argument"))
			{
				vm.writePush("argument", index);
			}
			
			vm.WriteArithmetic("add");
			vm.writePop("pointer",1);
			vm.writePush("that", 0);
			
			
		}

		else if(list.get(counter).symbol.equals("("))//'(' expression ')'
		{
			// '(' 
			counter++;

			//---------------------CompileExpression
			CompileExpression();
			//---------------------CompileExpression

			// ')' 
			counter++;			
		}

		else if(equalsUnaryOp(list.get(counter).symbol))//unaryOp term
		{
			String unaryOp=list.get(counter).symbol;// unaryOp
			counter++;	

			//---------------------CompileTerm()
			CompileTerm();
			//---------------------CompileTerm()
			writeUnaryOp(unaryOp);
		}
		else if(list.get(counter).type.equals("identifier") && (list.get(counter+1).symbol.equals("(") || list.get(counter+1).symbol.equals(".")))//subroutineCall
		{

			//---------------------CompileSubroutineCall()
			CompileSubroutineCall();
			//---------------------CompileSubroutineCall()

		}
		else if(equalsKeyWordConstant(list.get(counter).symbol)) //keywordConstant
		{		
			writeKeyWordConstantToVM(list.get(counter).symbol);//keywordConstant -  i write vm code in equalsKeyWordConstant
			counter++;
		}

		else if(list.get(counter).type.equals("integerConstant"))
		{

			index=Integer.parseInt(list.get(counter).symbol);
			vm.writePush("constant",index);// integerConstant 
			counter++;
		}

		else if(list.get(counter).type.equals("stringConstant"))
		{
			stringConstant=list.get(counter).symbol;// stringConstant  
			counter++;
			System.out.println(stringConstant);
			vm.writePush("constant", stringConstant.length());
			vm.writeCall("String.new", 1);
			
			for (int i = 0; i < stringConstant.length(); i++)
			{
				vm.writePush("constant",(int)stringConstant.charAt(i));
				vm.writeCall("String.appendChar", 2);
				//System.out.println(stringConstant.charAt(i));
			}
						
		}
		else
		{
			varName=list.get(counter).symbol;	// varName 
			kind=symbol.kindOf(varName);
			index=symbol.indexOf(varName);
		
			
			if(kind.equals("argument"))
			{
				vm.writePush("argument",index );
			}
			else if(kind.equals("var"))
			{
				vm.writePush("local",index );
			}
			else if(kind.equals("field"))
			{
				vm.writePush("this",index );
			}
			else if(kind.equals("static"))
			{
				vm.writePush("static",index );
			}


			counter++;
		}


	}


	public void CompileSubroutineCall()
	{
		int nArgs,index;
		boolean isMethod=false,isConstructor=false;
		String methodName="";
		String localSName;
		if(list.get(counter+1).symbol.equals("("))  // '(' expressionList ')' 
		{

			localSName=className+".";
			localSName+=list.get(counter).symbol;	// subroutineName  method
			
			counter++;
			// '('
			counter++;
			vm.writePush("pointer", 0);
			//---------------------CompileExpressionList
			numOfEx=0;
			nArgs=CompileExpressionList();
			//---------------------CompileExpressionList()

			// ')'
			counter++;


			vm.writeCall(localSName, nArgs+1);
			//vm.writePop("temp", 0);
		}

		else if(list.get(counter+1).symbol.equals("."))  // (className | varName)'.' subroutineName '('expressionList ')' 
		{
			
			localSName=list.get(counter).symbol;	// className | varName
			counter++;
			
			if(list.get(counter+1).symbol.equals("new"))
				isConstructor=true;
			if(symbol.isArg(localSName))
			{
				index=symbol.indexOf(localSName);
				localSName=symbol.typeOf(localSName);
				isMethod=true;
				methodName=localSName;
				vm.writePush("argument",index );	
			}
			
			else if(symbol.isfield(localSName) && isConstructor==false )
			{		
				index=symbol.indexOf(localSName);
				localSName=symbol.typeOf(localSName);
				isMethod=true;
				methodName=localSName;
				vm.writePush("this",index );	
			}
			else if(symbol.isVar(localSName))
			{
				localSName=symbol.typeOf(localSName);
				isMethod=true;
				methodName=localSName;
				vm.writePush("local", 0);
			}
			else if(symbol.isStatic(localSName))
			{
				index=symbol.indexOf(localSName);
				localSName=symbol.typeOf(localSName);
				isMethod=true;
				methodName=localSName;
				vm.writePush("static",index );
			}
			

			localSName+=list.get(counter).symbol;	// '.'
			counter++;

			localSName+=list.get(counter).symbol;	// subroutineName
			counter++;
			
			// '('
			counter++;

			//---------------------CompileExpressionList
			
			nArgs=CompileExpressionList();

			//---------------------CompileExpressionList()

			// ')'
			counter++;
			
			 if(localSName.contains(".new"))
			{
				 vm.writeCall(localSName, nArgs);
				 return;
			}
			 else if(isMethod)
			{			
				vm.writeCall(localSName, nArgs+1);
				return;
			}

			vm.writeCall(localSName, nArgs);

		}



	}

	public int CompileExpressionList()
	{
		int localNumOfEx=0;

		//---------------------CompileExpression   (expression (',' expression)* )?

		if(list.get(counter+1).symbol.equals("[") || list.get(counter).symbol.equals("(") || equalsUnaryOp(list.get(counter).symbol)||
				(list.get(counter).type.equals("identifier") && (list.get(counter+1).symbol.equals("(") || list.get(counter+1).symbol.equals(".")))||
				equalsKeyWordConstant(list.get(counter).symbol)||list.get(counter).type.equals("integerConstant")||list.get(counter).type.equals("stringConstant")||
				list.get(counter+1).symbol.equals("(")||list.get(counter+1).symbol.equals(".")||list.get(counter).type.equals("identifier"))
		{
			localNumOfEx++;
			CompileExpression();
			
			if(list.get(counter).symbol.equals(","))
			{
				
				while(list.get(counter).symbol.equals(","))
				{
					// ','
					counter++;
					localNumOfEx++;
					CompileExpression();
					
				}
			}
			//---------------------CompileExpression

		}

		return localNumOfEx;
	}
	//---------------------------------------------------------------

	/*public void toList(String lineXml)
	{	

		String type,symbol="",temp;
		String[] parts;

		parts =lineXml.split("<");
		temp=parts[1];
		parts =temp.split(">");
		type=parts[0];

		if(!(type.equals("tokens")||type.equals("/tokens")))
		{
			parts =lineXml.split(">");
			temp=parts[1];
			parts =temp.split("<");
			if(parts[0].isEmpty())
			{

				symbol="<";
				list.add(new Node2(symbol,type));
				return;
			}
			symbol=parts[0];	
			System.out.println(type+":"+symbol);
			list.add(new Node2(symbol,type));
		}
	}*/
	
	public void toList(String lineXml)
	{
		String type,symbol="",temp;
		String[] parts;
		parts =lineXml.split(" ");
		type=parts[0];
		parts=type.split("<");
		type=parts[1];
		parts=type.split(">");
		type=parts[0];
		
		if(!(type.equals("tokens")||type.equals("/tokens")))
			{
				parts =lineXml.split(">");
				symbol=parts[1];
				parts =symbol.split("<");
				symbol=parts[0];
				//symbol=symbol.trim();
				//System.out.println(type+":"+symbol);
				symbol=symbol.substring(1, symbol.length()-1);
				list.add(new Node2(symbol,type));
			}
		
			
	}

	public void printList()
	{
		for (int i = 0; i < list.size(); i++) 
		{
			System.out.println("symbol: "+list.get(i).symbol);
			System.out.println("type: "+list.get(i).type);

		}
	}

	/*public void wtf(String command)
	{

	}*/


	public boolean equalsOp(String op)
	{
		if(op.equals("+"))	
			return true;	
		else if(op.equals("-"))	
			return true;
		else if(op.equals("*"))			
			return true;	
		else if(op.equals("/"))	
			return true;
		else if(op.equals("&"))		
			return true;	
		else if(op.equals("|"))		
			return true;		
		else if(op.equals("<"))			
			return true;		
		else if(op.equals(">"))			
			return true;		
		else if(op.equals("^"))			
			return true;	
		else if(op.equals("="))		
			return true;
		else if(op.equals("&lt;"))
			return true;
		else if(op.equals("&amp;"))
			return true;
		else if(op.equals("&gt;"))
			return true;
		return false;
	}

	public boolean equalsKeyWordConstant(String keyWord)
	{
		if(keyWord.equals("true"))
			return true;
		else if(keyWord.equals("false"))
			return true;

		else if(keyWord.equals("null"))
			return true;
		else if(keyWord.equals("this"))
			return true;
		return false;
	}
	public void writeOpToVM(String op)
	{
		if(op.equals("+"))		
			vm.wtf("add");		
		else if(op.equals("-"))
			vm.wtf("sub");					
		else if(op.equals("*"))		
			vm.wtf("call Math.multiply 2");		
		else if(op.equals("/"))		
			vm.wtf("call Math.divide 2");			
		else if(op.equals("&"))		
			vm.wtf("and");		
		else if(op.equals("|"))		
			vm.wtf("or");					
		else if(op.equals("<"))		
			vm.wtf("lt");					
		else if(op.equals(">"))	
			vm.wtf("gt");			
		else if(op.equals("^"))	
			vm.wtf("call Math.power 2");	
		else if(op.equals("="))
			vm.wtf("eq");
		else if(op.equals("&lt;"))
			vm.wtf("lt");
		else if(op.equals("&amp;"))
			vm.wtf("and");
		else if(op.equals("&gt;"))
			vm.wtf("gt");




	}
	public void writeKeyWordConstantToVM(String keyWord)
	{
		if(keyWord.equals("true"))
		{
			vm.writePush("constant", 0);
			vm.wtf("not");
		}
		else if(keyWord.equals("false"))		
			vm.writePush("constant", 0);		
		else if(keyWord.equals("null"))
			vm.writePush("constant", 0);
		else if(keyWord.equals("this"))
			vm.writePush("pointer", 0);



	}

	public boolean equalsUnaryOp(String keyWord)
	{
		if(keyWord.equals("-"))
			return true;
		else if(keyWord.equals("~"))	
			return true;
		return false;
	}
	public void writeUnaryOp(String keyWord)
	{
		if(keyWord.equals("-"))
			vm.wtf("neg");				
		else if(keyWord.equals("~"))		
			vm.wtf("not");			

	}


	public String createLine(String type,String symbol)
	{
		String str="";
		str="<"+type+"> " + symbol+  " </"+type+">"; 
		return str;

	}

	public void printMethodList()
	{

		symbol.printMethodList();
	}

	public void printClassList()
	{

		symbol.printClassList();
	}

}
