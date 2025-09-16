package com.phoenix.distributed.lang.utils.io.compress.zip;

import com.phoenix.distributed.lang.constant.Const;
import com.phoenix.distributed.lang.utils.io.Files1;
import com.phoenix.distributed.lang.utils.io.Streams;
import com.phoenix.distributed.lang.utils.io.compress.BaseFileDecompressor;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * zip 解压器
 *
 * @author wjj-phoenix
 * @since 2021/9/27 21:08
 */
public class ZipDecompressor extends BaseFileDecompressor {

    private ZipFile zipFile;

    public ZipDecompressor() {
        this(Const.SUFFIX_ZIP);
    }

    public ZipDecompressor(String suffix) {
        super(suffix);
    }

    @Override
    public void doDecompress() throws Exception {
        try {
            this.zipFile = new ZipFile(decompressFile);
            Enumeration<?> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                File file = new File(decompressTargetPath, entry.getName());
                if (entry.isDirectory()) {
                    Files1.mkdirs(file);
                } else {
                    try (InputStream in = zipFile.getInputStream(entry);
                         OutputStream out = Files1.openOutputStreamFast(file)) {
                        Streams.transfer(in, out);
                    }
                }
            }
        } finally {
            Streams.close(zipFile);
        }
    }

    @Override
    public ZipFile getCloseable() {
        return zipFile;
    }

}
