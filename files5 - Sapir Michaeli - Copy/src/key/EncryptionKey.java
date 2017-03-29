package key;

/**
 * Created by Sapir Michaeli on 26.03.2017.
 */
public class EncryptionKey {
    private int key;

    public EncryptionKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
