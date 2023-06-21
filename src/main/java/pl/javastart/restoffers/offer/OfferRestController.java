package pl.javastart.restoffers.offer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class OfferRestController {
    private final OfferService offerService;

    public OfferRestController(OfferService offerService) {
        this.offerService = offerService;
    }

    //Metoda zwraca wszystkie oferty lub szuka oferty według podanego parametru
    @GetMapping("/api/offers")
    public List<OfferDto> searchOffers(@RequestParam(required = false) String title) {
        return offerService.searchOffers(title);
    }

    //Metoda zwraca liczbe wszystkich ofert w systemie
    @GetMapping("/api/offers/count")
    public Long countAll() {
        return (long) offerService.findAll()
                .size();
    }

    //Metoda dodaje nową oferte do bazy danych
    @PostMapping("/api/offers")
    public ResponseEntity<OfferDto> addOffer(@RequestBody OfferInsertDto offerInsertDto) {
        OfferDto offerDto = offerService.insert(offerInsertDto);
        return ResponseEntity.ok(offerDto);
    }

    //Metoda znajduje oferte po id
    @GetMapping("/api/offers/{id}")
    public ResponseEntity<OfferDto> findById(@PathVariable Long id) {
        Optional<OfferDto> offerOptional = offerService.findById(id);
        return offerOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    //Usuwanie oferty po id
    @DeleteMapping("/api/offers/{id}")
    public void deleteOfferById(@PathVariable Long id) {
        offerService.deleteOffersById(id);
    }
}

