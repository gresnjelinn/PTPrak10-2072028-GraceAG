package com.example.pt04prak2072028jdbc.dao;

import com.example.pt04prak2072028jdbc.model.CategoryEntity;
import com.example.pt04prak2072028jdbc.model.ItemsEntity;
import com.example.pt04prak2072028jdbc.util.HiberUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class ItemsDao implements DaoInterface<ItemsEntity>  {

    @Override
    public List<ItemsEntity> getData() {
        List<ItemsEntity> iList;
        SessionFactory sf = new HiberUtil().getSessionFactory();
        Session s = sf.openSession();

        CriteriaBuilder bob = s.getCriteriaBuilder();
        CriteriaQuery q = bob.createQuery(ItemsEntity.class);
        q.from(ItemsEntity.class);

        iList = s.createQuery(q).getResultList();

        s.close();
        return iList;
    }

    @Override
    public void addData(ItemsEntity data) {
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
    public void delData(ItemsEntity data) {
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
    public void updateData(ItemsEntity data) {
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
