package com.example.springbootlearning.controllerandMapping;

import org.springframework.stereotype.Controller;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

//@Controller
//public class UserController {
//

/**
 * @Controller : It indicates the class is responsible for handling the incoming HTTPS requests.
 *
 * ResponseBody annotation used it tells that the return type of the method is http response body
 * not the view and send as it is.
 */
//    @RequestMapping(path = "/fetchUser", method = RequestMethod.GET)
//    @ResponseBody
//    public String getUserDetails(){
//        return "fetch return user details";
//    }
//
//    @RequestMapping(path = "/fetchUser", method = RequestMethod.POST)
//    @ResponseBody
//    public String saveUserDetails(){
//        return "fetch return user details";
//    }
//}


//

/**
 *
 * RestController = Controller + ResponseBody
 * @RestController annotation used internally it have annotation with @controller and @responseBody
 * we can do this using the requestMapping annotation
 * @GetMapping and @PostMapping
 * code become more readable interanlly specifically set  the method type
 */

@RestController
@RequestMapping(value = "/api")
public class UserController {

    /**
     * @initBinder annotation used for when required some modification in request params value
     * before pass into the method.
     * It invoked before the controller method called.
     *
     * To do this we have to register the custom editor we created using the @initBinder annotation
     * @return
     */


    @InitBinder
    protected void initBinder(WebDataBinder binder){
        System.out.println("check init binder called");
        binder.registerCustomEditor(Date.class, "dob", new DatePropertyEditor("dd/MM/yyyy"));
        binder.registerCustomEditor(String.class, "firstname", new StringDataPropertyEditor());
    }




    @GetMapping(path = "/fetchUser")
    public String getUserDetails(){
        return "fetch return user details";
    }

    @PostMapping(path = "/fetchUser")
    public String saveUserDetails(){
        return "fetch return user details";
    }

    /**
     * @RequestParam: annotation
     * Used to bind , request params to controller method parameter
     * eg.
     *
     * http://localhost:8080/api/fetchUserByParams?firstname=SUMIT&lastname=SAHU&age=27
     */

    @GetMapping(path = "fetchUserByParams")
    public String fetchUserDetailsByRequestParams(@RequestParam(name = "firstname") String firstName,
                                                  @RequestParam(name = "lastname", required = false) String lastName,
                                                  @RequestParam(name ="age") int age
                                                  ){
        return "fetching the user by params is : " +firstName + " " + lastName +" and age is  " + age;
    }

    /**
     *  http://localhost:8080/api/fetchUserByDobAndName?firstname=SUMIT&dob=10/07/1997
     */


    @GetMapping(path = "fetchUserByDobAndName")
    public String fetchUserDetailByDobAndName(@ModelAttribute User userRequest){
        System.out.println("Date of birth :" + userRequest.getDob() );
        return "fetching the user by params is : " +userRequest.getFirstname() + "  dateOfBirth "+ userRequest.getDob();
    }
}

