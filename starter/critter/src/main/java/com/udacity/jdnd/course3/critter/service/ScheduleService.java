package com.udacity.jdnd.course3.critter.service;

import com.google.common.collect.Lists;
import com.udacity.jdnd.course3.critter.model.Schedule;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public Schedule saveSchedule(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> findAll() {
        return Lists.newArrayList(scheduleRepository.findAll());
    }

    public List<Schedule> findByEmployeeId(long employeeId) {
        val schedules = scheduleRepository.findAll();
        return StreamSupport.stream(schedules.spliterator(), false)
                .filter(s ->
                        s.getEmployees().stream()
                                .anyMatch(e ->
                                        e.getId().equals(employeeId)
                                )
                ).collect(Collectors.toList());
    }

    public List<Schedule> findByPetId(long petId) {
        val schedules = scheduleRepository.findAll();
        return StreamSupport.stream(schedules.spliterator(), false)
                .filter(s ->
                        s.getPets().stream()
                                .anyMatch(p ->
                                        p.getId().equals(petId)
                                )
                ).collect(Collectors.toList());
    }

    public List<Schedule> findByCustomerId(long customerId) {
        val schedules = scheduleRepository.findAll();
        return StreamSupport.stream(schedules.spliterator(), false)
                .filter(s ->
                        s.getPets().stream()
                                .anyMatch(p ->
                                        p.getOwner().getId().equals(customerId)
                                )
                ).collect(Collectors.toList());

    }
}
