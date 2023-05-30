package ru.home.proj.tasklist.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.proj.tasklist.entities.Role;
import ru.home.proj.tasklist.repos.RoleRepository;
import ru.home.proj.tasklist.services.RoleService;


@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(Role entity) {
        return roleRepository.save(entity);
    }

    @Override
    public void delete(Role entity) {
        roleRepository.delete(entity);
    }

    @Override
    public Role get(Long id) {
        return roleRepository.findById(id).orElse(null);
    }
}
