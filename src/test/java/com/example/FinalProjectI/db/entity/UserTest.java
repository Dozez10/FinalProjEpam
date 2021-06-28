package com.example.FinalProjectI.db.entity;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
class UserTest {
    private User user;
    @BeforeAll
    static void someMethodBeforeAll() throws SQLException {

    }
    @BeforeEach
    void initMocks(){
        user = new User();
    }
    @AfterAll
    static void someMethodAfterAll(){

    }

    @Test
    void getUserId() {
        user.setUserId(321321312);
        assertEquals(321321312,user.getUserId());
    }

    @Test
    void setUserId() {
        user.setUserId(321321312);
        assertEquals(321321312,user.getUserId());
    }

    @Test
    void getUserType() {
        user.setUserType("asdasd");
        assertEquals("asdasd",user.getUserType());
    }

    @Test
    void setUserType() {
        user.setUserType("asdasd");
        assertEquals("asdasd",user.getUserType());
    }

    @Test
    void getUserPassword() {
        user.setUserPassword("asdasdad");
        assertEquals("asdasdad",user.getUserPassword());
    }

    @Test
    void setUserPassword() {
        user.setUserPassword("asdasdad");
        assertEquals("asdasdad",user.getUserPassword());
    }

    @Test
    void getUserEmail() {
        user.setUserEmail("asdasdasasda");
        assertEquals("asdasdasasda",user.getUserEmail());
    }

    @Test
    void setUserEmail() {
        user.setUserEmail("asdasdasasda");
        assertEquals("asdasdasasda",user.getUserEmail());
    }

    @Test
    void getUserLogin() {
        user.setUserLogin("asdasadwadsadzxasdsa");
        assertEquals("asdasadwadsadzxasdsa",user.getUserLogin());
    }

    @Test
    void setUserLogin() {
        user.setUserLogin("asdasadwadsadzxasdsa");
        assertEquals("asdasadwadsadzxasdsa",user.getUserLogin());
    }

    @Test
    void testEquals() {
        User user = new User();
        user.setUserId(321312);
        User user2 = new User();
        user2.setUserId(321312);
        assertEquals(user2,user);
    }

    @Test
    void testHashCode() {
        User user = new User();
        user.setUserId(321312);
        User user2 = new User();
        user2.setUserId(321312);
        assertEquals(user2.hashCode(),user.hashCode());
    }

    @Test
    void testToString() {
        User user = new User();
        user.setUserId(321312);
        User user2 = new User();
        user2.setUserId(321312);
        assertNotEquals(user2.toString(),user.toString());
    }
}