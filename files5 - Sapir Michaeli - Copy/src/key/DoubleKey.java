package key;

import java.io.Serializable;

/**
 * Created by Sapir Michaeli on 26.03.2017.
 */
public class DoubleKey <T,M> implements Serializable{
    T key1;
    M key2;

    public DoubleKey(T key1, M key2) {
        this.key1 = key1;
        this.key2 = key2;
    }

    public T getKey1() {
        return key1;
    }

    public void setKey1(T key1) {
        this.key1 = key1;
    }

    public M getKey2() {
        return key2;
    }

    public void setKey2(M key2) {
        this.key2 = key2;
    }

}
