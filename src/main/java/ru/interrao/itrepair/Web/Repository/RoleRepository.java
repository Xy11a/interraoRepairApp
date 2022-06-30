package ru.interrao.itrepair.Web.Repository;

import ru.interrao.itrepair.Web.Entity.Auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer>
{

}
