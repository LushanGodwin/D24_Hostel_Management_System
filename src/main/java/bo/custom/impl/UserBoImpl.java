package bo.custom.impl;

import bo.custom.UserBO;
import dao.DAOFactory;
import dao.custom.UserDAO;
import dto.UserDTO;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserBoImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public boolean checkUser(String userName, String password) {
        return userDAO.check(userName,password);
    }

    @Override
    public boolean saveUser(UserDTO userDTO) {
        return userDAO.save(new User(userDTO.getUserId(), userDTO.getUserName(), userDTO.getPassword(), userDTO.getPasswordHint()));
    }

    @Override
    public boolean updateUser(UserDTO userDTO) {
        return userDAO.update(new User(userDTO.getUserId(), userDTO.getUserName(), userDTO.getPassword(), userDTO.getPasswordHint()));
    }

    @Override
    public boolean deleteStudent(String userId) {
        User user = new User();
        user.setUserId(userId);
        return userDAO.delete(user);
    }

    @Override
    public List<UserDTO> getAllStudent() {
        List<UserDTO> userDTOArrayList = new ArrayList<>();
        List<User> userArrayList = userDAO.getAll();
        for (User user:userArrayList) {
            userDTOArrayList.add(new UserDTO(user.getUserId(),user.getUserName(),user.getPassword(),user.getPassword_hint()));
        }
        return userDTOArrayList;
    }
}
