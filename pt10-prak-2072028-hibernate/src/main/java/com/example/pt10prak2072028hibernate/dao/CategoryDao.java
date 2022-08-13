package com.example.pt10prak2072028hibernate.dao;

import com.example.pt10prak2072028hibernate.model.CategoryEntity;
import com.example.pt10prak2072028hibernate.util.HiberUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class CategoryDao implements DaoInterface<CategoryEntity> {

    @Override
    public List<CategoryEntity> getData() {
        List<CategoryEntity> cList;
        SessionFactory sf = new HiberUtil().getSessionFactory();
        Session s = sf.openSession();

        CriteriaBuilder bob = s.getCriteriaBuilder();
        CriteriaQuery q = bob.createQuery(CategoryEntity.class);
        q.from(CategoryEntity.class);

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
        s.delete(data);
        s.close();
    }

    @Override
    public void updateData(CategoryEntity data) {
        SessionFactory sf = new HiberUtil().getSessionFactory();
        Session s = sf.openSession();
        s.update(data);
        s.close();
    }
}
