package com.phoenix.distributed.lang.utils.io.crypto;

import com.phoenix.distributed.lang.constant.Const;
import com.phoenix.distributed.lang.constant.Letters;
import com.phoenix.distributed.lang.utils.Arrays1;
import com.phoenix.distributed.lang.utils.crypto.AES;
import com.phoenix.distributed.lang.utils.crypto.Keys;
import com.phoenix.distributed.lang.utils.crypto.enums.CipherAlgorithm;
import com.phoenix.distributed.lang.utils.io.Files1;
import com.phoenix.distributed.lang.utils.io.Streams;

import javax.crypto.SecretKey;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Callable;

/**
 * 文件加密器
 *
 * @author wjj-phoenix
 * @since 2020/7/15 18:02
 */
public class FileEncrypt implements Callable<Boolean> {

    private final InputStream in;

    private final OutputStream out;

    private final String password;

    private final int bufferSize;

    private final boolean autoClose;

    public FileEncrypt(File file, File dest, String password) {
        this(file, dest, password, Const.BUFFER_KB_8);
    }

    public FileEncrypt(String file, String dest, String password) {
        this(file, dest, password, Const.BUFFER_KB_8);
    }

    public FileEncrypt(InputStream in, OutputStream out, String password) {
        this(in, out, password, Const.BUFFER_KB_8);
    }

    public FileEncrypt(String file, String dest, String password, int bufferSize) {
        this(new File(file), new File(dest), password, bufferSize);
    }

    public FileEncrypt(File file, File dest, String password, int bufferSize) {
        this.autoClose = true;
        this.password = password;
        this.bufferSize = bufferSize;
        Files1.touch(dest);
        this.in = Files1.openInputStreamSafe(file);
        this.out = Files1.openOutputStreamSafe(dest);
    }

    public FileEncrypt(InputStream in, OutputStream out, String password, int bufferSize) {
        this.autoClose = false;
        this.password = password;
        this.in = in;
        this.out = out;
        this.bufferSize = bufferSize;
    }

    @Override
    public Boolean call() {
        SecretKey secretKey = Keys.generatorKey(password, CipherAlgorithm.AES);
        try {
            byte[] bs = new byte[bufferSize];
            int read;
            while ((read = in.read(bs)) != -1) {
                byte[] ebs = AES.encrypt(Arrays1.resize(bs, read), secretKey);
                out.write(ebs);
                out.write(Letters.LF);
            }
            out.flush();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            if (autoClose) {
                Streams.close(in);
                Streams.close(out);
            }
        }
    }

}
