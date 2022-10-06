package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.*;

@Service
public class StudentService {
    public HashMap<Long, Student> students = new HashMap<>();
    private long lastId = 0;

    public Student createStudent(Student student) {
        student.setId(++lastId);
        students.put(lastId, student);
        return student;
    }

    public Student findStudent(long id) {
        return students.get(id);
    }
    public Collection<Student> getAll(){
        return students.values();
    }

    public Student editStudent(Student student) {
        students.put(student.getId(), student);
        return student;
    }

    public Student deleteStudent(long id) {
        return students.remove(id);
    }

    public Collection<Student> getStudentsByAge(int age) {
        Collection<Student> tmp = new ArrayList<>();
        for (long i = 1; i <= students.size(); i++) {
            if (students.get(i) == null){
                i++;
            }
            if (students.get(i).getAge() == age) {
                tmp.add(students.get(i));
            }
        }
        return Collections.unmodifiableCollection(tmp);
    }
}