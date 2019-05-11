package hu.student.projlab.mealride_api.web;


import hu.student.projlab.mealride_api.exception.AlreadyAddedToRestaurantException;
import hu.student.projlab.mealride_api.exception.InvalidDataException;
import hu.student.projlab.mealride_api.exception.UserNotFoundException;
import hu.student.projlab.mealride_api.service.WorkerService;
import hu.student.projlab.mealride_api.service.dto.WorkerDTO;
import hu.student.projlab.mealride_api.util.EndpointConstants;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8080"})
@PreAuthorize("hasRole('ROLE_RESTADMIN')")
@RequestMapping(value = EndpointConstants.RESTAURANT_ENDPOINT)
class WorkerResource {

    private WorkerService workerService;

    @Autowired
    public WorkerResource(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping(EndpointConstants.WORKER_RESOURCE)
    @ApiOperation(value = "Find all workers of a Restaurant",
            response = WorkerDTO.class,
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Workers are returned"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "User is not authenticated"),
            @ApiResponse(code = 403, message = "Invalid operation"),
            @ApiResponse(code = 404, message = "Worker not found.")}
    )
    public ResponseEntity<List<WorkerDTO>> getWorkers(@RequestParam Long id) throws InvalidDataException {
        List<WorkerDTO> result = workerService.findAll(id);
        return new ResponseEntity<>(
                result, null, HttpStatus.OK);
    }

    @PostMapping(EndpointConstants.WORKER_RESOURCE)
    @ApiOperation(value = "Register a new worker into Restaurant",
            response = WorkerDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Worker is added to Restaurant"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 401, message = "User is not authenticated")}
    )
    public ResponseEntity<WorkerDTO> addWorker(
            @ApiParam(value = "Worker data that should be added to Restaurant", required = true)
            @RequestBody String workerEmail) throws UserNotFoundException, AlreadyAddedToRestaurantException {
        WorkerDTO worker = workerService.addWorker(workerEmail);
        return ResponseEntity.ok(worker);
    }

    @PutMapping(EndpointConstants.WORKER_RESOURCE)
    @ApiOperation(value = "Update worker in the Restaurant",
            response = WorkerDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Worker is updated"),
            @ApiResponse(code = 400, message = "ID is not supplied"),
            @ApiResponse(code = 401, message = "User is not authenticated"),
            @ApiResponse(code = 403, message = "Invalid operation"),
            @ApiResponse(code = 404, message = "Worker not found")}
    )
    public ResponseEntity<WorkerDTO> updateWorker(
            @ApiParam(value = "Worker data that should be updated in Restaurant", required = true)
            @RequestBody WorkerDTO workerDTO) throws UserNotFoundException {
        WorkerDTO worker = workerService.updateWorkerRoles(workerDTO);
        return ResponseEntity.ok(worker);
    }

    @DeleteMapping(EndpointConstants.WORKER_RESOURCE)
    @ApiOperation(value = "Remove worker from Restaurant")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Worker is removed"),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 401, message = "User is not authenticated"),
            @ApiResponse(code = 403, message = "Invalid operation"),
            @ApiResponse(code = 404, message = "Worker not found")}
    )
    public ResponseEntity<String> deleteWorker(
            @ApiParam(value = "ID of worker that should be removed from Restaurant", required = true)
            @RequestBody String workerEmail) throws UserNotFoundException {
        workerService.deleteWorker(workerEmail);
        return ResponseEntity.ok("Worker is deleted successfully");
    }

}
