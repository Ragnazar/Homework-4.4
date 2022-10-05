package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(path = "{id}")
    public Student getStudentInfo(@PathVariable Long id) throws NotFoundException {
        return studentService.findStudent(id);
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @DeleteMapping(path = "{id}")
    public Student removeStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }

    @PutMapping
    public Student updateStudent(@RequestBody Student student) {
        return studentService.editStudent(student);
    }

    @GetMapping(path = "/filter/{age}")
    public Collection<Student> filterByAge(@PathVariable int age) {
        return studentService.getStudentsByAge(age);
    }

}
