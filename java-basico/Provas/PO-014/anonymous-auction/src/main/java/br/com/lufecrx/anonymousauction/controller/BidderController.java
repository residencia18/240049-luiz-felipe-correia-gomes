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

import br.com.lufecrx.anonymousauction.model.Bidder;
import br.com.lufecrx.anonymousauction.model.dto.BidderDTO;
import br.com.lufecrx.anonymousauction.repository.BidderRepository;
import br.com.lufecrx.anonymousauction.service.BidderService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/bidders")
public class BidderController {

    private final BidderRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<BidderDTO> findById(@PathVariable int id) {
        Optional<Bidder> opt = repository.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        BidderDTO dto = BidderService.from(opt.get());
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Iterable<BidderDTO>> findAll() {
        Iterable<Bidder> bidders = repository.findAll();
        return ResponseEntity.ok(BidderService.from(bidders));
    }

    @PostMapping
    public ResponseEntity<BidderDTO> save(@RequestBody BidderDTO dto) {
        Bidder entity = repository.save(BidderService.fromDto(dto));
        return ResponseEntity.ok(BidderService.from(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BidderDTO> update(@PathVariable int id, @RequestBody BidderDTO updatedBidderDTO) {
        Optional<Bidder> optionalBidder = repository.findById(id);
        if (optionalBidder.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
    
        Bidder existingBidder = optionalBidder.get();
        existingBidder.setName(updatedBidderDTO.getName());
        existingBidder.setCpf(updatedBidderDTO.getCpf());
    
        Bidder updatedBidder = repository.save(existingBidder);
        return ResponseEntity.ok(BidderService.from(updatedBidder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BidderDTO> delete(@PathVariable int id) {
        Optional<Bidder> opt = repository.findById(id);
        repository.delete(opt.get());
        return ResponseEntity.ok(BidderService.from(opt.get()));
    }
}
