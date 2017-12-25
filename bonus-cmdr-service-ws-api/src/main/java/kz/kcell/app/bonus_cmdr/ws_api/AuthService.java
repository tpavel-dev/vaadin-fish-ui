package kz.kcell.app.bonus_cmdr.ws_api;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface AuthService {
    Boolean auth(
            @WebParam(name = "user") String user,
            @WebParam(name = "password") String password
    );

}
