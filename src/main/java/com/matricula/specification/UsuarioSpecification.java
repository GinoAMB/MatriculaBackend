package com.matricula.specification;

import com.matricula.entity.UsuarioEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UsuarioSpecification {

    public static Specification<UsuarioEntity> filtrar(
            String search,
            String rol,
            Boolean estado
    ) {
        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            // Buscar por nombre o correo
            if (search != null && !search.isBlank()) {
                Predicate nombre = cb.like(
                        cb.lower(root.get("persona").get("nombre")),
                        "%" + search.toLowerCase() + "%"
                );

                Predicate apellidos = cb.like(
                        cb.lower(root.get("persona").get("apellidos")),
                        "%" + search.toLowerCase() + "%"
                );

                Predicate correo = cb.like(
                        cb.lower(root.get("correo")),
                        "%" + search.toLowerCase() + "%"
                );

                predicates.add(cb.or(nombre, apellidos, correo));
            }

            // Filtrar por rol
            if (rol != null && !rol.isBlank()) {
                predicates.add(
                        cb.equal(root.get("rol").get("nombre"), rol)
                );
            }

            // Filtrar por estado
            if (estado != null) {
                predicates.add(
                        cb.equal(root.get("estado"), estado)
                );
            }

            // Orden descendente
            query.orderBy(cb.desc(root.get("idUsuario")));

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}