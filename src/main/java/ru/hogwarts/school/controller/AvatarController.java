package ru.hogwarts.school.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.FacultyService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("avatar")
public class AvatarController {
    private final AvatarService avatarService;

    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@RequestParam MultipartFile avatar) throws IOException {
        if (avatar.getSize() >= 1024 * 300) {
            return ResponseEntity.badRequest().body("File is too big");
        }
        avatarService.saveAvatar(avatar);

        return ResponseEntity.ok().build();


    }

    @GetMapping(value = "{id}/avatar-from-db")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable Long id) {
        logger.error("There is no such file with id = " + id);
        Avatar result = avatarService.findAvatar(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(result.getMediaType()));
        headers.setContentLength(result.getData().length);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(result.getData());
    }

    @GetMapping(value = "/{id}/avatar-from-file")
    public void downloadAvatar(@PathVariable Long id, HttpServletResponse response) throws IOException {
        logger.error("There is no such file with id = " + id);
        Avatar avatar = avatarService.findAvatar(id);
        Path path = Path.of(avatar.getFilePath());
        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream()) {
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            is.transferTo(os);
        }
    }

    @GetMapping
    public ResponseEntity<Page<Avatar>> getAvatarsList(@RequestParam (required = false, defaultValue = "1") Integer page ,
                                                       @RequestParam (required = false, defaultValue = "5") Integer limit) {
        return ResponseEntity.ok(avatarService.getAllAvatars(page, limit));
    }
}
