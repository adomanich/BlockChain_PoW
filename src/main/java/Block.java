import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@NoArgsConstructor
@AllArgsConstructor
public class Block {

    private String prevHash;
    private String[] data;
    private int nonce;
    private String hash;

    public Block(String prevHash, String[] data) {
        this.prevHash = prevHash;
        this.data = data;
        this.nonce = 0;
    }

    public void changeNonce() {
        this.nonce++;
    }

    public String getPrevHash() {
        return prevHash;
    }

    public String getHash() {
        return hash;
    }

    public int getNonce() {
        return nonce;
    }

    public String[] getData() {
        return data;
    }

    public void setCalculatedHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return this.prevHash + Arrays.toString(this.data) + this.nonce;
    }
}
