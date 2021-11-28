package com.pc_buy.pcbuy.models.repo;

import com.pc_buy.pcbuy.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByLogin(String login);
}
