package vetorlog.serializer;

import vetorlog.dto.UserDTO;
import vetorlog.model.UserModel;

public class UserSerializer {
    public static UserDTO fromModelToDTO(UserModel model) {
        UserDTO dto = new UserDTO();
        dto.setEmail(model.getEmail());
        dto.setName(model.getName());

        return dto;
    }
}
