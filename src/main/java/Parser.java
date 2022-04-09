import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Parser {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final String fileName = "blockchain.json";

    private Parser() {
    }

    public static void parseBlockChainListToJson(BlockChain blockChain) {
        try {
            mapper.writeValue(new File("target/" + fileName), blockChain);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BlockChain parseJsonToBlockChain() {
        BlockChain blockChain = null;
        try {
            blockChain = mapper.readValue(new URL("file:target/" + fileName), BlockChain.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return blockChain;
    }
}
