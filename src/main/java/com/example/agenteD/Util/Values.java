package com.example.agenteD.Util;

import com.example.agenteD.Dao.ApiDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class Values implements ApiDao {
    @PersistenceContext
    EntityManager entityManager;

    String ColumnName;
    String TableName;

    public List<Values> getApi(){



        String query="SELECT"+ ColumnName +"FROM"+ TableName +"";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Values> findById() {
        return null;
    }

    @Override
    public void save() {
    }

}