package lab4;

import static org.junit.Assert.fail;

import org.junit.Before;

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
        
        matrix3 = matrixFactory.getMatrix(3, defaultVal);
        
        if ((defaultVal.equals(newInstance2)) || (defaultVal.equals(newInstance3))
                || (newInstance3.equals(newInstance2))) {
            fail("new instances should be different");
        }
    }
    
    
    
}