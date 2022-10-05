package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.hogwarts.school.model.Faculty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class FacultyService {
    public HashMap<Long, Faculty> faculties = new HashMap<>();
    private long lastId = 0;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(++lastId);
        faculties.put(lastId, faculty);
        return faculty;
    }

    public Faculty findFaculty(long id) {
        Faculty faculty = faculties.get(id);
        if (faculty == null) {
            throw new NotFoundException("Такого факультета не существует");
        }
        return faculty;
    }

    public Faculty editFaculty(Faculty faculty) {
        long id = faculty.getId();
        Faculty tar = faculties.get(id);
        if (tar == null) {
            throw new NotFoundException("Такого факультета не существует");
        }
        faculties.put(id, faculty);
        return faculty;
    }

    public Faculty deleteFaculty(long id) {
        Faculty faculty = faculties.get(id);
        if (faculty == null) {
            throw new NotFoundException("Такого факультета не существует");
        }
        return faculties.remove(id);
    }

    public List<Faculty> getFacultyByColor(String color) {
        List<Faculty> tmp = new ArrayList<>();
        for (long i = 1; i <= faculties.size(); i++) {
            if (faculties.get(i) == null){
                i++;
            }
            if (Objects.equals(faculties.get(i).getColor(), color)) {
                tmp.add(faculties.get(i));
            }
        }
        return tmp;
    }
}
