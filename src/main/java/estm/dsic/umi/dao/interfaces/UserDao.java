package estm.dsic.umi.dao.interfaces;

import estm.dsic.umi.beans.User;

public interface UserDao {
    User create(User user);
    User getById(Integer id);
    User get(User user);
    User getByEmailAndPassword(String email, String password);
}
