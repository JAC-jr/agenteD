package com.example.agenteD.DaoImp;

import com.example.agenteD.Util.Values;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class ApiDaoImp implements Runnable{

    @PersistenceContext
    EntityManager entityManager;

    public List<Values> findById{
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void save(Out out){
        entityManager.merge(out);
    }
    @Override
    public void run() {

    }
}
