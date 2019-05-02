package hu.student.projlab.mealride_api.service.mapper;

import hu.student.projlab.mealride_api.domain.user.SpringUser;
import hu.student.projlab.mealride_api.service.dto.WorkerDTO;
import org.springframework.stereotype.Component;

@Component
public class WorkerMapper {

    public WorkerDTO createWorkerDTO(SpringUser springUser) {
        WorkerDTO worker = new WorkerDTO();
        worker.setId(springUser.getId());
        worker.setUsername(springUser.getUsername());
        worker.setFirstname(springUser.getCustomerUser().getFirstname());
        worker.setLastname(springUser.getCustomerUser().getLastname());
        worker.setPhone(springUser.getCustomerUser().getPhone());
        worker.setRoles(springUser.getRoles());
        return worker;
    }

}
