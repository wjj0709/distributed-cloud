package com.phoenix.distributed.lang.utils.crypto.symmetric;

import com.phoenix.distributed.lang.utils.Exceptions;
import com.phoenix.distributed.lang.utils.crypto.enums.CipherAlgorithm;
import com.phoenix.distributed.lang.utils.crypto.enums.PaddingMode;
import com.phoenix.distributed.lang.utils.crypto.enums.WorkingMode;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;

import static com.phoenix.distributed.lang.utils.codec.Base64s.decode;
import static com.phoenix.distributed.lang.utils.codec.Base64s.encode;

/**
 * ECB 模式非对称加密 AES DES 3DES
 *
 * @author wjj-phoenix
 * @since 2020/11/3 14:07
 */
public class EcbSymmetric extends BaseSymmetric {

    public EcbSymmetric(CipherAlgorithm algorithm, SecretKey secretKey) {
        this(algorithm, PaddingMode.PKCS5_PADDING, secretKey);
    }

    public EcbSymmetric(CipherAlgorithm algorithm, PaddingMode paddingMode, SecretKey secretKey) {
        super(algorithm, WorkingMode.ECB, paddingMode, secretKey);
    }

    @Override
    public byte[] encrypt(byte[] plain) {
        try {
            Cipher cipher = super.getCipher();
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return encode(cipher.doFinal(this.zeroPadding(plain, cipher.getBlockSize())));
        } catch (Exception e) {
            throw Exceptions.encrypt("encrypt data error", e);
        }
    }

    @Override
    public byte[] decrypt(byte[] text) {
        try {
            Cipher cipher = super.getCipher();
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return this.clearZeroPadding(cipher.doFinal(decode(text)));
        } catch (Exception e) {
            throw Exceptions.decrypt("decrypt data error", e);
        }
    }

}

