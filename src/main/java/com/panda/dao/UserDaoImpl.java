package com.panda.dao;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.panda.pojo.User;

@Repository
public class UserDaoImpl implements UserDao {

    @Resource
    SessionFactory sessionFactory;

    /**
     * @category 获取当前的session
     * @return
     */
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public User login(String account, String pwd) {
        Session session = getCurrentSession();
        Query query = session.createQuery(
                "from " + User.class.getName() + " where account='" + account + "' and pwd='" + pwd + "'");
        User user = (User) query.uniqueResult();
        return user;
    }

    public User getUserById(String account) {
        Session session = getCurrentSession();
        Query query = session.createQuery(
                "from " + User.class.getName() + " where account='" + account + "'");
        User user = (User) query.uniqueResult();
        return user;
    }

}
