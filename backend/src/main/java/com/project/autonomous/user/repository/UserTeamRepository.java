package com.project.autonomous.user.repository;

import com.project.autonomous.user.entity.UserTeam;
import com.project.autonomous.user.entity.UserTeamId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTeamRepository extends CrudRepository<UserTeam, UserTeamId> {
}
