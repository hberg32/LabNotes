import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

class StaticClass  {
    public static String aStaticMethod() {
        return "StaticClass.aStaticMethod";
    }

    public String aNonStaticMethod() {
        return "StaticClass.aNonStaticMethod";
    }
}
class InstanceClass extends StaticClass {
    //@Override   <-- this LOOKs valid but isn't  You can only hide a superclass static not override it
    public static String aStaticMethod() {
        return "InstanceClass.aStaticMethod";
    }

    @Override
    public String aNonStaticMethod() {
        return "InstanceClass.aNonStaticMethod";
    }
}
public class StaticMethodHiding {
    @Test
    public void proof() {
        StaticClass staticClass = new StaticClass();
        InstanceClass instClass = new InstanceClass();

        assertThat(staticClass.aStaticMethod(), equalTo("StaticClass.aStaticMethod"));
        assertThat(staticClass.aNonStaticMethod(), equalTo("StaticClass.aNonStaticMethod"));
        assertThat(instClass.aStaticMethod(), equalTo("InstanceClass.aStaticMethod"));
        assertThat(instClass.aNonStaticMethod(), equalTo("InstanceClass.aNonStaticMethod"));

        //This bit below is the point of this exercise
        assertThat(((StaticClass) instClass).aStaticMethod(), equalTo("StaticClass.aStaticMethod"));
        assertThat(((StaticClass) instClass).aNonStaticMethod(), equalTo("InstanceClass.aNonStaticMethod"));
    }
}