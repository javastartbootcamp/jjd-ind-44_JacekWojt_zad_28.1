package pl.javastart.restoffers.offer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Long> {
}
