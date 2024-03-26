package br.com.lufecrx.anonymousauction.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lufecrx.anonymousauction.model.Bid;
import br.com.lufecrx.anonymousauction.model.WinData;
import br.com.lufecrx.anonymousauction.repository.BidRepository;
import br.com.lufecrx.anonymousauction.service.WinDataService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/winner")
@AllArgsConstructor
public class WinnerController {   

    private final BidRepository bidRepository;

    @GetMapping("/auction={id}")
    public ResponseEntity<WinData> winner(@PathVariable int id) {
        Iterable <Bid> opt = bidRepository.findByAuctionId(id);
        if (opt == null) {
            return ResponseEntity.notFound().build();
        }         
        return ResponseEntity.ok(WinDataService.from(opt));
    }
}
