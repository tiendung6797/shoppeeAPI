package shoppee.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import shoppee.com.entities.Admin;
import shoppee.com.entities.Role;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
	
	//Optional<Admin> findByUsername(String username);
	@Query(value = "SELECT * FROM admin AS ad WHERE ad.admin_id =:id", nativeQuery = true)
	Admin findById(@Param("id") int id);
	
	@Query(value = "SELECT * FROM admin AS ad WHERE ad.username =:username", nativeQuery = true)
	Admin findByUsername(@Param("username") String username);
	
	@Query(value = "SELECT * FROM admin AS ad WHERE ad.username =:username AND ad.password =:password ", nativeQuery = true)
	Admin getAdminByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}