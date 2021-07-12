package com.mnrc.services.impl;

import com.mnrc.services.ImageService;
import com.sun.xml.messaging.saaj.util.ByteInputStream;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class ImageServiceImpl implements ImageService {

    @Override
    public BufferedImage resizeImageTo_265_By_293(byte[] imageContent) throws IOException {
        BufferedImage originalImage = ImageIO.read(new ByteInputStream(imageContent, imageContent.length));
        if(null == originalImage){
            return null;
        }
        BufferedImage outputImage = new BufferedImage(265, 293, originalImage.getType());

        Graphics2D g = outputImage.createGraphics();
        g.drawImage(originalImage, 0, 0, 265, 293, null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        return outputImage;
    }

    public byte[] bufferedImageToByteArray(BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
