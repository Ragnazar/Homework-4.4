package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.service.AvatarService;

@RestController
@RequestMapping("avatar")
public class AvatarController {
    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @PostMapping
    public Avatar uploadAvatar(@RequestBody Avatar avatar) {
        return avatarService.saveAvatar(avatar);
    }

    @GetMapping(path = "{id}/local")
    public ResponseEntity<Avatar> getAvatarFromLocal(@PathVariable Long id) {
        Avatar avatar = avatarService.findAvatar(id);
        if (avatar == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(avatar);
    }

    @GetMapping(path = "{id}/source")
    public ResponseEntity<Avatar> getAvatarFromSource(@PathVariable Long id) {
        Avatar avatar = avatarService.findAvatar(id);
        if (avatar == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(avatar);
    }
}
