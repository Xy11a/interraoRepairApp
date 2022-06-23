package Web.Services.Impl;

import Web.Entity.Auth.Role;
import Web.Entity.Auth.User;
import Web.Repository.RoleRepository;
import Web.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager manager;

    @Autowired
    UserRepository userRep;

    @Autowired
    RoleRepository roleRep;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRep.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException("User not found");
        return user;
    }

    public User findUserById(int id) {
        Optional<User> userFromDB = userRep.findById(id);
        return userFromDB.orElse(new User());
    }

    public List<User> allUsers() {
        return userRep.findAll();
    }

    public boolean saveUser(User user) {
        User userFromDB = userRep.findByUsername(user.getUsername());
        if (userFromDB != null) return false;

        user.setRoles(Collections.singleton(new Role(1, "ROLE_USER")));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRep.save(user);
        return true;
    }

    public boolean deleteUser(int id) {
        if (userRep.findById(id).isPresent()) {
            userRep.deleteById(id);
            return true;
        }
        return false;
    }

    public List<User> usergtList(Integer idMin) {
        return manager.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }

}
