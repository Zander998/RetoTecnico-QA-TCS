package karate;

import com.intuit.karate.junit5.Karate;

public class DemoblazeTest {

    @Karate.Test
    public Karate runAll() {
        return Karate.run("classpath:features").tags("~@ignore");
    }
}
