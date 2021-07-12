package com.mnrc.services;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface ImageService {

    public BufferedImage resizeImageTo_265_By_293(byte[] imageContent) throws IOException;

    public byte[] bufferedImageToByteArray(BufferedImage bufferedImage) throws IOException;

}
