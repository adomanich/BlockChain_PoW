import java.util.Arrays;
import java.util.Random;

public class DataGenerator {

    private final int transactionsBlockSize;

    public DataGenerator(int transactionsBlockSize) {
        this.transactionsBlockSize = transactionsBlockSize;
    }

    public String[] getTransactions() {
        String[] transactions = new String[this.transactionsBlockSize];
        Arrays.setAll(transactions, p -> "transaction_" + new Random().nextInt(100000));

        return transactions;
    }
}
