package vetorlog.api;

import io.swagger.annotations.*;
import lombok.extern.log4j.Log4j2;
import vetorlog.conf.Constant;
import vetorlog.controller.UserController;
import vetorlog.dto.CreateUserDTO;
import vetorlog.dto.LoginDTO;
import vetorlog.dto.TokenDTO;
import vetorlog.dto.error.ErrorDTO;
import vetorlog.dto.error.UnauthorizedErrorDTO;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Api(value = "/user")
@Log4j2
@Path("v1/user")
public class UserAPI {
    @Inject
    private UserController controller;

    @ApiOperation(value = "Login", produces = MediaType.APPLICATION_JSON, response = TokenDTO.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = Constant.CODE_ERROR_400, response = ErrorDTO.class),
            @ApiResponse(code = 500, message = Constant.CODE_ERROR_500, response = ErrorDTO.class),
            @ApiResponse(code = 401, message = Constant.CODE_ERROR_401, response = UnauthorizedErrorDTO.class),
    })
    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response login(@ApiParam(name = "body") LoginDTO loginDTO) {
        return controller.login(loginDTO);
    }

    @ApiOperation(value = "Criar usuário", produces = MediaType.APPLICATION_JSON, response = TokenDTO.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = Constant.CODE_ERROR_400, response = ErrorDTO.class),
            @ApiResponse(code = 500, message = Constant.CODE_ERROR_500, response = ErrorDTO.class),
            @ApiResponse(code = 401, message = Constant.CODE_ERROR_401, response = UnauthorizedErrorDTO.class),
    })
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response createUser(@ApiParam(name = "body") CreateUserDTO createUserDTO) {
        return controller.create(createUserDTO);
    }
}
