package pl.schoolmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.schoolmanager.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    @Modifying
    @Query("UPDATE UserRole ur SET username = :username WHERE user_id = :user_id")
    void updateWithUsernameByUserId(@Param("user_id") long user_id, @Param("username") String username);

    List<UserRole> findAllBySchoolIdAndEnabledIsFalse(long schoolId);
    UserRole findOneByUserRoleAndUserIdAndSchoolId(String userrole, long userId, long schoolId );
    
}