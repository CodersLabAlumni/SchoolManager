package pl.schoolmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.schoolmanager.entity.SchoolAdmin;

public interface SchoolAdminRepository extends JpaRepository<SchoolAdmin, Long> {

}