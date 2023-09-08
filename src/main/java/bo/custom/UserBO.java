package bo.custom;

import bo.SuperBO;
import dto.UserDTO;

import java.util.List;

public interface UserBO extends SuperBO {

    boolean checkUser(String userName, String password);

    boolean saveUser(UserDTO userDTO);

    boolean updateUser(UserDTO userDTO);

    boolean deleteStudent(String userId);

    List<UserDTO> getAllStudent();
}
