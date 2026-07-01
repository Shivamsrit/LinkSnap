package com.url.LinkSnap.controller;


import com.url.LinkSnap.dto.requestdto.UserRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/User")
public class UserController {

    @GetMapping
    public String getUser() {

    }

    // @RequestParam
    @GetMapping("/details")
    public String index(@RequestParam String firstName, @RequestParam String lastName) {
        return "Get Api - 2 "+firstName+" "+lastName;
    }


    // @RequestBody
    @PostMapping(path = "/post")
    public String postUser(@RequestBody UserRequestDto userRequestDto ) {
        return "Post Api " + userRequestDto.getName()+" "
                +userRequestDto.getPassword()+
                " "+userRequestDto.getRole()+
                " "+userRequestDto.getEmail();
    }

    @PutMapping(path = "/put")
    public String putUser(){
        return "Put Api";
    }
    //
    @PatchMapping(path = "/{name}")
    public String patchUser(@PathVariable String name ) {
        return "Patch Api "+name+" ";
    }

    @DeleteMapping(path = "delete")
    public String deleteUser() {
        return "Delete Api";
    }


}
