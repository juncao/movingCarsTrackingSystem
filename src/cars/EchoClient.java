package cars;

import java.rmi.RemoteException;

public interface EchoClient extends java.rmi.Remote {

	public void setClientTitle (String title) throws RemoteException;

}
