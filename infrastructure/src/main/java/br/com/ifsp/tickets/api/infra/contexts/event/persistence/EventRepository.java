package br.com.ifsp.tickets.api.infra.contexts.event.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<EventJpaEntity, UUID> {

    Page<EventJpaEntity> findAll(Specification<EventJpaEntity> where, Pageable page);
}
