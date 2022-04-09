import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Block {

    private String prevHash;
    private String[] data;
    private String hash;
    private int nonce;
    private long timestamp;

    public Block(String prevHash, String[] data) {
        this.prevHash = prevHash;
        this.data = data;
        this.nonce = 0;
        this.timestamp = System.currentTimeMillis();
    }

    public void changeNonce() {
        this.nonce++;
    }

    public void setCalculatedHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return this.prevHash + this.getTimestamp() + Arrays.toString(this.data) + this.nonce;
    }
}
