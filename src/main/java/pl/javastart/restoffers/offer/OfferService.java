package pl.javastart.restoffers.offer;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.javastart.restoffers.category.CategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final CategoryRepository categoryRepository;

    public OfferService(OfferRepository offerRepository, CategoryRepository categoryRepository) {
        this.offerRepository = offerRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<OfferDto> searchOffers(String title) {
        if (title != null) {
            return searchByTitle(title);
        } else {
            return findAll();
        }
    }

    public List<OfferDto> findAll() {
        return offerRepository.findAll()
                .stream()
                .map(OfferService::toDto)
                .collect(Collectors.toList());
    }

    public Optional<OfferDto> findById(Long id) {
        return offerRepository.findById(id).map(OfferService::toDto);
    }
    //Metoda zwraca oferty według podanego parametru

    public List<OfferDto> searchByTitle(String title) {
        return findByTitle(title);
    }

    public List<OfferDto> findByTitle(String title) {
        List<OfferDto> offers = offerRepository.findAll()
                .stream()
                .filter(offer -> offer.getTitle().toLowerCase().contains(title))
                .map(OfferService::toDto)
                .toList();
        if (offers.isEmpty())
            return offerRepository.findAll().stream().map(OfferService::toDto).collect(Collectors.toList());
        else
            return offers;
    }

    public OfferDto insert(OfferInsertDto offerInsertDto) {
        Offer offer = new Offer();
        offer.setTitle(offerInsertDto.getTitle());
        offer.setDescription(offerInsertDto.getDescription());
        offer.setImgUrl(offerInsertDto.getImgUrl());
        offer.setPrice(offerInsertDto.getPrice());
        offer.setCategory(categoryRepository.findByName(offerInsertDto.getCategory()));
        offerRepository.save(offer);
        return toDto(offer);
    }

    public void deleteOffersById(Long id) {
        try {
            offerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ignore) {
            //ignore
        }
    }
    static OfferDto toDto(Offer offer) {
        OfferDto dto = new OfferDto();
        dto.setId(offer.getId());
        dto.setTitle(offer.getTitle());
        dto.setDescription(offer.getDescription());
        dto.setImgUrl(offer.getImgUrl());
        dto.setPrice(offer.getPrice());
        dto.setCategory(offer.getCategory().getName());
        return dto;
    }
}
