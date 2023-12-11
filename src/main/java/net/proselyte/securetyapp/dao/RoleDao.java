package net.proselyte.securetyapp.dao;

import net.proselyte.securetyapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleDao extends JpaRepository<Role, Long> {
}
