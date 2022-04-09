import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BlockChain {

    private final List<Block> blockchain;

    public BlockChain() {
        this.blockchain = new ArrayList<>();
    }

    public BlockChain buildNewBlock(String[] data) {
        Optional<Block> prevBlock = this.getLastBlockFromChain();
        String blockToBuildPrevHash = prevBlock.isPresent() ? prevBlock.get().getHash() : "";
        Block potentiallyNewBlock = new Block(blockToBuildPrevHash, data);

        String hash = HashManager.calculateHash(potentiallyNewBlock.toString());
        while (!HashManager.hasWorkBeenProven(hash)) {
            potentiallyNewBlock.changeNonce();
            hash = HashManager.calculateHash(potentiallyNewBlock.toString());
        }
        potentiallyNewBlock.setCalculatedHash(hash);

        this.blockchain.add(potentiallyNewBlock);

        return this;
    }

    public List<Block> getBlockchain() {
        return this.blockchain;
    }

    private Optional<Block> getLastBlockFromChain() {
        if (this.blockchain.size() > 0) {
            return Optional.of(blockchain.get(this.blockchain.size() - 1));
        } else {
            return Optional.empty();
        }
    }
}
