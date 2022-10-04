package ru.hogwarts.school.controller;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping(path = "{id}")
    public Faculty getFacultyInfo(@PathVariable Long id) {
        return facultyService.findFaculty(id);
    }

    @PostMapping
    public Faculty addFaculty(Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @DeleteMapping(path = "{id}")
    public Faculty removeFaculty(@PathVariable Long id) {
        return facultyService.deleteFaculty(id);
    }

    @PutMapping
    public Faculty updateFaculty(Faculty faculty) {
        return facultyService.editFaculty(faculty);
    }
    @GetMapping(path = "{color}")
    public List<Faculty> filterByAge(@PathVariable String color){
        return facultyService.getFacultyByColor(color);
    }
}
