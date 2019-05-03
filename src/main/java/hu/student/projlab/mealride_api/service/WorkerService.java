package hu.student.projlab.mealride_api.service;

import hu.student.projlab.mealride_api.config.security.SecurityUtils;
import hu.student.projlab.mealride_api.domain.user.SpringUser;
import hu.student.projlab.mealride_api.exception.InvalidDataException;
import hu.student.projlab.mealride_api.exception.UserIsNotAuthenticatedException;
import hu.student.projlab.mealride_api.exception.UserNotFoundException;
import hu.student.projlab.mealride_api.repository.RestaurantRepository;
import hu.student.projlab.mealride_api.service.dto.UserDTO;
import hu.student.projlab.mealride_api.service.dto.WorkerDTO;
import hu.student.projlab.mealride_api.service.mapper.WorkerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkerService {

    private WorkerMapper workerMapper;

    private RestaurantRepository restaurantRepository;

    private UserService userService;

    @Autowired
    public WorkerService(WorkerMapper workerMapper, RestaurantRepository restaurantRepository, UserService userService) {
        this.workerMapper = workerMapper;
        this.restaurantRepository = restaurantRepository;
        this.userService = userService;
    }

    public List<WorkerDTO> findAll(Long id) throws UserIsNotAuthenticatedException, InvalidDataException {
        if (id != userService.getCurrentUser(SecurityUtils.getCurrentUserLogin())
                .orElseThrow(() -> new UserIsNotAuthenticatedException("User not found."))
                .getRestaurantUser().getRestaurant().getId())
            throw new InvalidDataException();
        List<SpringUser> users = userService.findAllWorkers(id);
        return users.stream()
                .map(user -> workerMapper.createWorkerDTO(user))
                .collect(Collectors.toList());
    }

    public WorkerDTO addWorker(UserDTO userDTO) throws UserNotFoundException, UserIsNotAuthenticatedException {
        SpringUser springUser = userService.findByEmail(userDTO.getUsername())
                .orElseThrow(() -> new UserNotFoundException(userDTO.getUsername()));
        // TODO This currentuser-getting needs a refactor.. everywhere.
        springUser.getRestaurantUser().setRestaurant(userService.getCurrentUser(SecurityUtils.getCurrentUserLogin())
                .orElseThrow(() -> new UserIsNotAuthenticatedException("User not found."))
                .getRestaurantUser().getRestaurant());
        // TODO save restaurantuser

        // 1. Check if user exists
        // 2. Check if current user has restaurant
        // 3. Check if current user has ROLE_RESTADMIN (it is checked in controller with @PreAuthorize !)
        // 4. Set worker to restaurants
        // 5. Add user to Restaurant's workers list
        // 6. Return the new worker data



        return workerMapper.createWorkerDTO(springUser);
    }

    public WorkerDTO updateWorkerRoles(WorkerDTO workerDTO) {
        // TODO update worker
        return new WorkerDTO();
    }

    public void deleteWorker(UserDTO userDTO) {
        // TODO delete worker
    }
}
