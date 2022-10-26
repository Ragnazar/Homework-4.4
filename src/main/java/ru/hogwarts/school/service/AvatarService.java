package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.repository.AvatarRepository;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
@Transactional
public class AvatarService {
    @Value("${student.avatar.dir.path}")
    private String avatarsDir;

    private final AvatarRepository avatarRepository;

    private static final Logger logger = LoggerFactory.getLogger(AvatarService.class);

    public AvatarService(AvatarRepository avatarRepository, StudentService studentService) {
        this.avatarRepository = avatarRepository;
    }

    public void saveAvatar(MultipartFile file) throws IOException {
        logger.error("There is no such directory to save");
        logger.info("Was invoked method for save new picture");
        Path filePath = Path.of(avatarsDir, file.getOriginalFilename());
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }

        Avatar avatar = new Avatar();
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setData(file.getBytes());
        avatarRepository.save(avatar);
    }

    public Avatar findAvatar(Long id) {
        logger.info("Was invoked method for search the avatar by id");
        return avatarRepository.findById(id).orElse(new Avatar());
    }


    public Page<Avatar> getAllAvatars(Integer page, Integer limit) {
        logger.info("Was invoked method for show an limited avatars list");
        PageRequest pageRequest = PageRequest.of(page - 1, limit);
        return avatarRepository.findAll(pageRequest);
    }

    public Avatar editAvatar(Avatar avatar) {
        logger.info("Was invoked method for edit the avatar");
        return avatarRepository.save(avatar);
    }

    public void deleteAvatar(Long id) {
        logger.info("Was invoked method for delete the avatar");
        avatarRepository.deleteById(id);
    }
}
