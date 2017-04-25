package key;

import java.io.Serializable;

/**
 * Created by Sapir Michaeli on 26.03.2017.
 */
public class DoubleKey <T,M> implements Serializable {
    Key<T> key1;
    Key<M> key2;

    public DoubleKey(Key<T> key1, Key<M> key2) {
        this.key1 = key1;
        this.key2 = key2;
    }

    public Key<T> getKey1() {
        return key1;
    }

    public void setKey1(Key<T> key1) {
        this.key1 = key1;
    }

    public Key<M> getKey2() {
        return key2;
    }

    public void setKey2(Key<M> key2) {
        this.key2 = key2;
    }

}
