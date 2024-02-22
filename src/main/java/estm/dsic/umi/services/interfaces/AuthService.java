package estm.dsic.umi.services.interfaces;

import estm.dsic.umi.beans.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AuthService extends Remote {
    User authenticate(String email, String password) throws RemoteException;
}
