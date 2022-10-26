package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;
import ru.hogwarts.school.repository.StudentRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final FacultyRepository facultyRepository;

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository, FacultyRepository facultyRepository) {
        this.studentRepository = studentRepository;
        this.facultyRepository = facultyRepository;
    }

    public Student createStudent(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        logger.info("Was invoked method for search student by id");
        return studentRepository.findById(id).orElse(null);
    }

    @Transactional
    public Collection<Student> getAllStudents() {
        logger.info("A method was invoked to get a list of all the students recorded in the database");
        return studentRepository.findAll();
    }

    public Student editStudent(Student student) {
        logger.info("Was invoked method to edit the student");
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.info("Was invoked method to delete the student");
        studentRepository.deleteById(id);
    }


    public Collection<Student> findStudentsByAgeIsBetween(int age1, int age2) {
        logger.info("A method was called to search for students whose age is in the range from " + age1 + "to " + age2);
        return studentRepository.findStudentsByAgeIsBetween(age1, age2);
    }

    public Collection<Student> findByNameAndAge(String name, int age) {
        logger.info("The method of searching for students whose name and age match was called " + name + "and " + age);
        return studentRepository.findByNameIgnoreCaseAndAge(name, age);
    }


    public Faculty findFaculty(Long id) {
        logger.info("Was invoked method for search faculty by its student id");
        return facultyRepository.findByStudentsId(id);
    }

    public Integer getCount() {
        logger.info("Was invoked method for counting students on database");
        return studentRepository.getCount();
    }

    public Double gatAverageAge() {
        logger.info("Was invoked method for counting students average age");
        return studentRepository.getAverageAge();
    }

    public List<Student> getLastFive() {
        logger.info("Was invoked method for show last five student in the database");
        return studentRepository.getLastFive();
    }
}