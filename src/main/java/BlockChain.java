import java.util.ArrayList;
import java.util.List;

public class BlockChain {

    private final List<Block> blockchain;

    public BlockChain() {
        this.blockchain = new ArrayList<>();
    }

    public BlockChain buildNewBlock(String[] data) {
        Block prevBlock = this.getLastBlockFromChain();
        String blockToBuildPrevHash = prevBlock == null ? "" : prevBlock.getHash();
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

    private Block getLastBlockFromChain() {
        if (this.blockchain.size() > 0) {
            return blockchain.get(this.blockchain.size() - 1);
        } else {
            return null;
        }
    }
}
