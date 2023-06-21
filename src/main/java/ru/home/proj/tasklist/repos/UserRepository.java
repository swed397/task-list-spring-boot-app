package ru.home.proj.tasklist.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.home.proj.tasklist.entities.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select\n" +
            "\t*\n" +
            "from\n" +
            "\tusers u\n" +
            "join user_roles ur on\n" +
            "\tu.id = ur.role_id where u.username = :username",
            nativeQuery = true)
    Optional<User> findByUsername(@Param("username") String username);
}
