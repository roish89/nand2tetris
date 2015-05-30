//import java.io.BufferedReader;
//import java.io.FileReader;



public class Main {

	public static void main(String[] args)
	{
		System.out.println(args[0]);
		Parser pars= new Parser(args[0]);
		pars.lopEnd();
		
	}
}
