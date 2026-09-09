package com.matricula.specification;

import com.matricula.entity.MatriculaEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class MatriculaSpecification {

    public static Specification<MatriculaEntity> filtrar(
            String search,
            String nivel,
            String grado,
            String seccion
    ) {

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            // Buscar por nombre o apellido del alumno
            if (search != null && !search.isBlank()) {

                String searchLower = "%" + search.toLowerCase() + "%";

                Predicate nombreCompleto = cb.like(
                        cb.lower(
                                cb.concat(
                                        cb.concat(
                                                root.get("alumno").get("nombre"),
                                                " "
                                        ),
                                        root.get("alumno").get("apellidos")
                                )
                        ),
                        searchLower
                );

                Predicate nombre = cb.like(
                        cb.lower(root.get("alumno").get("nombre")),
                        searchLower
                );

                Predicate apellidos = cb.like(
                        cb.lower(root.get("alumno").get("apellidos")),
                        searchLower
                );

                Predicate documento = cb.like(
                        cb.lower(root.get("alumno").get("numeroDocumento")),
                        searchLower
                );

                predicates.add(
                        cb.or(nombreCompleto, nombre, apellidos, documento)
                );
            }

            // Filtrar por nivel
            if (nivel != null && !nivel.isBlank()) {

                predicates.add(
                        cb.equal(
                                root.get("seccion")
                                        .get("grado")
                                        .get("nivel")
                                        .get("nombre"),
                                nivel
                        )
                );
            }

            // Filtrar por grado
            if (grado != null && !grado.isBlank()) {

                predicates.add(
                        cb.equal(
                                root.get("seccion")
                                        .get("grado")
                                        .get("nombre"),
                                grado
                        )
                );
            }

            // Filtrar por sección
            if (seccion != null && !seccion.isBlank()) {

                predicates.add(
                        cb.equal(
                                root.get("seccion")
                                        .get("nombre"),
                                seccion
                        )
                );
            }

            // Orden descendente
            query.orderBy(
                    cb.desc(root.get("idMatricula"))
            );

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}