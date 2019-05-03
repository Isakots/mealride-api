package hu.student.projlab.mealride_api.web;


import hu.student.projlab.mealride_api.exception.AlreadyAddedToRestaurantException;
import hu.student.projlab.mealride_api.exception.InvalidDataException;
import hu.student.projlab.mealride_api.exception.UserIsNotAuthenticatedException;
import hu.student.projlab.mealride_api.exception.UserNotFoundException;
import hu.student.projlab.mealride_api.service.WorkerService;
import hu.student.projlab.mealride_api.service.dto.UserDTO;
import hu.student.projlab.mealride_api.service.dto.WorkerDTO;
import hu.student.projlab.mealride_api.util.EndpointConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// TODO Check if this is working on class or not..
// @PreAuthorize("hasRole('ROLE_RESTADMIN')")
@RequestMapping(value = EndpointConstants.RESTAURANT_ENDPOINT)
class WorkerController {

    private WorkerService workerService;

    @Autowired
    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }


    @PreAuthorize("hasRole('ROLE_RESTADMIN')")
    @GetMapping(EndpointConstants.WORKER_RESOURCE)
    public ResponseEntity<List<WorkerDTO>> getWorkers(@RequestParam Long id) throws InvalidDataException, UserIsNotAuthenticatedException {
        List<WorkerDTO> result = workerService.findAll(id);
        return new ResponseEntity<>(
                result, null, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_RESTADMIN')")
    @PostMapping(EndpointConstants.WORKER_RESOURCE)
    public ResponseEntity<WorkerDTO> addWorker(@RequestBody String workerEmail) throws UserNotFoundException, UserIsNotAuthenticatedException, AlreadyAddedToRestaurantException {
        WorkerDTO worker = workerService.addWorker(workerEmail);
        return ResponseEntity.ok(worker);
    }

    @PreAuthorize("hasRole('ROLE_RESTADMIN')")
    @PutMapping(EndpointConstants.WORKER_RESOURCE)
    public ResponseEntity<WorkerDTO> updateWorker(@RequestBody WorkerDTO workerDTO) throws UserNotFoundException {
        // This method is only for adding/removing ROLE_RESTADMIN to user
        WorkerDTO worker = workerService.updateWorkerRoles(workerDTO);
        return ResponseEntity.ok(worker);
    }

    @PreAuthorize("hasRole('ROLE_RESTADMIN')")
    @DeleteMapping(EndpointConstants.WORKER_RESOURCE)
    public ResponseEntity<String> deleteWorker(@RequestBody String workerEmail) throws UserNotFoundException {
        workerService.deleteWorker(workerEmail);
        return ResponseEntity.ok("Worker is deleted successfully");
    }

}
