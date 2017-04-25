package md.curs.service;

import md.curs.dao.UserJdbcDao;
import md.curs.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * User service example
 *
 * Created by MG
 */
@Service
@Transactional(readOnly = true)
public class UserService {

    private UserJdbcDao userDao;

    @Autowired
    public UserService(UserJdbcDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Get an User by it's ID
     *
     * @param id the id of the user
     * @return an {@link Optional} User (present only if the user with specified id exists)
     */
    public Optional<User> getUser(long id) {
        return userDao.getUser(id);
    }

    /**
     * Find all users which contain in their name or surname the specified query
     *
     * @param query query to find by
     * @return the filtered user list
     */
    public List<User> findUsers(String query) {
        if (query == null) query = "";
        return userDao.findUsers(query);
    }

    /**
     * Get the count of users which have the age below 18
     *
     * @return minors count
     */
    public long getMinorsCount() {
        return userDao.minorsCount();
    }

    /**
     * Add a new user or update an existing one depending on the provided user.id
     *
     * @param user The user to save
     */
    @Transactional
    public void saveUser(User user) {
        if (user.getId() == null) {
            userDao.create(user);
        } else {
            userDao.update(user);
        }
    }

    /**
     * Remove an User
     *
     * @param u user to remove
     */
    @Transactional
    public void deleteUser(User u) {
        userDao.delete(u.getId());
    }
}
