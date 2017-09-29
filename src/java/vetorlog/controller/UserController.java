package vetorlog.controller;

import lombok.SneakyThrows;
import org.jvnet.hk2.annotations.Service;
import vetorlog.controller.prototype.Controller;
import vetorlog.dto.CreateUserDTO;
import vetorlog.dto.LoginDTO;
import vetorlog.dto.TokenDTO;
import vetorlog.dto.UserDTO;
import vetorlog.model.UserModel;
import vetorlog.model.adapters.UserAdapter;
import vetorlog.serializer.UserSerializer;
import vetorlog.util.PasswordUtils;
import vetorlog.util.TokenUtils;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

@Service
public class UserController extends Controller {
    @Inject
    private UserAdapter dbAdapter;

    @SneakyThrows
    public Response login(LoginDTO dto) {
        Response unauthorized = response.unauthorized(i18n().get("invalid_email_password"), true);

        UserModel user = dbAdapter.findUserByEmail(dto.getEmail());
        if (user == null)
            return unauthorized;

        boolean passwordMatch = PasswordUtils.checkPassword(dto.getPassword(), user.getPassword());
        if(passwordMatch)
            return response.ok(new TokenDTO(TokenUtils.generateToken(user.getId())));

        return unauthorized;
    }

    @SneakyThrows
    public Response create(CreateUserDTO createUserDTO) {
        String passwordHash = PasswordUtils.generateHashPassword(createUserDTO.getPassword(), null);

        // TODO: Validação automática no JSON, possibilidade de mapeamento mais facilitada
        UserModel model = new UserModel();
        model.setName(createUserDTO.getName());
        model.setEmail(createUserDTO.getEmail());
        model.setPassword(passwordHash);
        dbAdapter.insert(model);

        UserDTO data = UserSerializer.fromModelToDTO(model);
        return response.ok(data);
    }
}
