import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class HashManager {

    private HashManager() {
    }

    public static String calculateHash(String originalString) {
        return Hashing.sha256()
                .hashString(originalString, StandardCharsets.UTF_8)
                .toString();
    }

    public static boolean hasWorkBeenProven(String hash) {
        String workProveRule = "00000";
        return hash.startsWith(workProveRule);
    }
}
