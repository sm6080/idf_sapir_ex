package key;

import com.company.InputException;

/**
 * Created by Sapir Michaeli on 21.03.2017.
 */
public class BadKeyException extends InputException {

    public BadKeyException(String exceptionMessage) {
        super(exceptionMessage);
    }

}
