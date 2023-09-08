package org.example.bo.custom.Impl;



import org.example.bo.custom.UserBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.UserDAO;
import org.example.dto.UserDTO;
import org.example.entity.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public List<UserDTO> getAllUsers() throws SQLException, ClassNotFoundException, IOException {
        List<UserDTO> allUsers= new ArrayList<>();
        List<User> all = userDAO.getAll();
        for (User user : all) {
            allUsers.add(new UserDTO(user.getUserID(), user.getUserName(), user.getPassword(), user.getEmail()));
        }
        return allUsers;
    }

    @Override
    public boolean addUser(UserDTO dto) throws SQLException, ClassNotFoundException, IOException {
        return userDAO.add(new User(dto.getUserID(),dto.getUserName(),dto.getPassword(),dto.getEmail()));
    }

    @Override
    public boolean updateUser(UserDTO dto) throws SQLException, ClassNotFoundException, IOException {
        return userDAO.update(new User(dto.getUserID(),dto.getUserName(),dto.getPassword(),dto.getEmail()));
    }

    @Override
    public boolean deleteUser(String id) throws SQLException, ClassNotFoundException, IOException {
        return userDAO.delete(id);
    }

    @Override
    public UserDTO searchUser(String id) throws SQLException, ClassNotFoundException, IOException {
        User user = userDAO.search(id);
        return new UserDTO(user.getUserID(),user.getUserName(),user.getPassword(),user.getEmail());
    }

    @Override
    public String generateNewUserID() throws SQLException, ClassNotFoundException, IOException {
        return userDAO.generateNewID();
    }
}
