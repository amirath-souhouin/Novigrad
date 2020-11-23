package com.example.ajare_v2;
import androidx.test.filters.LargeTest;
import org.junit.Test;
import static com.example.ajare_v2.EmailActivity.checkPasswordValidity;
import static com.example.ajare_v2.HomeActivity.checkUserRole;
import static com.example.ajare_v2.EmailActivity.emailIsEmpty;
import static com.example.ajare_v2.CreateActivity.emailIsValid;
import static org.junit.Assert.*;
@LargeTest
public class test {
    @Test
    public void testcheckUserRole(){
        String emailEmployee = "admin@novigrad.com";
        String emailClient = "ab@c.com";
        assertEquals(checkUserRole(emailEmployee),true);
        assertEquals(checkUserRole(emailClient),false);
    }
    @Test
    public void testPasswordCheck(){
        String password = "abc";
        assertEquals(checkPasswordValidity(password),false);
    }
    @Test
    public void testemptyCredential(){
        String email = "";
        String email1 = "er";
        assertEquals(emailIsEmpty(email),true);
        assertEquals(emailIsEmpty(email1),false);
    }
    @Test
    public void testemailIsvalid(){
        String emailFalse = "/ty@nov.com";
        String emailEmployee = "admin@novigrad.com";
        assertEquals(emailIsValid(emailFalse),false);
        assertEquals(emailIsValid(emailEmployee),true);
    }
    @Test
    public void testsetServiceName(){
        Service service = new Service("COVID TEST","ID");
        service.setServiceName("BOOK");
        assertEquals(service.getServiceName(),"BOOK");
    }
}
