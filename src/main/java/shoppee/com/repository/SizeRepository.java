package shoppee.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import shoppee.com.entities.Size;

@Transactional
@Repository
public interface SizeRepository extends JpaRepository<Size, Integer> {

	@Modifying
	@Query(value = "UPDATE Size s SET s.quantity = ?1 WHERE s.size_id = ?2")
	int update(int quantity, int size_id);

	@Query(value = "SELECT * FROM size WHERE pro_id = ?1", nativeQuery = true)
	List<Size> getSizeByProId(int proId);
	
	@Modifying
	@Query(value = "UPDATE Size SET quantity = quantity - 1 WHERE pro_id = :pro_id AND size_name = :size_name")
	int updateQuantity(@Param("pro_id") Integer pro_id, @Param("size_name") String size_name);
	
}
