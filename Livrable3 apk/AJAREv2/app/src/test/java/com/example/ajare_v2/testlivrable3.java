package com.example.ajare_v2;
import androidx.test.filters.LargeTest;
import org.junit.Test;
import static com.example.ajare_v2.SuccursaleCreate.addressIsValid;
import static com.example.ajare_v2.SuccursaleCreate.numberIsValid;
import static org.junit.Assert.*;
@LargeTest
public class testlivrable3 {
    @Test
    public void testNumberIsValid(){
        String valid= "111-111-1111";
        String non_valid= "1111";
        assertEquals(numberIsValid(valid),true);
        assertEquals(numberIsValid(non_valid),false);
    }
    @Test
    public void testAdressIsValid(){
        String valid= "K1N7V1";
        String valid1= "K1N 7V1";
        String non_valid= "KKKKKK";
        String non_valid1= "K14 klp";

        assertEquals(addressIsValid(valid),true);
        assertEquals(addressIsValid(valid1),true);//space
        assertEquals(addressIsValid(non_valid),false);//letter
        assertEquals(addressIsValid(non_valid1),false);//wrong format
    }
}
