package com.ahmed.smartphonejava.service;

import com.ahmed.smartphonejava.beans.User;
import com.ahmed.smartphonejava.dao.IUserDao;
import com.ahmed.smartphonejava.connexion.Connexion;
import com.ahmed.smartphonejava.exception.ExistUserException;
import com.ahmed.smartphonejava.exception.UserNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserService implements IUserDao<User> {
    @Override
    public String login(String email, String password) {
        User existingUser=findByEmail(email);
        if(existingUser.getId()<=0 && existingUser.getEmail()==null){
            return "User Not found";
        }else{
            if(existingUser.getPassword().equals(password)){
                return "Log in Succeffuly";
            }else{
                return "Incorrect Password";
            }
        }
    }

    @Override
    public User register(User user) throws ExistUserException {
        User existingUser= findByEmail(user.getEmail());
        System.out.println(existingUser);
        if(existingUser.getId() > 0 && existingUser.getEmail()!=null) {
            throw new ExistUserException("User already exists");
        }else{
            String query = "INSERT INTO user (email, username, password) VALUES (?, ?, ?)";
            try{
                PreparedStatement ps = Connexion.connecter().prepareStatement(query);
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getUsername());
                ps.setString(3, user.getPassword());
                ps.executeUpdate();
                ps.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        String query = "SELECT * FROM user WHERE email = ?";
        User user = new User();
        try{
            PreparedStatement ps = Connexion.connecter().prepareStatement(query);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setCreated_at(rs.getDate("createdAt"));
            }
            ps.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User[] findAll() {
        return new User[0];
    }
}
