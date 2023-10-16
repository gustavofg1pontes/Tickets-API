package br.com.ifsp.tickets.api.infra.contexts.guest.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;
import java.util.UUID;

public interface GuestRepository extends JpaRepository<GuestJpaEntity, UUID> {
    Page<GuestJpaEntity> findAll(Specification<GuestJpaEntity> where, Pageable page);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM cv_guests WHERE event_id = :#{#eventid}"
    )
    Set<GuestJpaEntity> findAllByEvent(@Param("eventid") UUID eventId);
}
