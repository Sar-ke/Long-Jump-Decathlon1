package fr.stageLIS.long_jump_serveur.controller;

import fr.stageLIS.long_jump_serveur.DTO.GroupeDesDto;
import fr.stageLIS.long_jump_serveur.models.GroupeDes;
import fr.stageLIS.long_jump_serveur.services.GroupeDesService;
import fr.stageLIS.long_jump_serveur.wrapper.FreezeWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/GroupeDes")
public class GroupeDesController {


    private final GroupeDesService groupeDesService;
    @Autowired
    public GroupeDesController(GroupeDesService groupeDesService) {this.groupeDesService = groupeDesService;}


    @PutMapping("/create")
    public ResponseEntity<GroupeDesDto> createGroupe(@RequestBody int nbDes) {
        GroupeDesDto groupeDesDto = groupeDesService.convertToDto(groupeDesService.createGroupe(nbDes));
        return ResponseEntity.ok(groupeDesDto);
    }

    @GetMapping("/get")
    public ResponseEntity<GroupeDesDto> getGroupe(@RequestBody Long id) {
    try {
        GroupeDes groupeDes = groupeDesService.getGroupe(id);
        GroupeDesDto grpDesDto = groupeDesService.convertToDto(groupeDes);
        return ResponseEntity.ok(grpDesDto);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/update")
    public ResponseEntity<GroupeDesDto> updateGroupe(@RequestBody Long id, @RequestBody GroupeDesDto newGroupeDto) {
    try {
        GroupeDes groupeDes = groupeDesService.updateGroupe(id, groupeDesService.convertToEntity(newGroupeDto));
        return ResponseEntity.ok(groupeDesService.convertToDto(groupeDes));
    } catch (IllegalArgumentException e) {
        return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<GroupeDesDto> deleteGroupe(@RequestBody Long id) {
    try {
        groupeDesService.deleteGroupe(id);
        return ResponseEntity.ok().build();
    }
    catch (IllegalArgumentException e) {
        return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/throw")
    public ResponseEntity<GroupeDesDto> throwGroupe(@RequestBody Long id){

    try {
        GroupeDes groupeLance = groupeDesService.throwGroupe(id);
        return ResponseEntity.ok(groupeDesService.convertToDto(groupeLance));
    } catch (IllegalArgumentException e) {
        return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/freeze")
    public ResponseEntity<GroupeDesDto> freezeGroupe(@RequestBody FreezeWrapper freezeWrapper) {

    try {
        GroupeDes groupeDes = groupeDesService.freezeDeGroupe(freezeWrapper.getId(), freezeWrapper.getIdDe());
        return ResponseEntity.ok(groupeDesService.convertToDto(groupeDes));
    } catch (IllegalArgumentException e) {
        return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/unFreeze")
    public ResponseEntity<GroupeDesDto> unfreezeGroupe(@RequestBody FreezeWrapper freezeWrapper) {

        try {
            GroupeDes groupeDes = groupeDesService.unFreezeDeGroupe(freezeWrapper.getId(), freezeWrapper.getIdDe());
            return ResponseEntity.ok(groupeDesService.convertToDto(groupeDes));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
