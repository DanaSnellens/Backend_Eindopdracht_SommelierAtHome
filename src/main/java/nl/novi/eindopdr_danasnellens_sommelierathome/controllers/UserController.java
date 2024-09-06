/*
package nl.novi.eindopdr_danasnellens_sommelierathome.controllers;

import org.hibernate.query.SelectionQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> getUserAuthorities(@PathVariable("username") String userName) {
        return ResponseEntity.ok().body(userService.getAuthorities(userName));
    }

    @PostMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> addUserAuthority(@PathVariable("username") String userName, @RequestBody Map<>)
}
*/
