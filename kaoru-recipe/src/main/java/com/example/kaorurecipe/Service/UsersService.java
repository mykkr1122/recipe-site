package com.example.kaorurecipe.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.kaorurecipe.Domain.Users;
import com.example.kaorurecipe.Repository.UsersRepository;

@Service
@Transactional
public class UsersService {
    @Autowired
    private UsersRepository repository;

    public Users load(Integer id) {
        Users user = repository.load(id);
        return user;
    }

    public Users login(String email, String password) {
        Users user = repository.login(email, password);
        return user;
    }

    public void saveUser(Users user) {
        repository.insert(user);
    }

}
