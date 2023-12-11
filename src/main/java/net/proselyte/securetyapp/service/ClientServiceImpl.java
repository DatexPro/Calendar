package net.proselyte.securetyapp.service;

import net.proselyte.securetyapp.dao.ClientDao;
import net.proselyte.securetyapp.dao.RoleDao;
import net.proselyte.securetyapp.model.Client;
import net.proselyte.securetyapp.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(Client client) {
        client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOne(2L));
        client.setRoles(roles);
        clientDao.save(client);
    }

    @Override
    public Client findByName(String name) {
        return clientDao.findByName(name);
    }

    @Override
    public List<Client> getAllClient() {
        return clientDao.findAll();
    }
}
