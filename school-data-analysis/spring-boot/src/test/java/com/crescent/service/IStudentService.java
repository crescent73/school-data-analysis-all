package com.crescent.service;

import com.crescent.bean.Student;

import java.util.List;

public interface IStudentService {
    void save (String name);
    void delete(Long id);
    void update(Student student);
    Student get(Long id);
    List<Student> list();
}
