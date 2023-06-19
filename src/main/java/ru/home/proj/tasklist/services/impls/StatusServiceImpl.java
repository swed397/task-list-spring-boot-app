package ru.home.proj.tasklist.services.impls;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.home.proj.tasklist.entities.Status;
import ru.home.proj.tasklist.repos.StatusRepository;
import ru.home.proj.tasklist.services.StatusService;

@Service
@AllArgsConstructor
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;

    @Override
    public Status findStatusByName(String name) {
        return statusRepository.findByName(name);
    }
}
