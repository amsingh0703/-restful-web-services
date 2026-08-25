package com.rest_webservices.RestFul_web_services.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonSBIController {

//    @GetMapping(value = "/{version}/Personsbi",version = "1.0.0")
//    public PersonV1 getFirstVersionOfPerson(){
//        return new PersonV1("Bob Charli");
//    }
//
//    @GetMapping(value = "/{version}/Personsbi",version = "2.0.0")
//    public PersonV2 getSecondVersionOfPerson(){
//        return new PersonV2(new Name("Bob", "Charli"));
//    }
//
//    @GetMapping(path = "/Personsbi",version = "1.0.0")
//    public PersonV1 getFirstVersionOfPersonRequestParameter(){
//        return new PersonV1("Bob Charli");
//    }
//
//    @GetMapping(path = "/Personsbi",version = "2.0.0")
//    public PersonV2 getSecondVersionOfPersonRequestParameter(){
//        return new PersonV2(new Name("Bob", "Charli"));
//    }
//
//    @GetMapping(path = "/Personsbi/header",headers = "x-api-version=1")
//    public PersonV1 getFirstVersionOfPersonRequestHeader(){
//        return new PersonV1("Bob Charli");
//    }
//
//    @GetMapping(path = "/Personsbi/header",headers = "x-api-version=2")
//    public PersonV2 getSecondVersionOfPersonRequestHeader(){
//        return new PersonV2(new  Name("Bob", "Charli"));
//    }
//
//    @GetMapping(path = "/Personsbi/accept",produces = "application/vnd.company.app-v1+json")
//    public PersonV1 getFirstVersionOfPersonAcceptHeader(){
//        return new PersonV1("Bob Charli");
//    }
}
