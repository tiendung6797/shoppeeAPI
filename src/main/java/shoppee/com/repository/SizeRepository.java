package shoppee.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shoppee.com.entities.Size;

@Repository
public interface SizeRepository extends JpaRepository<Size, Integer> {
	
	
}
