import org.junit.Assert;
import org.junit.Test;
import prime.PrimeUtil;

public class PrimeUtilTest {

    @Test
    public void testGetPrimeForEmptyResult(){
        int[] expected = {};
        Assert.assertArrayEquals(expected, PrimeUtil.getPrime(2));
        Assert.assertArrayEquals(expected, PrimeUtil.getPrime(-1));
        Assert.assertArrayEquals(expected, PrimeUtil.getPrime(0));
    }
}
