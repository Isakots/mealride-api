package hu.student.projlab.mealride_api.service;

import hu.student.projlab.mealride_api.config.security.SecurityUtils;
import hu.student.projlab.mealride_api.domain.Restaurant;
import hu.student.projlab.mealride_api.domain.Role;
import hu.student.projlab.mealride_api.domain.user.RestaurantUser;
import hu.student.projlab.mealride_api.domain.user.SpringUser;
import hu.student.projlab.mealride_api.exception.AlreadyAddedToRestaurantException;
import hu.student.projlab.mealride_api.exception.InvalidDataException;
import hu.student.projlab.mealride_api.exception.UserNotFoundException;
import hu.student.projlab.mealride_api.repository.RestaurantRepository;
import hu.student.projlab.mealride_api.service.dto.WorkerDTO;
import hu.student.projlab.mealride_api.service.mapper.WorkerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
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

    public List<WorkerDTO> findAll(Long id) throws InvalidDataException {
        if (id != userService.getCurrentUser(SecurityUtils.getCurrentUserLogin())
                .getRestaurantUser().getRestaurant().getId())
            throw new InvalidDataException();
        List<SpringUser> users = userService.findAllWorkers(id);
        return users.stream()
                .map(user -> workerMapper.createWorkerDTO(user))
                .collect(Collectors.toList());
    }

    // 1. Check if user exists
    // 2. Check if current user has restaurant
    // 3. Check if current user has ROLE_RESTADMIN (it is checked in controller with @PreAuthorize !)
    // 4. Set worker to restaurants
    // 5. Add user to Restaurant's workers list
    // 6. Return the new worker data
    public WorkerDTO addWorker(String email) throws UserNotFoundException, AlreadyAddedToRestaurantException {
        SpringUser springUser = findSpringUserWithEmail(email);
        if (springUser.getRestaurantUser() == null)
            springUser.setRestaurantUser(new RestaurantUser());

        if (springUser.getRestaurantUser().getRestaurant() != null)
            throw new AlreadyAddedToRestaurantException();

        Restaurant restaurant = userService.getCurrentUser(SecurityUtils.getCurrentUserLogin())
                .getRestaurantUser().getRestaurant();

        springUser.getRestaurantUser().setRestaurant(restaurant);
        springUser.getRoles().add(new Role("ROLE_RESTWORKER"));
        restaurant.getWorkers().add(springUser.getRestaurantUser());
        // Do I need a restaurantUserrepository  ????
        userService.save(springUser);
        restaurantRepository.save(restaurant);
        return workerMapper.createWorkerDTO(springUser);
    }

    public WorkerDTO updateWorkerRoles(WorkerDTO workerDTO) throws UserNotFoundException {
        SpringUser springUser = findSpringUserWithEmail(workerDTO.getUsername());

        // TODO check if roles are allowed

        springUser.setRoles(workerDTO.getRoles());
        return workerMapper.createWorkerDTO(springUser);
    }

    public void deleteWorker(String email) throws UserNotFoundException {
        SpringUser springUser = findSpringUserWithEmail(email);

        Restaurant restaurant = springUser.getRestaurantUser().getRestaurant();
        restaurant.getWorkers().remove(springUser.getRestaurantUser());
        restaurantRepository.save(restaurant);
        springUser.getRestaurantUser().setRestaurant(null);
        springUser.getRoles().remove(new Role("ROLE_RESTWORKER"));
        if (springUser.getRoles().contains(new Role("ROLE_RESTADMIN")))
            springUser.getRoles().remove(new Role("ROLE_RESTADMIN"));

        userService.save(springUser);
    }

    private SpringUser findSpringUserWithEmail(String email) throws UserNotFoundException {
        return userService.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }
}
