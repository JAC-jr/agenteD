package com.example.MonitorAgent.Repository;

import com.example.MonitorAgent.Entity.ServicioRegistry;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.imageio.spi.ServiceRegistry;

public interface ServiceRegistryRepository extends JpaRepository<ServicioRegistry, Integer> {

}
