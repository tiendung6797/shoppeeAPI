package shoppee.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import shoppee.com.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	@Query(value = "SELECT * FROM user AS u WHERE u.email =:email AND u.password =:password ", nativeQuery = true)
	User getUserByUsernameAndPassword(@Param("email") String email, @Param("password") String password);

}
