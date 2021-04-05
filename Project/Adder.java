import java.rmi.*;
import java.io.*;

public interface Adder extends Remote{
	public String add(String n) throws RemoteException;	
	public void shutdown() throws IOException;
	public void sendwarning(String s) throws IOException;	
	public void killprocess(int pid) throws IOException;	
}