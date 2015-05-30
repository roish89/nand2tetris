import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;


public class VMWriter

{
	Writer writer;
	String[] parts;
	SymbolTable symbol;
	
	public VMWriter(String nameOfFile)
	{
		
		parts = nameOfFile.split("T.xml");
		nameOfFile=parts[0]+".vm";
		symbol= new SymbolTable();
		
		try
		{
			writer= new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nameOfFile), "utf-8"));
			
		}

		catch(Exception e)
		{
			System.err.println("error: constructor");
		}
		
		
	}
	
	
	public void writePush(String segment,int index )
	{
		wtf("push "+ segment+" "+index);
	}
	
	public void writePop(String segment,int index  )
	{
		wtf("pop "+ segment+" "+index);
	}
	
	public void WriteArithmetic(String command)
	{
		wtf(command);
	}
	public void WriteLabel(String label)
	{
		wtf(label);
	}
	public void WriteGoto(String label )
	{
		wtf(label);
	}
	public void WriteIf(String label)
	{
		wtf(label);
	}
	public void writeCall(String name ,int nArgs)
	{
		wtf("call "+name+" "+ nArgs);
	}
	public void writeFunction(String name,int nLocals)
	{
		wtf("function "+ name+ " "+ nLocals);
	}
	public void Constructs(String name,int nLocals)
	{
		int numField=symbol.varCount("field");
		wtf("function "+ name+ " "+ nLocals);
		
		
		
	}
	public void writeReturn()
	{
		wtf("return");
	}
	public void close(String label)
	{
		
	}
	
	//-------------------------------------------------------------------------
	
	
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
			System.err.println("error: writeToFileVM");
		}
	}
	
	
}


