package ru.home.proj.tasklist.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.home.proj.tasklist.entities.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
