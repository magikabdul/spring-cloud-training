package pl.training.cloud.users.service;

import feign.FeignException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.training.cloud.users.model.Department;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class FeignDepartmentsService implements DepartmentsService {

    @NonNull
    private FeignDepartmentsClient feignDepartmentsClient;

    @Cacheable(value = "departments", unless = "#result == null")
    @Override
    public Optional<Department> getDepartmentById(Long id) {
        try {
            return Optional.of(feignDepartmentsClient.getDepartmentById(id));
        } catch (FeignException ex) {
            ex.printStackTrace();
            Logger.getLogger(getClass().getName()).log(Level.INFO, "### Fetching department failed");
        }
        return Optional.empty();
    }

}
