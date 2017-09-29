package vetorlog.serializer;

import org.jvnet.hk2.annotations.Service;
import vetorlog.dto.UserDTO;
import vetorlog.model.UserModel;

@Service
public class UserSerializer {
    public UserDTO fromModelToDTO(UserModel model) {
        UserDTO dto = new UserDTO();
        dto.setEmail(model.getEmail());
        dto.setName(model.getName());

        return dto;
    }
}
