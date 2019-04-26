package shoppee.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import shoppee.com.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	@Query(value = "SELECT * FROM category AS ca WHERE ca.parent_id = 0", nativeQuery = true)
	List<Category> getParentCategory();
	
	@Query(value = "SELECT * FROM category AS ca WHERE ca.parent_id = :parent_id", nativeQuery = true)
	List<Category> getCategoryByParent(@Param("parent_id") Integer parent_id);
	
	@Query(value = "SELECT * FROM category AS ca WHERE ca.parent_id != 0", nativeQuery = true)
	List<Category> getAllCategory();

}
