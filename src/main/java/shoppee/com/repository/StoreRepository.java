package shoppee.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import shoppee.com.entities.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {
	
	@Query(value = "SELECT * FROM store AS s WHERE s.email =:email AND s.password =:password ", nativeQuery = true)
	Store getStoreByUsernameAndPassword(@Param("email") String email, @Param("password") String password);
}
