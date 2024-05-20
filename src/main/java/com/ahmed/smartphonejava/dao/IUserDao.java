package com.ahmed.smartphonejava.dao;

import com.ahmed.smartphonejava.exception.ExistUserException;
import com.ahmed.smartphonejava.exception.UserNotFoundException;

public interface IUserDao <T>{
    public String login(String email, String password) throws UserNotFoundException;
    public T register(T user) throws ExistUserException;
    public T update(T user);
    public boolean delete(int id);
    public T findById(int id);
    public T findByUsername(String username);
    public T findByEmail(String email);
    public T[] findAll();
}
