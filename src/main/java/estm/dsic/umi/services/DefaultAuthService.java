package estm.dsic.umi.services;

import estm.dsic.umi.beans.User;
import estm.dsic.umi.dao.interfaces.UserDao;
import estm.dsic.umi.services.interfaces.AuthService;
import estm.dsic.umi.dao.UserDaoJDBC;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class DefaultAuthService extends UnicastRemoteObject implements  AuthService {
    private static DefaultAuthService instance = null;
    private final UserDaoJDBC userDao;

    public static DefaultAuthService getInstance() throws RemoteException {
        if(instance==null)
            instance=new DefaultAuthService(UserDaoJDBC.getInstance());
        return instance;
    }
    private DefaultAuthService(UserDaoJDBC userDao) throws RemoteException {
        this.userDao=userDao;
    }

    @Override
    public User authenticate(String email, String password) {
        return userDao.get(email,password);
    }

}
