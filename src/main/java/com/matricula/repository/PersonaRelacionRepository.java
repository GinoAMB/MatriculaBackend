package com.matricula.repository;

import com.matricula.entity.PersonaRelacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonaRelacionRepository extends JpaRepository<PersonaRelacionEntity, Integer> {

    List<PersonaRelacionEntity> findByAlumno_IdPersona(Integer idAlumno);

}
