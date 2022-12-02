package com.example.MonitorAgent.Repository;

import com.example.MonitorAgent.Entity.Servicio;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Servicio, Integer> {

    List<Servicio> findAllByApplicationId(Integer applicationId);
    List<Servicio> getServciceNameByApplicationId(Integer applicationId);

}