package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;

@Service
public class FacultyService {

    private FacultyRepository facultyRepository;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(++lastId);
        faculties.put(lastId, faculty);
        return faculty;
    }

    public Faculty findFaculty(long id) {
        return faculties.get(id);
    }

    public Faculty editFaculty(Faculty faculty) {
        long id = faculty.getId();
        faculties.put(id, faculty);
        return faculty;
    }

    public Faculty deleteFaculty(long id) {
        return faculties.remove(id);
    }

    public Collection<Faculty> getFacultyByColor(String color) {
        Collection<Faculty> tmp = new ArrayList<>();
        for (long i = 1; i <= faculties.size(); i++) {
            if (faculties.get(i) == null) {
                i++;
            }
            if (Objects.equals(faculties.get(i).getColor(), color)) {
                tmp.add(faculties.get(i));
            }
        }
        return tmp;
    }

    public Collection<Faculty> getAll() {
        return faculties.values();
    }
}
