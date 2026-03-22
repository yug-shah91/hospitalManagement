package com.example.hospitalManagement.repository;

import com.example.hospitalManagement.dto.BloodGroupCountResponseEntity;
import com.example.hospitalManagement.entity.Patient;
import com.example.hospitalManagement.entity.type.BloodGroupType;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
    // ye interface isliye banate hai so that hum query kar sake Patient ke sath
    // bas iske thorugh hum query run kar sakte hai

    Patient findByName(String name);

    List<Patient> findByBirthDateOrEmail(LocalDate birthDate , String email);

    List<Patient> findByBirthDateBetween(LocalDate startDate,LocalDate endDate);

    List<Patient> findByNameContaining(String query);

    @Query("SELECT p FROM Patient p where p.bloodGroup = ?1")
    List<Patient> findByBloodGroup(@Param("bloodGroup") BloodGroupType bloodGroup); // Patient entity you probably have something like:private BloodGroupType bloodGroup;
    // So the repository method must accept the same type: BloodGroupType bloodGroup  Not a String.

    @Query("SELECT p FROM Patient p where p.birthDate > :birthDate")
    List<Patient> findByBornAfterDate(@Param("birthDate") LocalDate birthDate);

    @Query("SELECT new com.example.hospitalManagement.dto.BloodGroupCountResponseEntity" +
            "( p.bloodGroup , Count(p) )FROM Patient p GROUP BY p.bloodGroup")
        // GROUP BY and group by same hai
  //  List<Object[]> countBloodGroupType();
    List<BloodGroupCountResponseEntity> countEachBloodGroupType();

    @Query(value = "select * from patient",nativeQuery = true)
 //   List<Patient> findAllPatiens(Pageable pageable);
    Page<Patient> findAllPatiens(Pageable pageable);
    //agar kuch modify karna hai jaise naam n=change karna hai

    @Transactional
    @Modifying
    @Query("UPDATE Patient p SET p.name = :name where p.id = :id")
    int updateNameWithId(@Param("name") String name,@Param("id") Long id);

   // @Query("SELECT p FROM Patient p LEFT JOIN FETCH p.appointments a LEFT JOIN FETCH a.doctor")
    @Query("SELECT p FROM Patient p LEFT JOIN FETCH p.appointments ")
    List<Patient> findAllPatientWithAppointment();
}
