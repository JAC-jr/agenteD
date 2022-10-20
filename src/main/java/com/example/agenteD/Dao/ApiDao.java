package com.example.agenteD.Dao;

import com.example.agenteD.Util.Values;

import java.util.List;

public interface ApiDao {


    List <Values> findById();

   void save();
}
