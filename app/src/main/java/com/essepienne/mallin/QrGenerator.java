package com.essepienne.mallin;

import android.graphics.Bitmap;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public final class QrGenerator {

    private final String code;
    private final int dimension;

    public QrGenerator(String code, int dimension) {
        this.code = code;
        this.dimension = dimension;
    }

    public Bitmap QRGenerator(){
        QRGEncoder qrgEncoder = new QRGEncoder(code, null, QRGContents.Type.TEXT, dimension);
        try {
            return qrgEncoder.encodeAsBitmap();
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

}
