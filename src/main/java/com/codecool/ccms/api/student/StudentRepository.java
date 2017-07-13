package com.codecool.ccms.api.student;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bujak on 12.07.17.
 */

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
}
