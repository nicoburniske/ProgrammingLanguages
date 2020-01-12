import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class STest {
    // SName examples
    S john, nick, ben, abc, zyx;
    // List<S> Examples used to create the SArray examples
    List<S> list1, list2, list3, list4;
    // SArray examples
    S sDepth1a, sDepth1b, sDepth2, sDepth3;
    @Before
    public void setUp() throws Exception {
        john = new SName("john");
        nick = new SName("nick");
        ben = new SName("ben");
        abc = new SName("abc");
        zyx = new SName("zyx");

        list1 = new ArrayList(Arrays.asList(new S[]{john, nick, ben}));
        list2 = new ArrayList(Arrays.asList(new S[]{abc, zyx}));

        sDepth1a = new SArray(list1);
        sDepth1b = new SArray(list2);
        list3 = new ArrayList(Arrays.asList(new S[]{sDepth1a, sDepth1b, john}));
        sDepth2 = new SArray(list3);
        list4 = new ArrayList(Arrays.asList(new S[]{sDepth2, sDepth1a, john}));
        sDepth3 = new SArray(list4);
    }

    @Test
    public void toJSON() {
        assertEquals("\"john\"", john.toJSON());

        assertEquals("[\"john\",\"nick\",\"ben\"]", sDepth1a.toJSON());
        assertEquals("[\"abc\",\"zyx\"]", sDepth1b.toJSON());
        assertEquals("[[\"john\",\"nick\",\"ben\"],[\"abc\",\"zyx\"],\"john\"]", sDepth2.toJSON());
        assertEquals("[[[\"john\",\"nick\",\"ben\"],[\"abc\",\"zyx\"],\"john\"],[\"john\",\"nick\",\"ben\"],\"john\"]", sDepth3.toJSON());
    }
    @Test
    public void count() {
        assertEquals(1, john.count());

        assertEquals(3, sDepth1a.count());
        assertEquals(2, sDepth1b.count());
        assertEquals(6, sDepth2.count());
        assertEquals(10, sDepth3.count());
    }

    @Test
    public void replace() {
        assertEquals("\"qlsm\"", john.replace().toJSON());
        assertEquals("\"zyx\"", abc.replace().toJSON());

        assertEquals("[\"qlsm\",\"mrxp\",\"yvm\"]", sDepth1a.replace().toJSON());
        assertEquals("[\"zyx\",\"abc\"]", sDepth1b.replace().toJSON());
    }

    @Test
    public void context() {
        assertEquals("0", john.context().toJSON());

        assertEquals("[1,1,1]", sDepth1a.context().toJSON());
        assertEquals("[1,1]", sDepth1b.context().toJSON());
        assertEquals("[[2,2,2],[2,2],1]", sDepth2.context().toJSON());
        assertEquals("[[[3,3,3],[3,3],2],[2,2,2],1]", sDepth3.context().toJSON());
    }
}