package hu.student.projlab.mealride_api.web;


import hu.student.projlab.mealride_api.exception.AlreadyAddedToRestaurantException;
import hu.student.projlab.mealride_api.exception.InvalidDataException;
import hu.student.projlab.mealride_api.exception.UserNotFoundException;
import hu.student.projlab.mealride_api.service.WorkerService;
import hu.student.projlab.mealride_api.service.dto.WorkerDTO;
import hu.student.projlab.mealride_api.util.EndpointConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_RESTADMIN')")
@RequestMapping(value = EndpointConstants.RESTAURANT_ENDPOINT)
class WorkerResource {

    private WorkerService workerService;

    @Autowired
    public WorkerResource(WorkerService workerService) {
        this.workerService = workerService;
    }

    //    @PreAuthorize("hasRole('ROLE_RESTADMIN')")
    @GetMapping(EndpointConstants.WORKER_RESOURCE)
    public ResponseEntity<List<WorkerDTO>> getWorkers(@RequestParam Long id) throws InvalidDataException {
        List<WorkerDTO> result = workerService.findAll(id);
        return new ResponseEntity<>(
                result, null, HttpStatus.OK);
    }

    //  @PreAuthorize("hasRole('ROLE_RESTADMIN')")
    @PostMapping(EndpointConstants.WORKER_RESOURCE)
    public ResponseEntity<WorkerDTO> addWorker(@RequestBody String workerEmail) throws UserNotFoundException, AlreadyAddedToRestaurantException {
        WorkerDTO worker = workerService.addWorker(workerEmail);
        return ResponseEntity.ok(worker);
    }

    // @PreAuthorize("hasRole('ROLE_RESTADMIN')")
    @PutMapping(EndpointConstants.WORKER_RESOURCE)
    public ResponseEntity<WorkerDTO> updateWorker(@RequestBody WorkerDTO workerDTO) throws UserNotFoundException {
        WorkerDTO worker = workerService.updateWorkerRoles(workerDTO);
        return ResponseEntity.ok(worker);
    }

    // @PreAuthorize("hasRole('ROLE_RESTADMIN')")
    @DeleteMapping(EndpointConstants.WORKER_RESOURCE)
    public ResponseEntity<String> deleteWorker(@RequestBody String workerEmail) throws UserNotFoundException {
        workerService.deleteWorker(workerEmail);
        return ResponseEntity.ok("Worker is deleted successfully");
    }

}
