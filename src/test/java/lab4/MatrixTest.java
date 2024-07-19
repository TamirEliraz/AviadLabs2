package lab4;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MatrixTest {
    
    private MatrixFactory<Integer> matrixFactory = new MatrixFactory<Integer>();
    private Matrix<Integer> matrix3;
    private Integer defaultVal;
    private Integer newInstance2;
    private Integer newInstance3;
    
    private int i = 0;
    
    public Integer getParameterInstance() {
        return i++;
    }
    
    
    @Before
    public void setUp() throws Exception {
        i = 0;
        defaultVal = getParameterInstance();
        newInstance2 = getParameterInstance();
        newInstance3 = getParameterInstance();
        
        matrix3 = matrixFactory.getMatrix(4, defaultVal);
        
        if (( defaultVal.equals(newInstance2) ) || ( defaultVal.equals(newInstance3) )
                || ( newInstance3.equals(newInstance2) )) {
            fail("new instances should be different");
        }
    }
    
    @Test
    public void testGetterSetterTranspose() {
        assertNotNull(matrix3);
        assertEquals(matrix3.get(1, 1), defaultVal);
        matrix3.set(1, 2, newInstance2);
        assertEquals(matrix3.get(1, 2), newInstance2);
        matrix3.set(1, 4, newInstance3);
        assertEquals(matrix3.get(1, 4), newInstance3);
        matrix3.transpose();
        assertEquals(matrix3.get(1, 2), defaultVal);
        assertEquals(matrix3.get(1, 4), defaultVal);
        assertEquals(matrix3.get(2, 1), newInstance2);
        assertEquals(matrix3.get(4, 1), newInstance3);
        matrix3.set(1, 2, newInstance3);
        matrix3.transpose();
        assertEquals(matrix3.get(1, 2), newInstance2);
        assertEquals(matrix3.get(2, 1), newInstance3);
    }
    
    @Test
    public void testFail() {
        assertNotNull(matrix3);
        try {
            matrix3.get(100, 200);
            fail("Not suppose to happen!");
        } catch (IllegalArgumentException ignored) { }
        
        try {
            matrix3.set(-100, -200, newInstance2);
            fail("Not suppose to happen!");
        } catch (IllegalArgumentException ignored) { }
    }
    
    
}