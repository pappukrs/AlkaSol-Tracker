package codeserver.AlkaSol_backend.service;


import java.util.List;

import codeserver.AlkaSol_backend.model.request.TransferRequest;
import codeserver.AlkaSol_backend.model.response.BalanceResponse;
import codeserver.AlkaSol_backend.model.response.TransactionHistoryResponse;

public class WalletService {

    String createWallet();

    String transferSOL(TransferRequest request);

    BalanceResponse getWalletBalance(String walletAddress);

    List<TransactionHistoryResponse> getTransactionHistory(String walletAddress);
    
}
