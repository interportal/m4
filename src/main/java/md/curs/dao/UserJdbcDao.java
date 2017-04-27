package md.curs.dao;

import md.curs.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by MG
 */
@Repository
public class UserJdbcDao {

    private DataSource dataSource;

    @Autowired
    public UserJdbcDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int create(User user) {
        JdbcTemplate insert = new JdbcTemplate(dataSource);

        return insert.update("INSERT INTO users(name, surname, email, age) VALUES (?,?,?,?)",
                user.getName(), user.getSurname(), user.getEmail(), user.getAge());
    }

    public List<User> findUsers(String query) {
        Objects.requireNonNull(query, "query should not be null");
        query = "%" + query + "%";

        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("SELECT id, name, surname, email, age FROM users u WHERE u.name LIKE ? OR u.surname LIKE ?",
                new Object[]{query, query},
                new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new User(rs.getLong("id"),
                                rs.getString("name"),
                                rs.getString("surname"),
                                rs.getString("email"),
                                rs.getInt("age"));
                    }
                });
    }

    public Optional<User> getUser(long id) {
        JdbcTemplate select = new JdbcTemplate(dataSource);
        List<User> list = select.query("SELECT id, name, surname, email, age FROM users u WHERE u.id = ?",
                (rs, rowNum) -> new User(rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("email"),
                        rs.getInt("age")),
                id);
        if (list.size() > 0) {
            return Optional.of(list.get(0));
        } else {
            return Optional.empty();
        }
    }

    public int update(User user) {
        JdbcTemplate update = new JdbcTemplate(dataSource);

        return update.update("UPDATE users SET name = ?, surname = ?, email = ?, age = ? WHERE ID = ?",
                user.getName(), user.getSurname(), user.getEmail(), user.getAge(), user.getId());
    }

    public int delete(long userId) {
        JdbcTemplate delete = new JdbcTemplate(dataSource);

        return delete.update("DELETE FROM users WHERE ID = ?", userId);
    }

    public int minorsCount() {
        JdbcTemplate query = new JdbcTemplate(dataSource);

        return query.queryForList("SELECT COUNT(*) FROM users WHERE age >= 18", Integer.class).get(0);
    }
}
