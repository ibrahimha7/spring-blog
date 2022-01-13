package com.example.blog.AUTH;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserService implements UserDetailsService{

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
//        Login to get user from database
//        return new User("966560073923","1234",new ArrayList<>());
        AppUser user = userRepository.findUserByPhoneNumber(phoneNumber);

        if(user == null) {
            log.error("phone number not found");
            throw new UsernameNotFoundException("phone number Not found");
        }else {
            log.info("phone number found");
        }


        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRole())));



        return new User(user.getName(),user.getPassword(),authorities);
    }

//    public UserDetails loadUserByUsername(Long id) throws UsernameNotFoundException {
//        AppUser user = userRepository.findById(id).orElseThrow(()-> new IllegalStateException(
//                "User not exist"
//        ));
//
//        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRole())));
//
//        if(user == null) {
//            throw new UsernameNotFoundException("Not found");
//        }
//
//        return new User(user.getName(),user.getPassword(),authorities);
//    }

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;



    public void saveUser(AppUser user){
        log.info("Saving user");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }


    public void saveRole(AppRole role){
        log.info("Saving role");
        roleRepository.save(role);
    }


    public void addRoleToUser(Long userId, Long roleId){
        AppUser user = userRepository.findById(userId).orElseThrow(()-> new IllegalStateException(
                "User not exist"
        ));
        AppRole role = roleRepository.findById(roleId).orElseThrow(()-> new IllegalStateException(
                "Role not exist"
        ));;
        log.info("adding role to user");
        user.getRoles().add(role);
    }


    public AppUser getUser(String name){
        AppUser user = userRepository.findUserByName(name);
        log.info("get user"+ user);
        return user;
    }

    public AppRole getRole(Long id){
        AppRole role = roleRepository.findById(id).orElseThrow(()-> new IllegalStateException(
                "Role not exist"
        ));
        log.info("get role"+ role);
        return role;
    }


    public List<AppUser> getUsers(){
        log.info("get users");
        return userRepository.findAll();
    }

    public List<AppRole> getRoles(){
        log.info("get roles");
        return roleRepository.findAll();
    }



}
