package vetorlog.model.queries;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.jvnet.hk2.annotations.Service;
import vetorlog.model.UserModel;
import vetorlog.manager.DatabaseManager;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class UserQuery {
    @Inject
    private DatabaseManager dbManager;

    public UserModel findUserByEmail(String email) {
        TypedQuery<UserModel> query = dbManager.getEntityManager().createQuery(
                "SELECT m FROM UserModel AS m WHERE m.email LIKE :email", UserModel.class);
        query.setParameter("email", email);
        return dbManager.first(query);
    }

    public boolean isAdmin(UserModel user) {
        TypedQuery<UserModel> query = dbManager.getEntityManager().createQuery(
                "SELECT m FROM UserModel AS m WHERE m.id = :id AND m.roles.name LIKE \'admin\'", UserModel.class);
        query.setParameter("id", user);

        UserModel result = dbManager.first(query);
        return result != null;
    }
}