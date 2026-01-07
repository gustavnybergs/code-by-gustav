package se.gustavnyberg.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.gustavnyberg.portfolio.model.ContactMessage;

import java.util.List;

/**
 * Databaskoppling för kontaktmeddelanden.
 *
 * Genom att ärva JpaRepository får vi färdiga metoder som save()
 * för att spara meddelanden i PostgreSQL.
 */

@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {

}