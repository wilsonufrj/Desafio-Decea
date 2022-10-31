package com.br.decea.Controller;

import com.br.decea.DTO.ProjectDTO;
import com.br.decea.DTO.ProjectUpdateDTO;
import com.br.decea.Service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author wilson
 */
@RestController
@RequestMapping(path = "/projects")
public class ProjectController {

    @Autowired
    private IProjectService projectService;

    @PostMapping
    public ResponseEntity create(@RequestBody ProjectDTO projectDTO) throws Exception {
        return ResponseEntity.ok(projectService.create(projectDTO));
    }

    @GetMapping
    public ResponseEntity projects(@RequestHeader(value="username") String username ) throws Exception {
        return ResponseEntity.ok(projectService.getAll(username));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getProject(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(projectService.get(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateProject(@PathVariable Long id, @RequestBody ProjectUpdateDTO projectRequestDTO,@RequestHeader(value="username") String username) {
        try {
            return ResponseEntity.ok(projectService.update(id, projectRequestDTO,username));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping(value = "/{id}/done")
    public ResponseEntity finishedProject(@PathVariable Long id, @RequestHeader(value="username") String username) {
        try {
            projectService.doneProject(id,username);
            return ResponseEntity.status(HttpStatus.OK).body("Project finished");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}") //OK
    public ResponseEntity deleteProject(@PathVariable Long id,@RequestHeader(value="username") String username) throws Exception {
        projectService.delete(id,username);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
