import java.util.*;


public class inode {

	int si,di;
	String fname,parent;
	ArrayList<inode> children;
	
	public inode()
	{
		children =new ArrayList<inode>();
	}
	
	public void created(String name,String par)
	{
		fname=name;
		parent=par;
	}
	
	public void createf(String name,String par,int s,int d)
	{
		fname=name;
		parent=par;
		si=s;
		di=d;
	}
	
	public void showall()
	{
		for(inode child : children)
		{
			System.out.println("\t"+child.fname);
		}
	}
	
	public int search(String name)
	{
		int flag=-1;
		for(inode child : children)
		{
			if(name.equalsIgnoreCase(child.fname))
			{
				 flag=children.indexOf(child);
				 break;
			}
		}
		return flag;
	}
	
	
}
