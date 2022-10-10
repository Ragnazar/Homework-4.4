package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.repository.AvatarRepository;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
public class AvatarService {
    private final AvatarRepository avatarRepository;

    public AvatarService(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    public Avatar saveAvatar(Avatar avatar){
        return avatarRepository.save(avatar);
    }

    public Avatar findAvatar(Long id){
        return avatarRepository.findById(id).orElse(null);
    }

    @Transactional
    public Collection<Avatar> getAllAvatars() {
        return avatarRepository.findAll();
    }

    public Avatar editAvatar(Avatar avatar){
        return avatarRepository.save(avatar);
    }

    public void deleteAvatar(Long id) {
        avatarRepository.deleteById(id);
    }
}
