package estm.dsic.umi.dao.interfaces;

import estm.dsic.umi.beans.User;

public interface UserDao extends CrudAble<User, Integer> {
    User get(User user);
    User get(String email, String password);
}
