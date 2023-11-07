package com.robotdreams.rbfinalproject.repository;

import com.robotdreams.rbfinalproject.model.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Long> {

    @Query("select c from CurrencyEntity c where c.source=:source and c.target=:target")
    CurrencyEntity findBySourceAndTarget(@Param("source") String source,
                                         @Param("target") String target);

}
