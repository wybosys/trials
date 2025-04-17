import java.util.logging.Logger;
import java.util.logging.Level;

public class Hash {
    public static void main(String[] args) {
        Logger log = Logger.getLogger("tesglog");  
        log.setLevel(Level.ALL);

        log.info("abc123123l*(*)(*fdsdfa)".hashCode() + "");             
        log.info("abc123123l*(*)(*fdsdfa)一二三四abc123123l*(*)(*fdsdfa)一二三四".hashCode() + "");
    }
}
