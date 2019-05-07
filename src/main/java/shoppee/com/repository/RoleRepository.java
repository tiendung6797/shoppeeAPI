package shoppee.com.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import shoppee.com.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	Optional<Role> findByRoleName(String roleName);
	
	/*@Query( value = "SELECT * FROM role WHERE role_name = ?1", nativeQuery=true )
	Role findByRoleName(String role_name);*/
}
