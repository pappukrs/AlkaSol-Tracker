package codeserver.AlkaSol_backend.service.impl;


import org.p2p.solanaj.core.*;
import org.p2p.solanaj.rpc.RpcClient;
import org.p2p.solanaj.rpc.RpcException;
import org.p2p.solanaj.rpc.types.ConfirmedSignature;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

    private final RpcClient client = new RpcClient("https://api.devnet.solana.com");

    // Create Wallet
    @Override
    public String createWallet() {
        Keypair keypair = Keypair.generate();
        return "Wallet created! PublicKey: " + keypair.getPublicKey().toBase58()
                + " PrivateKey: " + Base64.getEncoder().encodeToString(keypair.getSecretKey());
    }

    // Transfer SOL
    @Override
    public String transferSOL(TransferRequest request) {
        try {
            PublicKey senderPublicKey = new PublicKey(Base64.getDecoder().decode(request.getSenderPrivateKey()));
            PublicKey recipient = new PublicKey(request.getRecipient());
            long lamports = (long) (request.getAmount() * 1_000_000_000);  // Convert SOL to lamports

            Transaction transaction = new Transaction();
            transaction.add(SystemProgram.transfer(senderPublicKey, recipient, lamports));

            Keypair senderKeypair = Keypair.fromSecretKey(Base64.getDecoder().decode(request.getSenderPrivateKey()));
            String signature = client.getApi().sendTransaction(transaction, senderKeypair);

            return "Transaction successful. Signature: " + signature;
        } catch (RpcException e) {
            return "Transaction failed: " + e.getMessage();
        }
    }

    // Get Wallet Balance
    @Override
    public BalanceResponse getWalletBalance(String walletAddress) {
        try {
            PublicKey publicKey = new PublicKey(walletAddress);
            double balance = client.getApi().getBalance(publicKey) / 1_000_000_000.0;  // Convert lamports to SOL
            return new BalanceResponse(walletAddress, balance);
        } catch (RpcException e) {
            throw new RuntimeException("Error fetching balance: " + e.getMessage());
        }
    }

    // Get Transaction History
    @Override
    public List<TransactionHistoryResponse> getTransactionHistory(String walletAddress) {
        try {
            PublicKey publicKey = new PublicKey(walletAddress);
            List<ConfirmedSignature> signatures = client.getApi().getConfirmedSignaturesForAddress2(publicKey);

            List<TransactionHistoryResponse> history = new ArrayList<>();
            for (ConfirmedSignature signature : signatures) {
                TransactionHistoryResponse response = new TransactionHistoryResponse();
                response.setSignature(signature.getSignature());
                response.setTimestamp(signature.getBlockTime() * 1000L); // Convert to milliseconds
                // Add more details if necessary
                history.add(response);
            }
            return history;
        } catch (RpcException e) {
            throw new RuntimeException("Error fetching transaction history: " + e.getMessage());
        }
    }
}
