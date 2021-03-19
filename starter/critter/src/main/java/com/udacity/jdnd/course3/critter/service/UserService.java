package com.udacity.jdnd.course3.critter.service;

import com.google.common.collect.Lists;
import com.udacity.jdnd.course3.critter.model.Customer;
import com.udacity.jdnd.course3.critter.model.Employee;
import com.udacity.jdnd.course3.critter.model.EmployeeSkill;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> findAllCustomers() {
        return Lists.newArrayList(customerRepository.findAll());
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Optional<Employee> findEmployeeById(long employeeId) {
        return employeeRepository.findById(employeeId);
    }

    public Optional<Customer> findCustomerById(Long customerId) {
        return customerRepository.findById(customerId);
    }

    public Set<Employee> findEmployeeBySkillAndDayOfWeek(Set<EmployeeSkill> skills, DayOfWeek dayOfWeek) {
        return StreamSupport.stream(employeeRepository.findAll().spliterator(), false)
                .filter(e -> e.getSkills().containsAll(skills) &&
                        e.getDaysAvailable().contains(dayOfWeek))
                .collect(Collectors.toSet());
    }
}
