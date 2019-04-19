package shoppee.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import shoppee.com.entities.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
	
	@Query(value = "SELECT * FROM admin AS ad WHERE ad.username =:username AND ad.password =:password ", nativeQuery = true)
	Admin getAdminByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}