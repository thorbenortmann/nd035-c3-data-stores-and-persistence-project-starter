package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private PetService petService;

    @Autowired
    private UserService userService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        val customerToSave = toCustomer(customerDTO);
        val savedCustomer = userService.saveCustomer(customerToSave);
        return toCustomerDTO(savedCustomer);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        return userService.findAllCustomers().stream().map(this::toCustomerDTO).collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        throw new UnsupportedOperationException();
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        throw new UnsupportedOperationException();
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        throw new UnsupportedOperationException();
    }

    private Customer toCustomer(CustomerDTO customerDTO) {
        val customer = new Customer();
        customerDTO.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setNotes(customerDTO.getNotes());
        if (customerDTO.getPetIds() != null) {
            customer.setPets(petService.findPets(customerDTO.getPetIds()));
        }

        return customer;
    }

    private CustomerDTO toCustomerDTO(Customer customer) {
        var customerDTO = new CustomerDTO();
        customerDTO.setId(customer.getId());
        customerDTO.setName(customer.getName());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        customerDTO.setNotes(customer.getNotes());
        if (customer.getPets() != null) {
            customerDTO.setPetIds(customer.getPets().stream().map(Pet::getId).collect(Collectors.toList()));
        }
        return customerDTO;
    }
}
