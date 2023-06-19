package ru.home.proj.tasklist.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional
    @Override
    public Role save(Role entity) {
        return roleRepository.save(entity);
    }

    @Transactional
    @Override
    public void delete(Role entity) {
        roleRepository.delete(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public Role get(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    @Override
    public Role findByName(String name) {
        return roleRepository.findByRole(name);
    }
}
