import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;

public class BlockChainTest {

    @Test
    public void bockChainCreationTest() {
        DataGenerator dataGenerator = new DataGenerator(4);
        BlockChain blockChain = new BlockChain();

        String[] dataForFirstBlock = dataGenerator.getTransactions();
        String[] dataForSecondBlock = dataGenerator.getTransactions();
        String[] dataForThirdBlock = dataGenerator.getTransactions();

        blockChain.buildNewBlock(dataForFirstBlock);
        Assert.assertEquals(blockChain.getBlockchain().size(), 1);

        blockChain.buildNewBlock(dataForSecondBlock);
        Assert.assertEquals(blockChain.getBlockchain().size(), 2);

        blockChain.buildNewBlock(dataForThirdBlock);
        Assert.assertEquals(blockChain.getBlockchain().size(), 3);
    }

    @Test
    public void bockChainProofOfWorkTest() {
        DataGenerator dataGenerator = new DataGenerator(4);
        BlockChain blockChain = new BlockChain();

        String[] dataForFirstBlock = dataGenerator.getTransactions();
        String[] dataForSecondBlock = dataGenerator.getTransactions();
        String[] dataForThirdBlock = dataGenerator.getTransactions();

        blockChain.buildNewBlock(dataForFirstBlock)
                .buildNewBlock(dataForSecondBlock)
                .buildNewBlock(dataForThirdBlock);

        Block firstBlock = blockChain.getBlockchain().get(0);
        Block secondBlock = blockChain.getBlockchain().get(1);

        Assert.assertNotEquals(firstBlock.getHash(), secondBlock.getHash(), "Error - Hash from first block equals to the Hash of second one");
        Assert.assertEquals(firstBlock.getHash(), secondBlock.getPrevHash(), "Error - Hash from first block does not equal to the prev hash of second one");
        Assert.assertEquals(secondBlock.getHash(), HashManager.calculateHash(secondBlock.toString()), "Error - Calculated hash from Block with " + secondBlock.getNonce() + " nonce and " + Arrays.toString(secondBlock.getData()) + " transactions list is not correct");
    }
}
