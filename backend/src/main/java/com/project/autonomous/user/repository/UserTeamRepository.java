package com.project.autonomous.user.repository;

import com.project.autonomous.user.entity.UserTeam;
import com.project.autonomous.user.entity.UserTeamId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface UserTeamRepository extends CrudRepository<UserTeam, UserTeamId> {
    Optional<UserTeam> findByUserId(long userId);
    ArrayList<UserTeam> findAllByTeamId(long teamId);
}
