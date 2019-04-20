package shoppee.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shoppee.com.entities.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Integer> {
	
}
