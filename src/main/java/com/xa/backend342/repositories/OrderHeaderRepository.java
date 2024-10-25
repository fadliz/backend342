package com.xa.backend342.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xa.backend342.entities.OrderHeader;
@Repository
public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long>{
    // ORDER BY o.id DESC and the use of Optional<String>. 
    // The query is ordered by id in descending order, 
    // and Spring Data JPA will automatically limit the result 
    // to one when you're using Optional<String> or String
    @Query("SELECT o.reference FROM OrderHeader o WHERE o.reference LIKE :pattern ORDER BY o.id DESC LIMIT 1")
    Optional<String> findLatestReference(@Param("pattern") String pattern);
}
