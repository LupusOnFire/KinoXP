package dk.kinoxp.web.model.services;

import java.io.*;
import java.net.URL;

public class DownloadService {
    public void downloadFile(String upstreamPath, File dstFile) throws IOException {
        URL url = new URL(upstreamPath);
        InputStream in = new BufferedInputStream(url.openStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n = 0;
        while (-1 != (n = in.read(buf))) {
            out.write(buf, 0, n);
        }
        out.close();
        in.close();
        byte[] response = out.toByteArray();

        System.out.println(dstFile.getAbsolutePath());
        dstFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(dstFile);
        fos.write(response);
        fos.close();
    }
}
