import java.io.File;


public class Main {





	public static void main(String[] args)
	{
		/*
		CodeWriter code;
		Parser pars;
		File file =new File(args[0]);
		code= new CodeWriter(args[0]);
		
		// Reading directory contents
        File[] files = file.listFiles();
        
        
        for (int i = 0; i < files.length; i++)
        {
        	if(files[i].getName().contains(".vm"))
        	{
        		
        		String s=files[i].getPath();
        		pars= new Parser(s);
        		
        		
        	}
        	
        }
        //pars.lopEnd();
		
		*/
		
		Parser pars= new Parser(args[0]);
		pars.lopEnd();
	}
}
