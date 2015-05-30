import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class Parser {
	CodeWriter code;
	String[] parts;
	String line,arg0,arg1,arg2;
	//String args;


	public Parser(String args)
	{
		
        
		
		//System.out.println(args);
		code= new CodeWriter(args);
		try(BufferedReader br = new BufferedReader(new FileReader(args))) 
		{

			line=br.readLine();
			

			while (line != null) 
			{
				line=br.readLine();
				if(line==null)
					break;

				line=this.deleteCom(line);
				if(!line.equals("null"))
				{
					//System.out.print(line);
					this.lineToArgs(line);
					//	code.writeArithmetic(line);
					//	code.WritePushPop(line, "5", 5);

				}


			}




		}

		catch(Exception e)
		{
			System.out.println("error");
		}

	}

	public boolean hasMoreCommands()
	{
		
		return true;

	}

	public void advance()
	{
		

	}

	public String commandType(String line)
	{
		if(line.contains("push"))
			return "C_PUSH";
		else if(line.contains("pop"))
			return "C_POP";
		else if(line.contains("add") || line.contains("sub") || line.contains("neg") || line.contains("eq") || line.contains("gt") ||line.contains("lt") || line.contains("and") || line.contains("or") || line.contains("not")) // just for now
			return "C_ARITHMETIC";
		else if(line.contains("label"))
			return "C_LABEL";
		else if(line.equals("goto"))
			return "C_GOTO";
		else if(line.equals("if-goto"))
			return "C_IF";
		else if(line.contains("function"))
			return "C_FUNCTION";
		else if(line.contains("return"))
			return "C_RETURN";
		else if(line.contains("call"))
			return "C_CALL";
		
		
		return "false";
	}

	public String arg1()
	{

		return "true";
	}

	public int arg2()
	{

		return 0;
	}	

	public String deleteCom(String line)// delete all the comments and all the space white 
	{	
		line=line.replaceAll("\\s+"," "); // remove all the whiteSpace
		parts =line.split("/");
		line=parts[0];		
		if(!line.isEmpty())
			return line;
		return "null";
	}

	public void lineToArgs(String line)
	{
		parts=line.split(" ");
		arg0=parts[0];
		//System.out.println(line);
		if(commandType(arg0).equals("C_ARITHMETIC"))
			code.writeArithmetic(arg0);
		
		else if(commandType(arg0).equals("C_PUSH") || commandType(arg0).equals("C_POP"))  //fromType (push/pop xxxx xxxx)
		{
		arg1=parts[1];
		arg2=parts[2];
		code.writePushPop(arg0, arg1,arg2); // command, segment, index   //Integer.parseInt(arg2)	
		}
		
		else if(commandType(arg0).equals("C_LABEL"))
		{
			//System.out.println("C_LABEL");
			arg1=parts[1];
			code.writeLabel(arg1);
		}
		
		else if(commandType(arg0).equals("C_IF"))
		{
			//System.out.println("C_IF");
			arg1=parts[1];
			code.WriteIf(arg1);
		}
		
		else if(commandType(arg0).equals("C_GOTO"))
		{
			arg1=parts[1];
			code.writeGoto(arg1);
		}
		
		else if(commandType(arg0).equals("C_FUNCTION"))
		{
			arg1=parts[1];
			arg2=parts[2];
			code.writeFunction(arg1,arg2);
		}
		
		else if(commandType(arg0).equals("C_RETURN"))
		{
			//System.out.println(arg0);
			code.writeReturn();
		}
			
		
	}

	public boolean ifArithmetic(String arg)
	{
		if(arg.equals("add"))
			return true;
		else if (arg.equals("sub"))
			return true;
		else if (arg.equals("neq"))
			return true;
		else if (arg.equals("eq"))
			return true;
		else if (arg.equals("gt"))
			return true;
		else if (arg.equals("lt"))
			return true;
		else if (arg.equals("and"))
			return true;
		else if (arg.equals("or"))
			return true;
		else if (arg.equals("not"))
			return true;
		return false;
	}
	
	public void lopEnd()
	{
		code.lopEnd();
	}


}
