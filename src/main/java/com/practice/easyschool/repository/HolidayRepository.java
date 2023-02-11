package com.practice.easyschool.repository;

import com.practice.easyschool.model.Holiday;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidayRepository extends CrudRepository<Holiday, String> {

}
