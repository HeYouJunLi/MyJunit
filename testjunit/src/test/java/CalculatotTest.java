
import org.junit.*;


import static org.junit.Assert.*;

public class CalculatotTest {

    private Calculatot cal;

    @BeforeClass
    public static void bc(){
        System.out.println("beforeclass");
    }

    @Before
    public void setUp() throws Exception {
        System.out.println("before");
        cal=new Calculatot();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("after");
    }

    @AfterClass
    public static void ac(){
        System.out.println("afterclass");
    }

    @Test
    public void add() {
        System.out.println("add测试");
        Assert.assertEquals(3,cal.add(1,2));
    }

    @Test
    public void sub() {
        System.out.println("sub测试");
        Assert.assertEquals(1,cal.sub(2,1));
    }
}