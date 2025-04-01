package springboot.desafio.cnab.model;

public enum TransactionType {
    DEBITO(1, "Debito", "Entrada", "+"),
    BOLETO(2, "Boleto", "Saída", "-"),
    FINANCIAMENTO(3, "Financiamento", "Saída", "-"),
    CREDITO(4, "Credito", "Entrada", "+"),
    RECEBIMENTO_EMPRESTIMO(5, "Recebimento de Empréstimo", "Entrada", "+"),
    VENDAS(6, "Vendas", "Entrada", "+"),
    RECEBIMENTO_TED(7, "Recebimento TED", "Entrada", "+"),
    RECEBIMENTO_DOC(8, "Recebimento DOC", "Entrada", "+"),
    ALUGUEL(9, "Aluguél", "Saída", "-");

    private final int code;
    private final String description;
    private final String type;
    private final String sign;

    TransactionType(int code, String description, String type, String sign) {
        this.code = code;
        this.description = description;
        this.type = type;
        this.sign = sign;
    }

    public static TransactionType getTransactionType(int code) {
        for (TransactionType transactionType : TransactionType.values()) {
            if (transactionType.code == code) {
                return transactionType;
            }
        }
        throw new IllegalArgumentException("No TransactionType found with code: " + code);
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getSign() {
        return sign;
    }
}
