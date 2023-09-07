package bo.custom;

import bo.SuperBO;
import dto.UserDTO;

public interface UserBO extends SuperBO {

    boolean checkUser(String userName, String password);

    boolean saveUser(UserDTO userDTO);
}
