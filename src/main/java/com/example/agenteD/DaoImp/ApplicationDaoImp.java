package com.example.agenteD.DaoImp;

import com.example.agenteD.Util.Values;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ApplicationDaoImp extends Values implements Runnable {

    @Override
    public void run() {

    }
}
