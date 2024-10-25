package com.xa.backend342.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xa.backend342.entities.OrderHeader;
@Repository
public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long>{
    
}
