package com.example.agenteD.Repository;

import com.example.agenteD.Entity.Api;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ApiRepository extends JpaRepository<Api, Integer> {

}