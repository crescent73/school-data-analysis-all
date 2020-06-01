package com.crescent.controller;

import com.crescent.bean.Student;
import com.crescent.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // 返回json数据
@RequestMapping("/test")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    // 保存学生
    @RequestMapping("/save1")
    public String save(String name) {
        studentService.save(name);

        return "save success!";
    }

    // 删除学生
    @RequestMapping("/delete")
    public String delete(Long id) {
        studentService.delete(id);
        return "delete success";
    }

    // 修改学生
    @RequestMapping("/update")
    public String update(Student student) {
        studentService.update(student);
        return "update success";
    }

    // 查询单个学生
    @RequestMapping("/get")
    public Student get(Long id) {
        return studentService.get(id);
    }

    // 查询所有学生
    @RequestMapping("/list1")
    public List<Student> list() {
        return studentService.list();
    }
}
