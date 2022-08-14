package com.example.pt04prak2072028jdbc.dao;

import com.example.pt04prak2072028jdbc.model.CategoryEntity;
import com.example.pt04prak2072028jdbc.util.HiberUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class CategoryDao implements DaoInterface<CategoryEntity> {

    @Override
    public List<CategoryEntity> getData() {
        List<CategoryEntity> cList;
        SessionFactory sf = new HiberUtil().getSessionFactory();
        Session s = sf.openSession();

        CriteriaBuilder bob = s.getCriteriaBuilder();
        CriteriaQuery q = bob.createQuery(CategoryEntity.class);
        Root<CategoryEntity> r = q.from(CategoryEntity.class);

//        Predicate p1 = bob.greaterThan(r.get("id"), 2);
//        Predicate p2 = bob.lessThan(r.get("id"), 6);
//        Predicate p3 = bob.and(p1, p2);
//        q.where(p3);

        cList = s.createQuery(q).getResultList();

        s.close();
        return cList;
    }

    @Override
    public void addData(CategoryEntity data) {
        SessionFactory sf = new HiberUtil().getSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        try {
            s.save(data);
            t.commit();
        } catch (Exception e) {
            t.rollback();
        }
        s.close();
    }

    @Override
    public void delData(CategoryEntity data) {
        SessionFactory sf = new HiberUtil().getSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        try {
            s.delete(data);
            t.commit();
        } catch (Exception e) {
            t.rollback();
        }
        s.close();
    }

    @Override
    public void updateData(CategoryEntity data) {
        SessionFactory sf = new HiberUtil().getSessionFactory();
        Session s = sf.openSession();
        Transaction t = s.beginTransaction();
        try {
            s.update(data);
            t.commit();
        } catch (Exception e) {
            t.rollback();
        }
        s.close();
    }
}
