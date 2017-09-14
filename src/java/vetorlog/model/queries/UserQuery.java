package vetorlog.model.queries;

import lombok.NoArgsConstructor;
import org.jvnet.hk2.annotations.Service;
import vetorlog.manager.DatabaseManager;
import vetorlog.model.UserModel;
import vetorlog.model.util.relational.IEntityManagerWrapper;

import javax.persistence.TypedQuery;

@NoArgsConstructor
@Service
public class UserQuery extends DatabaseManager {
    // É necessário quando não há ecossistema de DI (ex: JUnit)
    public UserQuery(IEntityManagerWrapper emw) {
        this.emw = emw;
    }

    public UserModel findUserByEmail(String email) {
        TypedQuery<UserModel> query = getEntityManager().createQuery(
                "SELECT m FROM UserModel AS m WHERE m.email LIKE :email", UserModel.class);
        query.setParameter("email", email);
        return first(query);
    }

    public boolean isAdmin(UserModel user) {
        TypedQuery<UserModel> query = getEntityManager().createQuery(
                "SELECT m FROM UserModel AS m WHERE m.id = :id AND m.roles.name LIKE \'admin\'", UserModel.class);
        query.setParameter("id", user);

        UserModel result = first(query);
        return result != null;
    }
}