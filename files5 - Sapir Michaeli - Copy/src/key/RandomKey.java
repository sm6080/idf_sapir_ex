package key;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by Sapir Michaeli on 19.03.2017.
 */
public class RandomKey implements Key, Serializable {

    Random random;
    Integer key;

    public RandomKey() {
        random = new Random(System.currentTimeMillis());
    }


    @Override
    public int getKey() {
        key = random.nextInt();
        return key;
    }

    @Override
    public void setKey(int key) {
        this.key = (Integer) key;
    }


}
