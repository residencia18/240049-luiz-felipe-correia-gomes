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

import br.com.lufecrx.anonymousauction.model.Bid;
import br.com.lufecrx.anonymousauction.model.dto.BidDTO;
import br.com.lufecrx.anonymousauction.repository.BidRepository;
import br.com.lufecrx.anonymousauction.service.BidService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/bid")
public class BidController {

    private final BidRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<BidDTO> findById(@PathVariable int id) {
        Optional <Bid> opt = repository.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        BidDTO dto = BidService.from(opt.get());
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Iterable<BidDTO>> findAll() {
        Iterable <Bid> bids = repository.findAll();
        return ResponseEntity.ok(BidService.from(bids));
    }

    @GetMapping("/auction={auctionId}")
    public ResponseEntity<Iterable<BidDTO>> findByAuction(@PathVariable int auctionId) {
        Iterable <Bid> bids = repository.findByAuctionId(auctionId);
        return ResponseEntity.ok(BidService.from(bids));
    }

    @GetMapping("/bidder={bidderId}")
    public ResponseEntity<Iterable<BidDTO>> findByBidder(@PathVariable int bidderId) {
        Iterable <Bid> bids = repository.findByBidderId(bidderId);
        return ResponseEntity.ok(BidService.from(bids));
    }

    @PostMapping
    public ResponseEntity<BidDTO> save(@RequestBody BidDTO dto) {
        Bid entity = repository.save(BidService.fromDto(dto));
        return ResponseEntity.ok(BidService.from(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BidDTO> update(@PathVariable int id, @RequestBody BidDTO updatedBidDTO) {
        Optional<Bid> optionalBid = repository.findById(id);
        if (optionalBid.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Bid existingBid = optionalBid.get();
    
        existingBid.setAmount(updatedBidDTO.getAmount());
    
        Bid updatedBid = repository.save(existingBid);
    
        BidDTO updatedBidDTOResponse = BidService.from(updatedBid);
        return ResponseEntity.ok(updatedBidDTOResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Optional <Bid> opt = repository.findById(id);
        repository.delete(opt.get());
        return ResponseEntity.ok().build();
    }
}
