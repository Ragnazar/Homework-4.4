package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
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
        Student student = students.get(id);
        System.out.println(student);
        if (student == null){
            throw new NotFoundException("Ученика с таким id нет в списке");
        }
        return student;
    }

    public Student editStudent(Student student) {
        long id = student.getId();
        Student tar = students.get(id);
        if (tar == null) {
            throw new NotFoundException("Ученика с таким id нет в списке");
        }
        students.put(student.getId(), student);
        return student;
    }

    public Student deleteStudent(long id) {
        return students.remove(id);
    }

    public Collection<Student> getStudentsByAge(int age) {
        Collection<Student> tmp = new ArrayList<>() {
        };
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