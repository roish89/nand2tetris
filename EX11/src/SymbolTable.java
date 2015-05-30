import java.util.LinkedList;


public class SymbolTable 
{
	
	
	LinkedList<Node3> classList=new LinkedList<Node3>();
	LinkedList<Node3> methodList=new LinkedList<Node3>();
	
	int staticCount,fieldCounter,argCounter,varCounter;
	
	public SymbolTable()
	{
		methodList.clear();
		classList.clear();
		staticCount=0;
		fieldCounter=0;
		argCounter=0;
		varCounter=0;
	}
	
	public void	startSubroutine()
	{
		
	}	

	public void	define(String name, String type, String kind )
	{
		
		
		if(kind.equals("static"))
		{
			classList.add(new Node3(name,type,kind,staticCount));
			staticCount++;
		}
		else if(kind.equals("field"))
		{
			classList.add(new Node3(name,type,kind,fieldCounter));		
			fieldCounter++;
		}
		
		else if(kind.equals("var"))
		{
			methodList.add(new Node3(name,type,kind,varCounter));		
			varCounter++;
		}
		else if(kind.equals("argument"))
		{
			methodList.add(new Node3(name,type,kind,argCounter));		
			argCounter++;
		}
		
	}
	public boolean isfield(String name)
	{
		for (int i = 0; i < classList.size(); i++) 
		{
			if(classList.get(i).name.equals(name)&&classList.get(i).kind.equals("field"))
				return true;
		}
		
		return false;
	}
	public boolean isStatic(String name)
	{
		for (int i = 0; i < classList.size(); i++) 
		{
			if(classList.get(i).name.equals(name)&&classList.get(i).kind.equals("static"))
				return true;
		}
		
		return false;
	}
	
	public boolean isVar(String name)
	{
		for (int i = 0; i < methodList.size(); i++) 
		{
			if(methodList.get(i).name.equals(name)&&methodList.get(i).kind.equals("var"))
				return true;
		}
		
		return false;
	}
	
	public boolean isArg(String name)
	{
		for (int i = 0; i < methodList.size(); i++) 
		{
			if(methodList.get(i).name.equals(name)&&methodList.get(i).kind.equals("argument"))
				return true;
		}
		
		return false;
	}
	
	

	public int	varCount(String kind)
	{
		int varCount=0;
		
		if(kind.equals("static")||kind.equals("field"))
		{
			for (int i = 0; i < classList.size(); i++) 
			{
				if(classList.get(i).kind.equals(kind))
					varCount++;
			}
			
		}
		
		if(kind.equals("var")||kind.equals("argument"))
		{
			for (int i = 0; i < methodList.size(); i++) 
			{
				if(methodList.get(i).kind.equals(kind))
					varCount++;
			}
					
		}
		
		return varCount;
	}
	
	
	public String kindOf(String name)
	{
		for (int i = 0; i < methodList.size(); i++) 
		{
			if(methodList.get(i).name.equals(name))
				return methodList.get(i).kind;
		}
		
		for (int i = 0; i < classList.size(); i++) 
		{
			if(classList.get(i).name.equals(name))
				return classList.get(i).kind;
		}
		
		
		
		return "NONE";
	}
	public String typeOf(String name)
	{
		for (int i = 0; i < methodList.size(); i++) 
		{
			if(methodList.get(i).name.equals(name))
				return methodList.get(i).type;
		}
		for (int i = 0; i < classList.size(); i++) 
		{
			if(classList.get(i).name.equals(name))
				return classList.get(i).type;
		}
		
		
		
		return "NONE";
	}
	public int indexOf(String name)
	{
		for (int i = 0; i < methodList.size(); i++) 
		{
			if(methodList.get(i).name.equals(name))
				return methodList.get(i).count;
		}
		for (int i = 0; i < classList.size(); i++) 
		{
			if(classList.get(i).name.equals(name))
				return classList.get(i).count;
		}
		
		
		
		return -1;
	}
	
	

	public void printClassList()
	{
		for (int i = 0; i < classList.size(); i++) 
		{
			System.out.print("Name:"+ classList.get(i).name+" " );
			System.out.print("Type:" +classList.get(i).type+" ");
			System.out.print("Kind:"+classList.get(i).kind+" ");
			System.out.println("#:"+classList.get(i).count+" ");

		}
	}
	
	public void printMethodList()
	{
		for (int i = 0; i < methodList.size(); i++) 
		{
			System.out.print("Name:"+ methodList.get(i).name+" " );
			System.out.print("Type:" +methodList.get(i).type+" ");
			System.out.print("Kind:"+methodList.get(i).kind+" ");
			System.out.println("#:"+methodList.get(i).count+" ");

		}
	}

}
