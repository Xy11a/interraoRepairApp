package ru.interrao.itrepair.Web.Repository;

import ru.interrao.itrepair.Web.Entity.Auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
