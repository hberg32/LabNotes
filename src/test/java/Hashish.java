import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

@SuppressWarnings("WeakerAccess") //Shut up about xxx can be package-private
public class Hashish {
    @Test
    public void theTest() {
        HashMap<thing, String> theMap = new HashMap<>();
        theMap.put(new thing("a"), "a");
        theMap.put(new thing("b"), "b");
        assertThat(theMap.size(), equalTo(2));//Proof that hashcodes don't affect identity
    }

    private class thing {
        String id;

        public thing(String id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof thing) {
                return id.equals(obj);
            }

            return false;
        }

        @Override
        //Always return fixed hashcode
        public int hashCode() {
            return 5;
        }
    }
}
