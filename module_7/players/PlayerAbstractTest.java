package players;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerAbstractTest {

    @Test
    public void testNFLPlayerAbstract() {
        PlayerAbstract player = new NFLPlayerAbstract("Joe Burrow", 4300, "Bengals");
        assertEquals("Joe Burrow", player.getNameAbstract());
        assertEquals(4300, player.getStatsAbstract());
        assertEquals("Football (NFL)", player.getSportAbstract());
    }

    @Test
    public void testNBAPlayerAbstract() {
        PlayerAbstract player = new NBAPlayerAbstract("Kevin Durant", 2100, "Suns");
        assertEquals("Kevin Durant", player.getNameAbstract());
        assertEquals(2100, player.getStatsAbstract());
        assertEquals("Basketball (NBA)", player.getSportAbstract());
    }
}
