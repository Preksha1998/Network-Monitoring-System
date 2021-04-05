import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class myframe2 extends JFrame
{
	void showmsg(String n)
	{
		JOptionPane.showMessageDialog(this,n,"Warning",JOptionPane.ERROR_MESSAGE);
	}		
}

public class Client extends UnicastRemoteObject implements Adder
{
	public Client() throws RemoteException{

	}

	@Override
	public String add(String n) throws RemoteException
	{
		String fn="tasklist /fi \"USERNAME eq " + n + "\"";
		String fnl="";
		
		try
		{
			ProcessBuilder builder = new ProcessBuilder("cmd.exe","/c",fn);
			builder.redirectErrorStream(true);
			Process p = builder.start();
			BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
			
			while(true)
			{
				String line;
				line=r.readLine();
				if(line==null) 
				{ 
					break; 
				}
				else
				{
					fnl+=line+"\n";
				}
			}
		}
		catch(IOException e)
		{
			
		}
		return fnl;
	}
	
	@Override
	public void shutdown() throws IOException
	{
		Runtime runtime = Runtime.getRuntime();
		Process proc = runtime.exec("shutdown -s -t 0");
	}
	
	@Override
	public void sendwarning(String n) throws IOException
	{
		System.out.println("Warning Received");
		myframe2 m1=new myframe2();
		m1.showmsg(n);
	}
	
	@Override
	public void killprocess(int pid) throws IOException
	{
		try {
            Runtime.getRuntime().exec("taskkill /PID " + pid);
            System.out.println(pid + " killed successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public static void main(String args[]) throws RemoteException{
		try{
				Registry reg = LocateRegistry.createRegistry(9999);
				reg.rebind("myserver",new Client());
				System.out.println("Client Listening...");
		}
		catch(RemoteException e)
		{
			System.out.println("exception : "+ e);
		}
	}
}