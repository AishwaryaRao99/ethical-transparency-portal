package com.aishwarya.ethical.transparency_portal.modules.user.repository;

import org.springframework.stereotype.Repository;
import com.aishwarya.ethical.transparency_portal.modules.user.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

}
