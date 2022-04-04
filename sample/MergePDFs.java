package sample;


import org.apache.pdfbox.multipdf.PDFMergerUtility;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MergePDFs {
    public boolean merge(List<File> files, String loc) throws Exception {
        PDFMergerUtility pdfMergerUtility = new PDFMergerUtility();
        pdfMergerUtility.setDestinationFileName(loc);
        for(File file: files) pdfMergerUtility.addSource(file);

        pdfMergerUtility.mergeDocuments();
        return true;
    }
}
