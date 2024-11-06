package nl.novi.eindopdr_danasnellens_sommelierathome.controllers;

import jakarta.validation.Valid;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.input.RoleInputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.dtos.output.RoleOutputDto;
import nl.novi.eindopdr_danasnellens_sommelierathome.services.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity <Set<RoleOutputDto>> getAllRoles() {
        return ResponseEntity.ok(roleService.getRoles());
    }

/*    @PostMapping
    public ResponseEntity <RoleOutputDto> createRole(@Valid @RequestBody RoleInputDto roleInputDto) {
        RoleOutputDto roleOutputDto = roleService.createRole(roleInputDto);
        return ResponseEntity.ok(roleOutputDto);
    }*/
}
