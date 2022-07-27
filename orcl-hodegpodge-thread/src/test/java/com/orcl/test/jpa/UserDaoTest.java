package com.orcl.test.jpa;

/**
 * @description:
 * @author: orcl
 * @since: 2022-07-20 22:11
 * @history: 2022-07-20 22:11 created by orcl
 */
public class UserDaoTest {

    public static void main(String[] args) {

        UserDao userDao = new UserDao();
        int updated = userDao.add(new User("orcl", 24));

    }

}
