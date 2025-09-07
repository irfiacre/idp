package com.example.idp.controller;
import lombok.RequiredArgsConstructor;
import com.example.idp.requests.ClientRegistrationRequest;
import com.example.idp.requests.UserRegistrationRequest;
import com.example.idp.service.client.ClientService;
import com.example.idp.service.resourceowner.ResourceOwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {
private  final ResourceOwnerService resourceOwnerService;
private  final ClientService clientService;

    @PostMapping("/user")
    public ResponseEntity <Void >addUsers(@RequestBody UserRegistrationRequest request){
        resourceOwnerService.createResourceOwner(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("/client")
    public  ResponseEntity <Void>  registerClient(@RequestBody ClientRegistrationRequest request){
        clientService.registerClient(request);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
