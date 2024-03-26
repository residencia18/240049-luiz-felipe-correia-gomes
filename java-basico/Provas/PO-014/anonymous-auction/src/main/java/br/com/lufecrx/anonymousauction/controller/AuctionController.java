package br.com.lufecrx.anonymousauction.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lufecrx.anonymousauction.model.Auction;
import br.com.lufecrx.anonymousauction.model.dto.AuctionDTO;
import br.com.lufecrx.anonymousauction.repository.AuctionRepository;
import br.com.lufecrx.anonymousauction.service.AuctionService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/auctions")
public class AuctionController {

    private final AuctionRepository repository;

    @PostMapping
    public ResponseEntity<AuctionDTO> save(@RequestBody AuctionDTO dto) {
        Auction entity = repository.save(AuctionService.fromDto(dto));
        return ResponseEntity.ok(AuctionService.from(entity));
    }

    @GetMapping
    public ResponseEntity<Iterable<AuctionDTO>> findAll() {
        Iterable <Auction> auctions = repository.findAll();
        return ResponseEntity.ok(AuctionService.from(auctions));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuctionDTO> findById(@PathVariable int id) {
        Optional <Auction> opt = repository.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        AuctionDTO dto = AuctionService.from(opt.get());
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuctionDTO> update(@PathVariable int id, @RequestBody AuctionDTO auctionDTO) {
        Optional<Auction> optionalAuction = repository.findById(id);
        if (optionalAuction.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
    
        Auction existingAuction = optionalAuction.get();
    
        existingAuction.setTitle(auctionDTO.getTitle());
        existingAuction.setDescription(auctionDTO.getDescription());
        existingAuction.setInitialPrice(auctionDTO.getInitialPrice());
        existingAuction.setActive(auctionDTO.isActive());
    
        Auction updatedAuction = repository.save(existingAuction);
    
        AuctionDTO updatedAuctionDTO = AuctionService.from(updatedAuction);
        return ResponseEntity.ok(updatedAuctionDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Optional <Auction> opt = repository.findById(id);
        repository.delete(opt.get());
        return ResponseEntity.ok().build();
    }
}
