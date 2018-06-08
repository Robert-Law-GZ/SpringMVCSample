package com.robert.dao;

import com.robert.entity.EmployeeEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Cacheable("findAll")
    @Transactional
    public List<EmployeeEntity> findAll() {
        Session session = sessionFactory.getCurrentSession();
        String hql="from EmployeeEntity";
        Query query = session.createQuery(hql);
        return  query.getResultList();
    }

    @CacheEvict(value = "findAll",allEntries = true)
    @Transactional
    public void add(EmployeeEntity employeeEntity){
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(employeeEntity);
    }

    @Transactional
    public void addWithHT(EmployeeEntity employeeEntity){
        hibernateTemplate.save(employeeEntity);
    }

}
