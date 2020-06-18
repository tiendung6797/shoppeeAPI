package shoppee.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import shoppee.com.entities.Admin;
import shoppee.com.entities.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {
	
	@Query(value = "SELECT * FROM store AS s WHERE s.email =:email AND s.password =:password ", nativeQuery = true)
	Store getStoreByUsernameAndPassword(@Param("email") String email, @Param("password") String password);

	@Query(value = "SELECT * FROM store AS s WHERE s.email =:username", nativeQuery = true)
	Store findByUserName(@Param("username") String username);
	
	@Query(value = "SELECT * FROM store AS s WHERE s.store_id =:id", nativeQuery = true)
	Store findById(@Param("id") int id);

	@Query(value = "SELECT * FROM store AS s WHERE s.email = ?1", nativeQuery = true)
	Store getStoreByEmail(String email);
	
	@Query(value = "SELECT * FROM store AS s WHERE s.store_name LIKE %:searchTerm%", nativeQuery = true)	
	List<Store> findStoredByText(@Param("searchTerm") String text);

}
