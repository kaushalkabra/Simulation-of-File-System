import java.util.*;


public class system {

	static String[] memory=new String[1000];
	static int loc=0;
	static inode root=new inode();
	
	public static void main(String args[])
	{
		root.created("root", "root");
		inode currentinode=root;
		
		Scanner sc=new Scanner(System.in);
		String line;

		System.out.println("INSTRUCTIONS:\n1.mkdir\t\t to make new directory\n2.create\t to create new file\n3.ls\t\t listing of file in current directory\n4.cd\t\t change directory\n5.mv\t\t to move file from current directory to other\n6.copy\t\t to copy file from current directory to other directory\n7.delete\t to delete file\n8.deldir\t to delete directory\n9.exit\t\t to close\n ");
		
		System.out.println(currentinode.fname+">>");
		line=sc.nextLine();
		String[] elements;
		
		while(!line.equalsIgnoreCase("exit"))
		{
			elements=line.split(" ");
			if(elements[0].equalsIgnoreCase("mkdir") && elements.length==2)
			{
				int flag=currentinode.search(elements[1]);
				if(flag==-1)
				{
					
				inode inode1=new inode();
				inode1.created(elements[1], currentinode.fname);
				currentinode.children.add(inode1);
				}
				else
				{
					System.out.println("Directory with same name exist so cannot create new one!!!");
				}
			}
			else if(elements[0].equalsIgnoreCase("create") && elements.length==2)
			{
				if(!elements[1].contains("."))
				{
					elements[1]=elements[1]+".file";
				}
				int flag=currentinode.search(elements[1]);
				if(flag==-1)
				{
					inode inode2=new inode();
					currentinode.children.add(inode2);
					System.out.println("Ënter content & end with -1");
					line=sc.nextLine();
					int s,d;
					s=loc;
					while(!line.equalsIgnoreCase("-1"))
					{
						memory[loc]=line;
						loc++;
						line=sc.nextLine();
					}
					d=loc;
					inode2.createf(elements[1], currentinode.fname, s, d);
				}
				else
				{
					System.out.println("File with same name exist so cannot create new one!!!");
				}
			}
			else if(elements[0].equalsIgnoreCase("ls"))
			{
				System.out.println(currentinode.fname+":");
				currentinode.showall();
			}
			else if(elements[0].equalsIgnoreCase("cat") && elements.length==2)
			{
				int s,d,i;
				if(!elements[1].contains("."))
				{
					elements[1]=elements[1]+".file";
				}
				int flag=currentinode.search(elements[1]);
				
				if(flag==-1)
				{
					System.out.println("file not found in");
				}
				else
				{
					s=currentinode.children.get(flag).si;
					d=currentinode.children.get(flag).di;
					
					for(i=s;i<d;i++)
					{
						System.out.println(memory[i]);
					}
				}
			}
			else if(elements[0].equalsIgnoreCase("delete") && elements.length==2)
			{
				if(!elements[1].contains("."))
				{
					elements[1]=elements[1]+".file";
				}
				int flag=currentinode.search(elements[1]);
				
				if(flag==-1)
				{
					System.out.println("file not found!!!");
				}
				else
				{
					currentinode.children.remove(flag);
					System.out.println("File is deleted successfully!!!");
				}
			}
			else if(elements[0].equalsIgnoreCase("deldir") && elements.length==2)
			{
				int flag=currentinode.search(elements[1]);
				
				if(flag==-1)
				{
					System.out.println("directory not found!!!");
				}
				else
				{
					currentinode.children.remove(flag);
					System.out.println("Directory is deleted successfully!!!");
				}
			}
			else if(elements[0].equalsIgnoreCase("cd") && elements.length==2)
			{
				if(elements[1].equalsIgnoreCase(".."))
				{
					currentinode=root;
				}
				else
				{
					int flag=currentinode.search(elements[1]);
					
					if(flag==-1)
					{
						System.out.println("directory not found!!!");
					}
					else
					{
						currentinode=currentinode.children.get(flag);
					}
				}
			}
			else if(elements[0].equalsIgnoreCase("mv") && elements.length==3)
			{
				if(!elements[1].contains("."))
				{
					elements[1]=elements[1]+".file";
				}
				int flag=currentinode.search(elements[1]);
				
				if(flag==-1)
				{
					System.out.println("file to be moved not found!!!");
				}
				
				move(currentinode.children.get(flag),elements[2]);
				currentinode.children.remove(flag);
			}
			else if(elements[0].equalsIgnoreCase("copy") && elements.length==3)
			{
				if(!elements[1].contains("."))
				{
					elements[1]=elements[1]+".file";
				}
				int flag=currentinode.search(elements[1]);
				
				if(flag==-1)
				{
					System.out.println("file to be moved not found!!!");
				}
				
				move(currentinode.children.get(flag),elements[2]);
			}
			else
			{
				System.out.println("please enter valid instruction with valid arguments!!!");
			}
			
			
			System.out.println(currentinode.fname+">>");
			line=sc.nextLine();
		}
	}
	
	public static void move(inode file,String path)
	{
		inode inode1 =root;
		int i,flag = 0;
		String[] element=path.split("/");
		for(i=0;i<(element.length-1);i++)
		{
			String str=element[i];
			flag=inode1.search(element[i+1]);
			if(flag==-1)
			{
				System.out.println("Directory "+element[i+1]+" not found!!!");
				break;
			}
			else
			{
				inode1=inode1.children.get(flag);
			}
		}
		
		if(flag!=-1)
		{
			inode1.children.add(file);
		}
	}
}
