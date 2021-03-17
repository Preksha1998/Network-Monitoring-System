import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Server{
	public static void main(String args[]) throws RemoteException{
		Server s = new Server();
		s.connectRemote();
	}	

	private void connectRemote() throws RemoteException{
		try{
			Scanner sc = new Scanner(System.in);
			Registry reg = LocateRegistry.getRegistry("192.168.1.8",9999);//ip inplace of localhost
			Adder ad = (Adder)reg.lookup("preksha's server :");
			System.out.println("Enter Username To Monitor: ");	
			String s=sc.nextLine();

			System.out.println("\n\nListing Processes:-\n\n" + ad.add(s));
		}
		catch(NotBoundException | RemoteException e)
		{
			System.out.println("Exception : "+ e);
		}
	}
}