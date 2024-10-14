package codeserver.AlkaSol_backend.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BalanceResponse {
    private String walletAddress;
    private double balance;
}