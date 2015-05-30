import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;


public class CodeWriter {

	Parser pars;
	String[] parts;
	Writer writer;
	String numLocal;

	//-----------------------------------------------
	int SP=256;

	public CodeWriter(String nameFolder) 
	{

		parts = nameFolder.split("vm");
		nameFolder=parts[0]+"asm";
		//System.out.println(nameFolder);
		try
		{
			writer= new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nameFolder), "utf-8"));
			
		}

		catch(Exception e)
		{
			System.out.println("error:try to writer");
		}


	}
	
	

	public void setFileName(String fileName)
	{


	}

	public void writeToFile(String Command)
	{
		try 
		{
			
			writer.write(Command);
			writer.flush();
		} 

		catch (IOException e) 
		{			
			System.out.println("error: to writeToFile");
		}
	}

	//---------------------------------------------------------code of ASM-----------------------------------------



	public void writeArithmetic(String arg)
	{
		if(arg.equals("add"))
		{
			this.writeToFile("@SP\n");
			this.writeToFile("M=M-1\n");
			this.writeToFile("A=M\n");
			this.writeToFile("D=M\n");
			this.writeToFile("@SP\n");
			this.writeToFile("M=M-1\n");
			this.writeToFile("A=M\n");
			this.writeToFile("M=M+D\n");
			this.writeToFile("@SP\n");
			this.writeToFile("M=M+1\n");
			
		}
		else if(arg.equals("sub"))
		{
			this.writeToFile("@SP\n");
			this.writeToFile("M=M-1\n");
			this.writeToFile("A=M\n");
			this.writeToFile("D=M\n");
			this.writeToFile("@SP\n");
			this.writeToFile("M=M-1\n");
			this.writeToFile("A=M\n");
			this.writeToFile("M=M-D\n");
			this.writeToFile("@SP\n");
			this.writeToFile("M=M+1\n");
		}
		else if(arg.equals("neg"))
		{
			this.writeToFile("@SP\n");
			this.writeToFile("M=M-1\n");
			this.writeToFile("A=M\n");
			this.writeToFile("M=-M\n");
			this.writeToFile("@SP\n");
			this.writeToFile("M=M+1\n");
		}
		
		else if(arg.equals("eq"))
		{
			this.writeToFile("@SP\n");
			this.writeToFile("M=M-1\n");
			this.writeToFile("A=M\n");
			this.writeToFile("D=M\n");
			this.writeToFile("@SP\n");
			this.writeToFile("M=M-1\n");
			this.writeToFile("A=M\n");
			this.writeToFile("D=M-D\n");
			this.writeToFile("@NEQ\n");
			this.writeToFile("D;JNE\n");
			this.writeToFile("@SP\n");
			this.writeToFile("A=M\n");
			this.writeToFile("M=-1\n");
			this.writeToFile("@eqEND\n");
			this.writeToFile("0;JEQ\n");
			this.writeToFile("(NEQ)\n");
			this.writeToFile("@SP\n");
			this.writeToFile("A=M\n");
			this.writeToFile("M=0\n");
			this.writeToFile("(eqEND)\n");
			this.writeToFile("@SP\n");
			this.writeToFile("M=M+1\n");
			
			
		}
		
		else if(arg.equals("gt"))
		{
			this.writeToFile("@SP\n");
			this.writeToFile("M=M-1\n");
			this.writeToFile("A=M\n");
			this.writeToFile("D=M\n");
			this.writeToFile("@SP\n");
			this.writeToFile("M=M-1\n");
			this.writeToFile("A=M\n");
			this.writeToFile("D=M-D\n");
			this.writeToFile("@LT\n");
			this.writeToFile("D;JLT\n");
			this.writeToFile("@SP\n");
			this.writeToFile("A=M\n");
			this.writeToFile("M=-1\n");
			this.writeToFile("@gtEND\n");
			this.writeToFile("0;JEQ\n");
			this.writeToFile("(LT)\n");
			this.writeToFile("@SP\n");
			this.writeToFile("A=M\n");
			this.writeToFile("M=0\n");
			this.writeToFile("(gtEND)\n");
			this.writeToFile("@SP\n");
			this.writeToFile("M=M+1\n");
		}
		
		else if(arg.equals("lt"))
		{
			this.writeToFile("@SP\n");
			this.writeToFile("M=M-1\n");
			this.writeToFile("A=M\n");
			this.writeToFile("D=M\n");
			this.writeToFile("@SP\n");
			this.writeToFile("M=M-1\n");
			this.writeToFile("A=M\n");
			this.writeToFile("D=M-D\n");
			this.writeToFile("@GT\n");
			this.writeToFile("D;JGT\n");
			this.writeToFile("@SP\n");
			this.writeToFile("A=M\n");
			this.writeToFile("M=-1\n");
			this.writeToFile("@ltEND\n");
			this.writeToFile("0;JEQ\n");
			this.writeToFile("(GT)\n");
			this.writeToFile("@SP\n");
			this.writeToFile("A=M\n");
			this.writeToFile("M=0\n");
			this.writeToFile("(ltEND)\n");
			this.writeToFile("@SP\n");
			this.writeToFile("M=M+1\n");
			
		}
		
		else if(arg.equals("and"))
		{
			this.writeToFile("@SP\n");
			this.writeToFile("M=M-1\n");
			this.writeToFile("A=M\n");
			this.writeToFile("D=M\n");
			this.writeToFile("@SP\n");
			this.writeToFile("M=M-1\n");
			this.writeToFile("A=M\n");
			this.writeToFile("D=M&D\n");
			this.writeToFile("@SP\n");
			this.writeToFile("A=M\n");
			this.writeToFile("M=D\n");
			this.writeToFile("@SP\n");
			this.writeToFile("M=M+1\n");
			
		}
		
		else if(arg.equals("or"))
		{
			this.writeToFile("@SP\n");
			this.writeToFile("M=M-1\n");
			this.writeToFile("A=M\n");
			this.writeToFile("D=M\n");
			this.writeToFile("@SP\n");
			this.writeToFile("M=M-1\n");
			this.writeToFile("A=M\n");
			this.writeToFile("D=M|D\n");
			this.writeToFile("@SP\n");
			this.writeToFile("A=M\n");
			this.writeToFile("M=D\n");
			this.writeToFile("@SP\n");
			this.writeToFile("M=M+1\n");
		}
		
		else if(arg.equals("not"))
		{
			this.writeToFile("@SP\n");
			this.writeToFile("M=M-1\n");
			this.writeToFile("A=M\n");
			this.writeToFile("M=!M\n");
			this.writeToFile("@SP\n");
			this.writeToFile("M=M+1\n");
		}
		
		
		
	}

	public void writePushPop(String command,String segment, String index) //WritePushPop(String command,String segment,int index)
	{   // command=push\pop , segment= constant,argument,local ...  , index=local 0,local 1, argument 0 ....

		if(command.equals("push"))
		{
			if(segment.equals("constant"))	 	//push constant x
			{
				this.writeToFile("@" + index + "\n");
				this.writeToFile("D=A\n");
				this.writeToFile("@SP\n");
				this.writeToFile("A=M\n");
				this.writeToFile("M=D\n");
				this.writeToFile("@SP\n");
				this.writeToFile("M=M+1\n");				
			}

			else if(segment.equals("local"))	//push local x	
			{
				this.writeToFile("@" + index + "\n");
				this.writeToFile("D=A\n");
				this.writeToFile("@LCL\n");
				this.writeToFile("A=M+D\n");
				this.writeToFile("D=M\n");
				this.writeToFile("@SP\n");
				this.writeToFile("A=M\n");
				this.writeToFile("M=D\n");
				this.writeToFile("@SP\n");
				this.writeToFile("M=M+1\n");


			}

			else if(segment.equals("argument"))	//push argument x
			{
				this.writeToFile("@" + index + "\n");
				this.writeToFile("D=A\n");
				this.writeToFile("@ARG\n");
				this.writeToFile("A=M+D\n");
				this.writeToFile("D=M\n");
				this.writeToFile("@SP\n");
				this.writeToFile("A=M\n");
				this.writeToFile("M=D\n");
				this.writeToFile("@SP\n");
				this.writeToFile("M=M+1\n");
			}

			else if(segment.equals("that"))		//push that x
			{
				this.writeToFile("@" + index + "\n");
				this.writeToFile("D=A\n");
				this.writeToFile("@THAT\n");
				this.writeToFile("A=M+D\n");
				this.writeToFile("D=M\n");
				this.writeToFile("@SP\n");
				this.writeToFile("A=M\n");
				this.writeToFile("M=D\n");
				this.writeToFile("@SP\n");
				this.writeToFile("M=M+1\n");
			}

			else if(segment.equals("this"))		//push this x
			{
				this.writeToFile("@" + index + "\n");
				this.writeToFile("D=A\n");
				this.writeToFile("@THIS\n");
				this.writeToFile("A=M+D\n");
				this.writeToFile("D=M\n");
				this.writeToFile("@SP\n");
				this.writeToFile("A=M\n");
				this.writeToFile("M=D\n");
				this.writeToFile("@SP\n");
				this.writeToFile("M=M+1\n");
			}
			
			else if(segment.equals("temp"))		//push temp x
			{
				this.writeToFile("@" + index + "\n");
				this.writeToFile("D=A\n");
				this.writeToFile("@5\n");	//temp0
				this.writeToFile("D=A+D\n");
				this.writeToFile("A=D\n");
				this.writeToFile("D=M\n");
				this.writeToFile("@SP\n");
				this.writeToFile("A=M\n");
				this.writeToFile("M=D\n");
				this.writeToFile("@SP\n");
				this.writeToFile("M=M+1\n");							
			}
			
			else if(segment.equals("pointer"))		//push pointer x
			{
				this.writeToFile("@" + index + "\n");
				this.writeToFile("D=A\n");
				this.writeToFile("@3\n");		//+3
				this.writeToFile("D=A+D\n");	//index +3
				this.writeToFile("A=D\n");
				this.writeToFile("D=M\n");
				this.writeToFile("@SP\n");
				this.writeToFile("A=M\n");
				this.writeToFile("M=D\n");
				this.writeToFile("@SP\n");
				this.writeToFile("M=M+1\n");				
			}
			
			else if(segment.equals("static"))		//push static x
			{
				this.writeToFile("@" + index + "\n");
				this.writeToFile("D=A\n");
				this.writeToFile("@16\n");
				this.writeToFile("D=A+D\n");
				this.writeToFile("A=D\n");
				this.writeToFile("D=M\n");
				this.writeToFile("@SP\n");
				this.writeToFile("A=M\n");
				this.writeToFile("M=D\n");
				this.writeToFile("@SP\n");
				this.writeToFile("M=M+1\n");
				
				
				
				
				
			}


		}
		if(command.equals("pop"))				
		{


			if(segment.equals("local"))	//pop local x	
			{
				this.writeToFile("@SP\n");
				this.writeToFile("M=M-1\n");
				this.writeToFile("A=M\n");
				this.writeToFile("D=M\n");
				this.writeToFile("@R15\n");
				this.writeToFile("M=D\n");
				this.writeToFile("@"+ index+ "\n");
				this.writeToFile("D=A\n");
				this.writeToFile("@LCL\n");
				this.writeToFile("D=M+D\n");
				this.writeToFile("@R14\n");
				this.writeToFile("M=D\n");
				this.writeToFile("@R15\n");
				this.writeToFile("D=M\n");
				this.writeToFile("@R14\n");
				this.writeToFile("A=M\n");
				this.writeToFile("M=D\n");
				
				


			}

			else if(segment.equals("argument"))	//pop argument x
			{
				this.writeToFile("@SP\n");
				this.writeToFile("M=M-1\n");
				this.writeToFile("A=M\n");
				this.writeToFile("D=M\n");
				this.writeToFile("@R15\n");
				this.writeToFile("M=D\n");
				this.writeToFile("@"+ index+ "\n");
				this.writeToFile("D=A\n");
				this.writeToFile("@ARG\n");
				this.writeToFile("D=M+D\n");
				this.writeToFile("@R14\n");
				this.writeToFile("M=D\n");
				this.writeToFile("@R15\n");
				this.writeToFile("D=M\n");
				this.writeToFile("@R14\n");
				this.writeToFile("A=M\n");
				this.writeToFile("M=D\n");
			}

			else if(segment.equals("that"))		//pop that x
			{
				this.writeToFile("@SP\n");
				this.writeToFile("M=M-1\n");
				this.writeToFile("A=M\n");
				this.writeToFile("D=M\n");
				this.writeToFile("@R15\n");
				this.writeToFile("M=D\n");
				this.writeToFile("@"+ index+ "\n");
				this.writeToFile("D=A\n");
				this.writeToFile("@THAT\n");
				this.writeToFile("D=M+D\n");
				this.writeToFile("@R14\n");
				this.writeToFile("M=D\n");
				this.writeToFile("@R15\n");
				this.writeToFile("D=M\n");
				this.writeToFile("@R14\n");
				this.writeToFile("A=M\n");
				this.writeToFile("M=D\n");
			}

			else if(segment.equals("this"))		//pop this x
			{
				this.writeToFile("@SP\n");
				this.writeToFile("M=M-1\n");
				this.writeToFile("A=M\n");
				this.writeToFile("D=M\n");
				this.writeToFile("@R15\n");
				this.writeToFile("M=D\n");
				this.writeToFile("@"+ index+ "\n");
				this.writeToFile("D=A\n");
				this.writeToFile("@THIS\n");
				this.writeToFile("D=M+D\n");
				this.writeToFile("@R14\n");
				this.writeToFile("M=D\n");
				this.writeToFile("@R15\n");
				this.writeToFile("D=M\n");
				this.writeToFile("@R14\n");
				this.writeToFile("A=M\n");
				this.writeToFile("M=D\n");
			}
			else if(segment.equals("temp"))		//pop temp x
			{
				this.writeToFile("@SP\n");
				this.writeToFile("M=M-1\n");
				this.writeToFile("A=M\n");
				this.writeToFile("D=M\n");
				this.writeToFile("@R15\n");
				this.writeToFile("M=D\n");
				this.writeToFile("@"+ index+ "\n");
				this.writeToFile("D=A\n");
				this.writeToFile("@5\n");		//+5
				this.writeToFile("D=A+D\n");	//index+5
				this.writeToFile("@R14\n");
				this.writeToFile("M=D\n");
				this.writeToFile("@R15\n");
				this.writeToFile("D=M\n");
				this.writeToFile("@R14\n");
				this.writeToFile("A=M\n");
				this.writeToFile("M=D\n");
							
			}
			
			else if(segment.equals("pointer"))		//pop pointer x
			{
				this.writeToFile("@SP\n");
				this.writeToFile("M=M-1\n");
				this.writeToFile("A=M\n");
				this.writeToFile("D=M\n");
				this.writeToFile("@R15\n");
				this.writeToFile("M=D\n");
				this.writeToFile("@"+ index+ "\n");
				this.writeToFile("D=A\n");
				this.writeToFile("@3\n");		//+3
				this.writeToFile("D=A+D\n");	//index+3
				this.writeToFile("@R14\n");
				this.writeToFile("M=D\n");
				this.writeToFile("@R15\n");
				this.writeToFile("D=M\n");
				this.writeToFile("@R14\n");
				this.writeToFile("A=M\n");
				this.writeToFile("M=D\n");
							
			}
			else if(segment.equals("static"))		//pop static x
			{
				this.writeToFile("@SP\n");
				this.writeToFile("M=M-1\n");
				this.writeToFile("A=M\n");
				this.writeToFile("D=M\n");
				this.writeToFile("@R15\n");
				this.writeToFile("M=D\n");
				this.writeToFile("@"+ index+ "\n");
				this.writeToFile("D=A\n");
				this.writeToFile("@16\n");		//+3
				this.writeToFile("D=A+D\n");	//index+3
				this.writeToFile("@R14\n");
				this.writeToFile("M=D\n");
				this.writeToFile("@R15\n");
				this.writeToFile("D=M\n");
				this.writeToFile("@R14\n");
				this.writeToFile("A=M\n");
				this.writeToFile("M=D\n");
							
			}

		}
		
	}
	
	public void writInit()
	{
		this.writeToFile("@256"+"\n");
		this.writeToFile("D=A"+"\n");
		this.writeToFile("@SP"+"\n");
		this.writeToFile("M=D"+"\n");
		
		//address
		this.writeToFile("@SP"+"\n");
		this.writeToFile("A=M"+"\n");
		this.writeToFile("M=0"+"\n");
		this.writeToFile("@SP"+"\n");
		this.writeToFile("M=M+1"+"\n");
		
		//LCL
		this.writeToFile("@SP"+"\n");
		this.writeToFile("A=M"+"\n");
		this.writeToFile("M=0"+"\n");
		this.writeToFile("@SP"+"\n");
		this.writeToFile("M=M+1"+"\n");
		
		//ARG
		this.writeToFile("@SP"+"\n");
		this.writeToFile("A=M"+"\n");
		this.writeToFile("M=0"+"\n");
		this.writeToFile("@SP"+"\n");
		this.writeToFile("M=M+1"+"\n");
		
		//THIS
		this.writeToFile("@SP"+"\n");
		this.writeToFile("A=M"+"\n");
		this.writeToFile("M=0"+"\n");
		this.writeToFile("@SP"+"\n");
		this.writeToFile("M=M+1"+"\n");
		
		//THAT
		this.writeToFile("@SP"+"\n");
		this.writeToFile("A=M"+"\n");
		this.writeToFile("M=0"+"\n");
		this.writeToFile("@SP"+"\n");
		this.writeToFile("M=M+1"+"\n");
		
		
	}
	
	public void writeLabel(String label)
	{
		this.writeToFile("("+label+")" + "\n");
	}
	
	public void writeGoto(String label)
	{
		this.writeToFile("@" +label + "\n");
		this.writeToFile("0;JEQ\n");
		
	}
	
	public void WriteIf(String label)
	{
		this.writeToFile("@SP\n");
		this.writeToFile("M=M-1\n");
		this.writeToFile("A=M\n");
		this.writeToFile("D=M\n");
		this.writeToFile("@" +label +"\n");
		this.writeToFile("D;JNE\n");			//i don't  
	}
	
	
	public void writeCall(String functionName,int numArgs)
	{
		//this.writeToFile("@"+\n");
		this.writeToFile("\n");
		this.writeToFile("\n");
		this.writeToFile("\n");
		
		
	}
	
	public void writeReturn()
	{
		//SAVE LCL IN R13
		this.writeToFile("@LCL\n");
		this.writeToFile("D=M\n");
		this.writeToFile("@R13\n");
		this.writeToFile("M=D\n");
		this.writeToFile("@LCL\n");
		this.writeToFile("D=M\n");
		this.writeToFile("@R13\n");
		this.writeToFile("M=D\n");
		
		//SAVE RETURN_ADDRESS IN R14
		this.writeToFile("@5\n");
		this.writeToFile("D=A\n");
		this.writeToFile("@LCL\n");
		this.writeToFile("A=M-D\n");
		this.writeToFile("D=M\n");
		this.writeToFile("@R14\n");
		this.writeToFile("M=D\n");
		
		//SAVE ARG IN R15
		this.writeToFile("@ARG\n");
		this.writeToFile("D=M\n");
		this.writeToFile("@15\n");
		this.writeToFile("M=D\n");
		
		//Repair THAT
		this.writeToFile("@LCL\n");
		this.writeToFile("M=M-1\n");
		this.writeToFile("A=M\n");
		this.writeToFile("D=M\n");
		this.writeToFile("@THAT\n");
		this.writeToFile("M=D\n");
		
		//Repair THIS
		this.writeToFile("@LCL\n");
		this.writeToFile("M=M-1\n");
		this.writeToFile("A=M\n");
		this.writeToFile("D=M\n");
		this.writeToFile("@THIS\n");
		this.writeToFile("M=D\n");
		
		//Repair ARG
		this.writeToFile("@LCL\n");
		this.writeToFile("M=M-1\n");
		this.writeToFile("A=M\n");
		this.writeToFile("D=M\n");
		this.writeToFile("@ARG\n");
		this.writeToFile("M=D\n");
		
		//Repair LCL
		this.writeToFile("@LCL\n");
		this.writeToFile("M=M-1\n");
		this.writeToFile("A=M\n");
		this.writeToFile("D=M\n");
		this.writeToFile("@LCL\n");
		this.writeToFile("M=D\n");
		
		//Repair the val of return
		this.writeToFile("@"+numLocal+"\n");
		this.writeToFile("D=A\n");
		this.writeToFile("@R13\n");
		this.writeToFile("D=M+D\n");
		this.writeToFile("A=D\n");
		this.writeToFile("D=M\n");
		this.writeToFile("@15\n");
		this.writeToFile("A=M\n");
		this.writeToFile("M=D\n");
		
		//Repair the SP
		this.writeToFile("@15\n");
		this.writeToFile("M=M+1\n");
		this.writeToFile("D=M\n");
		this.writeToFile("@SP\n");
		this.writeToFile("M=D\n");
		
		
	}
	
	public void writeFunction(String functionName, String numLocals)
	{
		this.numLocal=numLocals;
		
		this.writeToFile("@" + numLocals + "\n");
		this.writeToFile("D=A\n");
		this.writeToFile("(INITLIZE)\n");
		this.writeToFile("@SP\n");
		this.writeToFile("A=M\n");
		this.writeToFile("M=0\n");
		this.writeToFile("@SP\n");
		this.writeToFile("M=M+1\n");
		this.writeToFile("D=D-1\n");
		this.writeToFile("@INITLIZE\n");
		this.writeToFile("D;JGT\n");
		
	/*
	 this.writeToFile("@" + numLocals + "\n");
		this.writeToFile("(INITLIZE)\n");
		this.writeToFile("@SP\n");
		this.writeToFile("A=M\n");
		this.writeToFile("M=0\n");
		this.writeToFile("@SP\n");
		this.writeToFile("M=M+1\n");
		this.writeToFile("D=D-1\n");
		this.writeToFile("@INITLIZE\n");
		this.writeToFile("D;JGT\n"); 
	 
	 	this.writeToFile("@" + numLocals + "\n");
		this.writeToFile("D=A\n");
		this.writeToFile("@SP\n");
		this.writeToFile("M=M+D\n");
	 */
	}
	
	public void lopEnd() 
	{
		this.writeToFile("(loopEnd)\n");
		this.writeToFile("@loopEnd\n");
		this.writeToFile("0;JEQ\n");
	}

}
