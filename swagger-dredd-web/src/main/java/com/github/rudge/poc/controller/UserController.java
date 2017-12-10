package com.github.rudge.poc.controller;

import com.github.rudge.poc.domain.ResponseError;
import com.github.rudge.poc.domain.User;
import com.github.rudge.poc.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@Api(value= "/user", description = "Operations about User")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Create User", code = 201)
    @ApiResponses(value = { @ApiResponse(code = 201, message = "OK"),
                            @ApiResponse(code = 400, message = "Bad Request", response = ResponseError.class)})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    public void create(@RequestBody User user) {
        userService.save(user);
    }

    @ApiOperation(value = "Update User", code = 204)
    @ApiResponses(value = { @ApiResponse(code = 204, message = "OK"),
                            @ApiResponse(code = 400, message = "Bad Request", response = ResponseError.class)})
    @ResponseStatus(NO_CONTENT)
    @PatchMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    public void update(@RequestBody User user) {
        userService.save(user);
    }

    @ApiOperation(value = "Delete User by id", code = 204)
    @ApiImplicitParam(defaultValue = "{id}", required = true, name = "id", paramType = "path")
    @ApiResponses(value = { @ApiResponse(code = 204, message = "OK"),
                            @ApiResponse(code = 400, message = "Bad Request", response = ResponseError.class)})
    @ResponseStatus(NO_CONTENT)
    @DeleteMapping(path = "/{id}")
    public void remove(@PathVariable @NotNull Long id) {
        userService.remove(id);
    }

    @ApiOperation(value = "Return User by Id", response = User.class, code = 200, produces = APPLICATION_JSON_UTF8_VALUE)
    @ApiImplicitParam(defaultValue = "{id}", required = true, name = "id", paramType = "path")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = User.class),
                            @ApiResponse(code = 400, message = "Bad Request", response = ResponseError.class)})
    @ResponseBody
    @GetMapping(path = "/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    public User get(@PathVariable @NotNull Long id) {
        return userService.get(id);
    }

    @ApiOperation(value = "Return all Users", response = User.class, responseContainer = "list", code = 200)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = User.class, responseContainer = "list")})
    @ResponseBody
    @GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<List<User>>(userService.getAll(), OK);
    }
}