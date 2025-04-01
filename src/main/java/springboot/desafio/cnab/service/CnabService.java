package springboot.desafio.cnab.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import springboot.desafio.cnab.model.Transaction;
import springboot.desafio.cnab.model.TransactionType;
import springboot.desafio.cnab.repository.TransactionRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CnabService {

    private TransactionRepository repository;

    public CnabService(TransactionRepository repository) {
        this.repository = repository;
    }

    public void processFile(MultipartFile file) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                Transaction transaction = parseLine(line);
                repository.save(transaction);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private Transaction parseLine(String line) {
        Transaction transaction = new Transaction();

        int code = Integer.parseInt(line.substring(0, 1));
        TransactionType type = TransactionType.getTransactionType(code);

        transaction.setCode(type.getCode());
        transaction.setType(type.getType());
        transaction.setDescription(type.getDescription());
        transaction.setSign(type.getSign());

        transaction.setDate(LocalDate.parse(line.substring(1, 9), DateTimeFormatter.BASIC_ISO_DATE));
        transaction.setPrice(new BigDecimal(line.substring(9, 19)).divide(new BigDecimal(100)));
        transaction.setCpf(line.substring(19, 30));
        transaction.setCard(line.substring(30, 42));
        transaction.setHour(LocalTime.parse(line.substring(42, 48), DateTimeFormatter.ofPattern("HHmmss")));
        transaction.setShopOwner(line.substring(48, 62).trim());
        transaction.setShopName(line.substring(62, 80).trim());

        return transaction;
    }

    public List<Transaction> getTransactions() {
        return repository.findAll();
    }
}
