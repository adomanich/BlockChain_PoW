import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

public class BlockChainJsonStorageTest {

    @BeforeMethod
    public void storeBlockChain() {
        DataGenerator dataGenerator = new DataGenerator(4);
        BlockChain blockChain = new BlockChain();

        String[] dataForFirstBlock = dataGenerator.getTransactions();
        String[] dataForSecondBlock = dataGenerator.getTransactions();
        String[] dataForThirdBlock = dataGenerator.getTransactions();

        blockChain.buildNewBlock(dataForFirstBlock)
                .buildNewBlock(dataForSecondBlock)
                .buildNewBlock(dataForThirdBlock);

        Parser.parseBlockChainListToJson(blockChain);
    }

    @Test
    public void blockChainJsonStorageTest() {
        BlockChain blockChain = Parser.parseJsonToBlockChain();

        Block firstBlock = blockChain.getBlockchain().get(0);
        Block secondBlock = blockChain.getBlockchain().get(1);

        Assert.assertNotEquals(firstBlock.getHash(), secondBlock.getHash(), "Error - Hash of first block equals to the Hash of second one");
        Assert.assertEquals(firstBlock.getHash(), secondBlock.getPrevHash(), "Error - Hash of first block does not equal to the prev hash of second one");
        Assert.assertEquals(secondBlock.getHash(), HashManager.calculateHash(secondBlock.toString()), "Error - Calculated hash of Block with " + secondBlock.getNonce() + " nonce and " + Arrays.toString(secondBlock.getData()) + " transactions list is not correct");
    }
}
