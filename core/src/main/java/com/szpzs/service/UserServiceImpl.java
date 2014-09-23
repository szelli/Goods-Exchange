package com.szpzs.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.szpzs.model.Role;
import com.szpzs.model.User;
import com.szpzs.repository.UserDAO;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService, UserDetailsService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}
	
/* UserDetailsService */
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		com.szpzs.model.User userEntity = getUserByName(name);
		System.out.println("**************" + userEntity + "************************");
		if(userEntity == null) {
			throw new UsernameNotFoundException("Nincs ilyen nevű felhasználó!");
		}
		
		Boolean enabled;
		if(userEntity.getStatus() != null) {
			enabled = true;
		} else {
			enabled = false;
		}
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		
		return new org.springframework.security.core.userdetails.User(userEntity.getUserName(), userEntity.getPassword(), enabled,
				accountNonExpired, credentialsNonExpired, accountNonLocked, getAuthorities(userEntity.getRole().getId()));
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
		return authList;
	}
	
	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

	public List<String> getRoles(Integer role) {
		 List<String> roles = new ArrayList<String>();
		roles.add("ROLE_USER");
		if(role != null && role.equals(1)) {
			roles.add("ROLE_ADMIN");
		}
		return roles;
	}

/* UserDetailsService - end */


	@Override
	public User getUser(String userName, String password) {
		//password = convertPasswordToMd5(password);
		User user = userDAO.getUser(userName, password);
		return user;
	}
	
	@Override
	public User getUserById(Long id){
		return userDAO.getUserById(id);
	}
	
	@Override
	public User getUserByName(String username){
		return userDAO.getUserByName(username);
	}
	
	@Override
	public String saveUser(User user) {
		if (!existsUser(user.getUserName())){
			user.setRole(addRole());
			user.setPassword(convertPasswordToMd5(user.getPassword()));
			userDAO.saveUser(user);
			if (existsUser(user.getUserName())){
				return "ok";
			} else {
				return "user not saved";
			}
		} else {
			return "user exist";
		}
	}

	@Override
	public String editUser(User user){
		return userDAO.editUser(user);
	}
	
	public String validatePassword(Long id, String password){
		password = convertPasswordToMd5(password);
		if(userDAO.validatePassword(id, password)){
			return "ok";
		} else {
			return null;
		}
	}
	
	public String deleteUser(Long id){
		return userDAO.deleteUser(id);
	}
	
	@Override
	public boolean existsUser(String userName) {
		if (userDAO.existsUser(userName)){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Role addRole() {
		Role role = new Role();
		role.setId(2);
		role.setRole("user");
		return role;
	}

	@Override
	public String convertPasswordToMd5(String pass) {
		return new Md5PasswordEncoder().encodePassword( pass, null );
	}
	
}
