package shoppee.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import shoppee.com.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	@Query(value = "SELECT * FROM category AS ca WHERE ca.parent_id = 0", nativeQuery = true)
	List<Category> getParentCategory();

}
