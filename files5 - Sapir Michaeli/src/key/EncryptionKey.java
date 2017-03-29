package key;

/**
 * Created by Sapir Michaeli :) on 26.03.2017.
 */
public class EncryptionKey<T>  implements Key<T>{
    private T key;

    public EncryptionKey(T key) {
        this.key = key;
    }

    @Override
    public T getKey() {
        return key;
    }

    @Override
    public void setKey(T key) {
        this.key = key;
    }
}
