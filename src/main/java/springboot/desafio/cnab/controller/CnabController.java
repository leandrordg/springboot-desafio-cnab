package springboot.desafio.cnab.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springboot.desafio.cnab.model.Transaction;
import springboot.desafio.cnab.service.CnabService;

import java.util.List;

@RestController
@RequestMapping("/cnab")
public class CnabController {

    private CnabService cnabService;

    public CnabController(CnabService cnabService) {
        this.cnabService = cnabService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file){
        cnabService.processFile(file);
        return ResponseEntity.ok("File processed successfully");
    }

    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getTransaction(){
        return ResponseEntity.ok(cnabService.getTransactions());
    }
}
