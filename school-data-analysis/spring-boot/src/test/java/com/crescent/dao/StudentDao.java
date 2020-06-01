package com.crescent.dao;

import com.crescent.bean.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface StudentDao {
    // 添加
    @Insert("insert into student(name) values(#{name})")
    void save(String name);

    // 删除
    @Delete("delete from student where id=#{id}")
    void delete(Long id);

    // 修改
    @Update("update student set name=#{name} where id=#{id}")
    void update(Student student);

    // 查询单个
    @Select("select * from student where id=#{id}")
    Student get(Long id);
    // 查询多个

    @Select("select * from student")
    List<Student> list();
}
