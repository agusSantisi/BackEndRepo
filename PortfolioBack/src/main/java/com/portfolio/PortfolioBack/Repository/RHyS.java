
package com.portfolio.PortfolioBack.Repository;

import com.portfolio.PortfolioBack.Entity.HyS;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RHyS extends JpaRepository<HyS,Integer>{
    Optional<HyS> findByTecnologia(String tecnologia);
    public boolean existsByTecnologia(String tecnologia);
}
