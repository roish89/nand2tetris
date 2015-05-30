
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JackAnalyzer {



	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException 
	{



		File folder = new File(args[0]);

		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) 
		{


			if (file.isFile() && file.getName().contains("T.xml") ) 
			{
				//System.out.println(file.getName());
				String arg=file.getPath();



				String line;
				CompilationEngine engine= new CompilationEngine(arg);

				try(BufferedReader br = new BufferedReader(new FileReader(arg))) 
				{

					line=br.readLine();
					//line=deleteCom(line);
					
					while (line != null) 
					{
						//line=deleteCom(line);
						if(!line.equals("null"))
						{
							//System.out.println(line);
							engine.toList(line);

						}

						line=br.readLine();
						if(line==null)
							break;

					}



					br.close();

				}

				catch(Exception e)
				{
					System.out.println("error");
				}



				engine.CompileClass();
				//engine.printList();
			}
		}
	}

	public static String deleteCom(String line)// delete all the comments and all the space white 
	{	
		//st.replaceAll("\\s+","") and st.replaceAll("\\s","")
		String[] parts;
		//line=line.replaceAll("\\s+"," "); // remove all the whiteSpace
		//line=line.replaceAll("\\s","");
		parts =line.split("//");
		line=parts[0];	

		if(!line.isEmpty())
			return line;
		return "null";
	}

}
//------------------------------------------------------------------------------------

