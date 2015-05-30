
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.LinkedList;


public class CompilationEngine {

	String className;
	String[] parts;
	Writer writer;
	LinkedList<Node2> list=new LinkedList<Node2>();
	
	int counter=0;
	//String [] keyword = {"class" , "constructor" , "function" , "method" , "field" , "static" , "var" , "int" , "char" , "boolean" , "void" , "true" , "false" , "null" , "this" , "let" , "do" , "if" , "else" , "while", "return"}; 
	//char [] symbol = {'{','}','(',')','[',']','.',',',';','+','-','*','/','&','|','<','>','=','~'};

	//----------------------------------------------------------------


	public CompilationEngine(String nameOfFile)
	{
		parts = nameOfFile.split("T.xml");
		nameOfFile=parts[0]+".xml";
		counter=0;
		list.clear();
		try
		{
			writer= new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nameOfFile), "utf-8"));

		}

		catch(Exception e)
		{
			System.err.println("error: constructor");
		}



	}



	//-----------------------------------------------------------------

	public void CompileClass()
	{
		wtf("<class>");
		wtf(createLine(list.get(counter).type,list.get(counter).symbol )); //class
		counter++;
		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//className
		className=list.get(counter).symbol;
		counter++;
		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//'{'
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
				CompileSubroutine();
			}
		}


		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//'}'
		wtf("</class>");

	}
	public void CompileClassVarDec()
	{
		wtf("<classVarDec>");
		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//static/field
		counter++;

		wtf("<type>");
		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//type
		counter++;
		wtf("</type>");

		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//varName
		counter++;

		if(list.get(counter).symbol.equals(","))
		{
			while((list.get(counter).symbol.equals(",")))
			{
				wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//','
				counter++;
				wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//varName
				counter++;
			}
		}

		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//';'
		counter++;
		wtf("</classVarDec>");



	}

	public void CompileSubroutine()
	{
		wtf("<subroutineDec>");
		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//type
		counter++;

		if(list.get(counter).symbol.equals("void"))
		{
			wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//void
			counter++;
		}
		else 
		{
			wtf("<type>");
			wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//type
			counter++;
			wtf("</type>");
		}

		wtf("<subroutineName>");
		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//subroutineName
		counter++;
		wtf("</subroutineName>");

		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//'('
		counter++;

		//-------------parameterList

		wtf("<parameterList>");	
		if(list.get(counter).symbol.equals("int")|| list.get(counter).symbol.equals("char")||list.get(counter).symbol.equals("boolean")||list.get(counter).symbol.equals(className)|| list.get(counter).type.equals("identifier"))
		{
			compileParameterList();
		}		
		wtf("</parameterList>");

		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//')'
		counter++;


		//--------------subroutineBody

		
		if(list.get(counter).symbol.equals("{"))
		{
			CompileSubroutineBody();
		}
		



		wtf("</subroutineDec>");

	}

	public void compileParameterList()
	{
		wtf("<type>");
		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//type
		wtf("</type>");
		counter++;
		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//varName
		counter++;

		if(list.get(counter).symbol.equals(","))
		{
			while(list.get(counter).symbol.equals(","))
			{
				wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//','
				counter++;
				wtf("<type>");
				wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//type
				counter++;
				wtf("</type>");
				wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//varName
				counter++;
			}
		}

	}

	public void CompileSubroutineBody()
	{
		wtf("<subroutineBody>");

		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//'{'
		counter++;

		if(list.get(counter).symbol.equals("var"))
		{
			while(list.get(counter).symbol.equals("var"))
			{
			compileVarDec();
			}
		}


		compileStatements();


		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//'}'
		counter++;

		wtf("</subroutineBody>");

	}

	public void compileVarDec()
	{
		wtf("<varDec>");
		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//var
		counter++;
		wtf("<type>");
		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//type
		wtf("</type>");
		counter++;
		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//varName
		counter++;

		if(list.get(counter).symbol.equals(","))
		{
			while(list.get(counter).symbol.equals(","))
			{
				wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//','
				counter++;
				
				wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//varName
				counter++;
			}
		}

		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//';'
		counter++;
		
		
		
		wtf("</varDec>");
	}

	public void compileStatements()
	{
		wtf("<statements>");

		while(list.get(counter).symbol.equals("let")||list.get(counter).symbol.equals("if")||list.get(counter).symbol.equals("while")||list.get(counter).symbol.equals("do")||list.get(counter).symbol.equals("return"))
		{

			wtf("<statement>");

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

			wtf("</statement>");

		}

		wtf("</statements>");
	}

	public void compileDo()
	{
		wtf("<doStatement>");

		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//do
		counter++;

		//--------------------------------CompileSubroutineCall
		CompileSubroutineCall();
		//--------------------------------CompileSubroutineCall
		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//';'
		counter++;

		wtf("</doStatement>");
	}

	//----------------------------------------------------------let

	public void compileLet()
	{
		wtf("<letStatement>");

		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//let
		counter++;

		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//varName
		counter++;

		//---------------------------expression
		if(list.get(counter).symbol.equals("["))
		{
			wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//'['
			counter++;
			CompileExpression();
			wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//']'
			counter++;
		}
		//---------------------------expression

		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//'='
		counter++;
		//---------------------------expression
		CompileExpression();
		//---------------------------expression

		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//';'
		counter++;


		wtf("</letStatement>");
	}

	//-------------------------------------------------while

	public void compileWhile()
	{
		wtf("<whileStatement>");

		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//while
		counter++;

		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//'('
		counter++;

		//---------------------------expression
		CompileExpression();
		//---------------------------expression

		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//')'
		counter++;

		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//'{'
		counter++;

		//---------------------------Statements
		compileStatements();
		//---------------------------Statements

		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//'}'
		counter++;

		wtf("</whileStatement>");
	}

	public void compileReturn()
	{
		wtf("<returnStatement>");
		
		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//return
		counter++;
		//---------------------------Expression
		if(list.get(counter+1).symbol.equals("[") || list.get(counter).symbol.equals("(") || equalsUnaryOp(list.get(counter).symbol)||
				(list.get(counter).type.equals("identifier") && (list.get(counter+1).symbol.equals("(") || list.get(counter+1).symbol.equals(".")))||
				equalsKeyWordConstant(list.get(counter).symbol)||list.get(counter).type.equals("integerConstant")||list.get(counter).type.equals("stringConstant")||
				list.get(counter+1).symbol.equals("(")||list.get(counter+1).symbol.equals(".")||list.get(counter).type.equals("identifier"))
		{
			CompileExpression();
		}
		//---------------------------Expression

		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//';'
		counter++;

		wtf("</returnStatement>");
	}

	public void compileIf()
	{
		wtf("<ifStatement>");

		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//if
		counter++;

		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//'('
		counter++;

		//---------------------------expression
		CompileExpression();
		//---------------------------expression

		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//')'
		counter++;

		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//'{'
		counter++;

		//---------------------------Statements
		compileStatements();
		//---------------------------Statements

		wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//'}'
		counter++;

		if(list.get(counter).symbol.equals("else"))
		{
			wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//else
			counter++;

			wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//'{'
			counter++;

			//---------------------------Statements
			compileStatements();
			//---------------------------Statements

			wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//'}'
			counter++;


		}

		wtf("</ifStatement>");
	}

	public void CompileExpression()
	{
		wtf("<expression>");

		//----------------------CompileTerm

		CompileTerm();
		if(equalsOp(list.get(counter).symbol)) //if symbol==op
		{
			while(equalsOp(list.get(counter).symbol))
			{
				wtf("<op>");
				wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//op
				wtf("</op>");
				counter++;
				CompileTerm();														//term
			}
		}

		//----------------------CompileTerm

		wtf("</expression>");
	}

	public void CompileTerm()
	{
		wtf("<term>");

		if(list.get(counter+1).symbol.equals("["))// varName '[' expression']'
		{
			wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	// varName 
			counter++;
			wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	// '[' 
			counter++;

			//---------------------CompileExpression
			CompileExpression();
			//---------------------CompileExpression

			wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	// ']' 
			counter++;
		}

		else if(list.get(counter).symbol.equals("("))//'(' expression ')'
		{
			wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	// '(' 
			counter++;

			//---------------------CompileExpression
			CompileExpression();
			//---------------------CompileExpression

			wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	// ')' 
			counter++;			
		}

		else if(equalsUnaryOp(list.get(counter).symbol))//unaryOp term
		{
			wtf("<unaryOp>");
			wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	// unaryOp
			wtf("</unaryOp>");
			counter++;	

			//---------------------CompileTerm()
			CompileTerm();
			//---------------------CompileTerm()

		}
		else if(list.get(counter).type.equals("identifier") && (list.get(counter+1).symbol.equals("(") || list.get(counter+1).symbol.equals(".")))//subroutineCall
		{

			//---------------------CompileSubroutineCall()
			CompileSubroutineCall();
			//---------------------CompileSubroutineCall()

		}
		else if(equalsKeyWordConstant(list.get(counter).symbol)) //keywordConstant
				{
					wtf("<keywordConstant>");
					wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//keywordConstant 
					counter++;
					
					wtf("</keywordConstant>");
				}

		else if(list.get(counter).type.equals("integerConstant"))
		{
			wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	// integerConstant 
			counter++;
		}
		
		else if(list.get(counter).type.equals("stringConstant"))
		{
			wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	// stringConstant  
			counter++;
		}
		else
		{
			wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	//  varName 
			counter++;
		}

		wtf("</term>");
	}


	public void CompileSubroutineCall()
	{
		wtf("<subroutineCall>");

		if(list.get(counter+1).symbol.equals("("))  // '(' expressionList ')' 
		{
			wtf("<subroutineName>");
			wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	// subroutineName
			wtf("</subroutineName>");
			counter++;
			wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	// '('
			counter++;

			//---------------------CompileExpressionList
			CompileExpressionList();
			//---------------------CompileExpressionList()

			wtf(createLine(list.get(counter+1).type,list.get(counter).symbol ));	// ')'
			counter++;

		}

		else if(list.get(counter+1).symbol.equals("."))  // '.' subroutineName '('expressionList ')' 
		{
			wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	// className | varName
			counter++;
			wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	// '.'
			counter++;
			wtf("<subroutineName>");
			wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	// subroutineName
			wtf("</subroutineName>");
			counter++;
			
			wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	// '('
			counter++;

			//---------------------CompileExpressionList

			CompileExpressionList();

			//---------------------CompileExpressionList()

			wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	// ')'
			counter++;

		}


		wtf("</subroutineCall>");
	}

	public void CompileExpressionList()
	{

		wtf("<expressionList>");
		//---------------------CompileExpression   (expression (',' expression)* )?

		if(list.get(counter+1).symbol.equals("[") || list.get(counter).symbol.equals("(") || equalsUnaryOp(list.get(counter).symbol)||
				(list.get(counter).type.equals("identifier") && (list.get(counter+1).symbol.equals("(") || list.get(counter+1).symbol.equals(".")))||
				equalsKeyWordConstant(list.get(counter).symbol)||list.get(counter).type.equals("integerConstant")||list.get(counter).type.equals("stringConstant")||
				list.get(counter+1).symbol.equals("(")||list.get(counter+1).symbol.equals(".")||list.get(counter).type.equals("identifier"))
		{
			
			CompileExpression();
			
			if(list.get(counter).symbol.equals(","))
			{
				while(list.get(counter).symbol.equals(","))
				{
					wtf(createLine(list.get(counter).type,list.get(counter).symbol ));	// ','
					counter++;

					CompileExpression();

				}
			}
			//---------------------CompileExpression

		}
		wtf("</expressionList>");
	}
	//---------------------------------------------------------------
/*
	public void toList(String lineXml)
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
			if(parts[1].isEmpty())
			{
				symbol=">";
				list.add(new Node2(symbol,type));
				return;
			}
			
			parts =temp.split("<");
			if(parts[0].isEmpty())
			{
				symbol="<";
				list.add(new Node2(symbol,type));
				return;
			}
			symbol=parts[0];	
			list.add(new Node2(symbol,type));
			
			
			
		}

	}
	*/
	
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
			System.out.println( " type:"+list.get(i).type + " , symbol:"+list.get(i).symbol);
			

		}
	}

	public void wtf(String command)
	{
		try 
		{
			
			writer.write(command);
			writer.write("\n");
			writer.flush();
		} 

		catch (IOException e) 
		{			
			System.err.println("error: writeToFile");
		}
	}
	public void wtfPL(String command)
	{
		try 
		{
			writer.write(command);
			writer.flush();
		} 

		catch (IOException e) 
		{			
			System.err.println("error: writeToFile");
		}
	}

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
		else if(op.equals("&gt;"))
			return true;
		else if(op.equals("&lt;"))
			return true;
		else if(op.equals("&quot;;"))
			return true;
		else if(op.equals("&amp;"))
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

	public boolean equalsUnaryOp(String keyWord)
	{
		if(keyWord.equals("-"))
			return true;
		else if(keyWord.equals("~"))
			return true;
		return false;
	}



	public String createLine(String type,String symbol)
	{
		String str="";
		str="<"+type+"> " + symbol+ " </"+type+"> "; 
		return str;

	}

}
