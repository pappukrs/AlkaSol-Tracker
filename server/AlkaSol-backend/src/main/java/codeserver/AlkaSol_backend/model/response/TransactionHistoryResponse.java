package codeserver.AlkaSol_backend.model.response;

import lombok.Data;

@Data
public class TransactionHistoryResponse {
    private String signature;
    private long timestamp;
}
