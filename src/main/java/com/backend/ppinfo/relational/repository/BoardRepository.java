package com.backend.ppinfo.relational.repository;

import com.backend.ppinfo.relational.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query(nativeQuery = true, value = "select exists(select 1 from board b where b.name = :name)")
    Boolean checkIfNameExists(@Param("name") String name);

}
