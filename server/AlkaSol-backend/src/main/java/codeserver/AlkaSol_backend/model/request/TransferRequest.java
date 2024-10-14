package codeserver.AlkaSol_backend.model.request;

import lombok.Data;

@Data
public class TransferRequest {
    private String senderPrivateKey;
    private String recipient;
    private double amount;
}
