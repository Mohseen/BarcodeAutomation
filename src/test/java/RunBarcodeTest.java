import com.google.zxing.NotFoundException;
import org.testng.annotations.Test;

import java.io.IOException;

public class RunBarcodeTest extends BarCodeValidation {


    @Test
    public void BarcodeTest() throws InterruptedException, NotFoundException, IOException {
        TestBarcode();
    }



}
