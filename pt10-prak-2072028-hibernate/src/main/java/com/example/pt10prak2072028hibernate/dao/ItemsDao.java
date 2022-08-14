package com.example.pt10prak2072028hibernate.dao;

import com.example.pt10prak2072028hibernate.model.CategoryEntity;
import com.example.pt10prak2072028hibernate.model.ItemsEntity;
import com.example.pt10prak2072028hibernate.util.HiberUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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

    }

    @Override
    public void delData(ItemsEntity data) {

    }

    @Override
    public void updateData(ItemsEntity data) {

    }
}
