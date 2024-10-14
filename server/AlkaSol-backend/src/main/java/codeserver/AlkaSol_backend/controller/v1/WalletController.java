package codeserver.AlkaSol_backend.controller.v1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import codeserver.AlkaSol_backend.model.request.TransferRequest;
import codeserver.AlkaSol_backend.model.response.BalanceResponse;
import codeserver.AlkaSol_backend.model.response.TransactionHistoryResponse;
import codeserver.AlkaSol_backend.service.WalletService;

import java.util.List;


@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;


    // API to create a new wallet
    @PostMapping("/create")
    public ResponseEntity<String> createWallet() {
        return ResponseEntity.ok(walletService.createWallet());
    }



    // API to transfer SOL
    @PostMapping("/transfer")
    public ResponseEntity<String> transferSOL(@RequestBody TransferRequest request) {
        return ResponseEntity.ok(walletService.transferSOL(request));
    }

     // API to check wallet balance
    @GetMapping("/balance/{walletAddress}")
    public ResponseEntity<BalanceResponse> getBalance(@PathVariable String walletAddress) {
        return ResponseEntity.ok(walletService.getWalletBalance(walletAddress));
    }

    // API to get transaction history
    @GetMapping("/history/{walletAddress}")
    public ResponseEntity<List<TransactionHistoryResponse>> getTransactionHistory(@PathVariable String walletAddress) {
        return ResponseEntity.ok(walletService.getTransactionHistory(walletAddress));
    }




    
}
