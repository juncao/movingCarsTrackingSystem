package cars;

import java.rmi.RemoteException;

public interface EchoServer extends java.rmi.Remote{

	public int registerBallClient (EchoClient client) throws RemoteException;

}
