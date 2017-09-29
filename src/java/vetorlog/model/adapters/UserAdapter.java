package vetorlog.model.adapters;

import lombok.NoArgsConstructor;
import org.jvnet.hk2.annotations.Service;
import vetorlog.manager.DatabaseManager;
import vetorlog.model.UserModel;
import vetorlog.model.util.relational.IEntityManagerWrapper;

import javax.persistence.TypedQuery;

@NoArgsConstructor
@Service
public class UserAdapter extends DatabaseManager {
    // É necessário quando não há ecossistema de DI (ex: JUnit)
    public UserAdapter(IEntityManagerWrapper emw) {
        this.emw = emw;
    }

    public UserModel findUserByEmail(String email) {
        TypedQuery<UserModel> query = getEntityManager().createQuery(
                "SELECT m FROM UserModel AS m WHERE m.email LIKE :email", UserModel.class);
        query.setParameter("email", email);
        return first(query);
    }

    public boolean hasRole(UserModel user, String role) {
        TypedQuery<UserModel> query = getEntityManager().createQuery(
                "SELECT m FROM UserModel AS m WHERE m.id = :id AND m.role.name LIKE :role", UserModel.class);
        query.setParameter("id", user.getId());
        query.setParameter("role", role);

        UserModel result = first(query);
        return result != null;
    }
}