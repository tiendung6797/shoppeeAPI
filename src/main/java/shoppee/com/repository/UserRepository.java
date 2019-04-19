package shoppee.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import shoppee.com.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
