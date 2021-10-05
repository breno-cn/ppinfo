package com.backend.ppinfo.relational.repository;

import com.backend.ppinfo.relational.entity.BoardUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardUserRepository extends JpaRepository<BoardUser, Long> {

    Optional<BoardUser> findByUsername(String username);

    @Query(nativeQuery = true, value = "select exists(select 1 from board_user bu where bu.username = :username)")
    Boolean checkIfUsernameExists(@Param("username") String username);

    @Query(nativeQuery = true, value = "select exists(select 1 from board_user bu where bu.email = :email);")
    Boolean checkIfEmailExists(@Param("email") String email);

}
