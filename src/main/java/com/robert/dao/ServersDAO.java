package com.robert.dao;

import com.robert.entity.ServersEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ServersDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public List<ServersEntity> findAll(){
        Session session = sessionFactory.getCurrentSession();
        List<ServersEntity> userList = new ArrayList<ServersEntity>();
        String hsql="from ServersEntity";
        Query query = session.createQuery(hsql);
        userList = query.getResultList();
        return userList;
    }

}
