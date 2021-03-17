import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.io.*;

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

	public static void main(String args[]) throws RemoteException{
		try{
				Registry reg = LocateRegistry.createRegistry(9999);
				reg.rebind("preksha's server :",new Client());//server is method
				System.out.println("Client Listening...");
		}
		catch(RemoteException e)
		{
			System.out.println("exception : "+ e);
		}
	}
}