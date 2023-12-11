package net.proselyte.securetyapp.service;

import net.proselyte.securetyapp.dao.ClientDao;
import net.proselyte.securetyapp.model.Client;
import net.proselyte.securetyapp.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

public class ClientDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClientDao clientDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Client client = clientDao.findByName(name);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role: client.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new User(client.getName(),client.getPassword(), grantedAuthorities);
    }
}
