import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;


public class CodeWriter {

	Parser pars;
	String[] parts;
	Writer writer;

	//-----------------------------------------------
	int num=0;




	public CodeWriter(String nameFile) 
	{

		parts = nameFile.split("vm");
		nameFile=parts[0]+"asm";

		try
		{
			writer= new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nameFile), "utf-8"));
		

		}

		catch(Exception e)
		{
			System.out.println("error");
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
			num++;
			String numOfL=Integer.toString(num);
			
			this.writeToFile("@SP\n");
			this.writeToFile("M=M-1\n");
			this.writeToFile("A=M\n");
			this.writeToFile("D=M\n");
			this.writeToFile("@SP\n");
			this.writeToFile("M=M-1\n");
			this.writeToFile("A=M\n");
			this.writeToFile("D=M-D\n");
			this.writeToFile("@NEQ."+ numOfL +"\n");
			this.writeToFile("D;JNE\n");
			this.writeToFile("@SP\n");
			this.writeToFile("A=M\n");
			this.writeToFile("M=-1\n");
			this.writeToFile("@eqEND."+ numOfL +"\n");
			this.writeToFile("0;JEQ\n");
			this.writeToFile("(NEQ."+ numOfL +")\n");
			this.writeToFile("@SP\n");
			this.writeToFile("A=M\n");
			this.writeToFile("M=0\n");
			this.writeToFile("(eqEND."+ numOfL +")\n");
			this.writeToFile("@SP\n");
			this.writeToFile("M=M+1\n");
			
			
		}
		
		else if(arg.equals("gt"))
		{
			num++;
			String numOfL=Integer.toString(num);
			
			this.writeToFile("@SP\n");
			this.writeToFile("M=M-1\n");
			this.writeToFile("A=M\n");
			this.writeToFile("D=M\n");
			this.writeToFile("@SP\n");
			this.writeToFile("M=M-1\n");
			this.writeToFile("A=M\n");
			this.writeToFile("D=M-D\n");
			this.writeToFile("@LT."+ numOfL +"\n");
			this.writeToFile("D;JLT\n");
			this.writeToFile("@SP\n");
			this.writeToFile("A=M\n");
			this.writeToFile("M=-1\n");
			this.writeToFile("@gtEND."+ numOfL +"\n");
			this.writeToFile("0;JEQ\n");
			this.writeToFile("(LT."+ numOfL +")\n");
			this.writeToFile("@SP\n");
			this.writeToFile("A=M\n");
			this.writeToFile("M=0\n");
			this.writeToFile("(gtEND."+ numOfL +")\n");
			this.writeToFile("@SP\n");
			this.writeToFile("M=M+1\n");
		}
		
		else if(arg.equals("lt"))
		{
			num++;
			String numOfL=Integer.toString(num);
			
			this.writeToFile("@SP\n");
			this.writeToFile("M=M-1\n");
			this.writeToFile("A=M\n");
			this.writeToFile("D=M\n");
			this.writeToFile("@SP\n");
			this.writeToFile("M=M-1\n");
			this.writeToFile("A=M\n");
			this.writeToFile("D=M-D\n");
			this.writeToFile("@GT."+ numOfL +"\n");
			this.writeToFile("D;JGT\n");
			this.writeToFile("@SP\n");
			this.writeToFile("A=M\n");
			this.writeToFile("M=-1\n");
			this.writeToFile("@ltEND."+ numOfL +"\n");
			this.writeToFile("0;JEQ\n");
			this.writeToFile("(GT."+ numOfL +")\n");
			this.writeToFile("@SP\n");
			this.writeToFile("A=M\n");
			this.writeToFile("M=0\n");
			this.writeToFile("(ltEND."+ numOfL +")\n");
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
		
		else if(arg.equals("mult2"))
		{
			this.writeToFile("@SP\n");
			this.writeToFile("M=M-1\n");
			this.writeToFile("A=M\n");
			this.writeToFile("D=M\n");
			this.writeToFile("D=M+D\n");
			this.writeToFile("@SP\n");
			this.writeToFile("A=M\n");
			this.writeToFile("M=D\n");
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
				if(Integer.parseInt(index)>=8)
					System.err.println("the temp great than 8 ");
					
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
			else if(segment.equals("nothing"))
			{
				this.writeToFile("@"+index+"\n");
				this.writeToFile("D=A\n");
				this.writeToFile("@SP\n");
				this.writeToFile("M=M+D\n");			
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
				if(Integer.parseInt(index)>=8)
					System.err.println("the temp great than 8 ");
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
			
			else if(segment.equals("nothing"))
			{
				this.writeToFile("@"+index+"\n");
				this.writeToFile("D=A\n");
				this.writeToFile("@SP\n");
				this.writeToFile("AM=M-D\n");
				this.writeToFile("D=M\n");
			}

		}
		
		
	}
	
	public void lopEnd() 
	{
		this.writeToFile("(loopEnd)\n");
		this.writeToFile("@loopEnd\n");
		this.writeToFile("0;JEQ\n");
	}

}
