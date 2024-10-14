package codeserver.AlkaSol_backend.service.solana;

import com.mmorrell.solanaj.rpc.RpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolanaService {

    private final RpcClient client;

    @Autowired
    public SolanaService(RpcClient client) {
        this.client = client;
    }

    public void someSolanaOperation() {
        // Example: Check balance of a wallet
        String walletAddress = "YOUR_WALLET_ADDRESS";
        double balance = client.getBalance(walletAddress);
        System.out.println("Balance: " + balance);
    }
}

