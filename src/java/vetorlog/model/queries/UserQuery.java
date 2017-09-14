package vetorlog.model.queries;

import lombok.AllArgsConstructor;
import org.jvnet.hk2.annotations.Service;
import vetorlog.model.UserModel;
import vetorlog.manager.DatabaseManager;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

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
}