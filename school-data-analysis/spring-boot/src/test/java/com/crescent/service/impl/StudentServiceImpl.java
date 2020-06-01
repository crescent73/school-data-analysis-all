package com.crescent.service.impl;

import com.crescent.bean.Student;
import com.crescent.dao.StudentDao;
import com.crescent.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public void save(String name) {
        studentDao.save(name);
    }

    @Override
    public void delete(Long id) {
        studentDao.delete(id);
    }

    @Override
    public void update(Student student) {
        studentDao.update(student);
    }

    @Override
    public Student get(Long id) {
        return studentDao.get(id);
    }

    @Override
    public List<Student> list() {
        return studentDao.list();
    }
}
