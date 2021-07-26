package com.mnrc.administration.services.impl;

import com.mnrc.administration.services.ImageService;
import com.sun.xml.messaging.saaj.util.ByteInputStream;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

@Component
public class ImageServiceImpl implements ImageService {

    @Override
    public BufferedImage resizeImageTo_265_By_293(byte[] imageContent) throws IOException {
        BufferedImage originalImage = ImageIO.read(new ByteInputStream(imageContent, imageContent.length));
        if(null == originalImage){
            return null;
        }
        BufferedImage outputImage = new BufferedImage(265, 293, originalImage.getType());

        Graphics2D graphics2D = outputImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, 265, 293, null);
        graphics2D.dispose();
        graphics2D.setComposite(AlphaComposite.Src);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        return outputImage;
    }

    @Override
    public byte[] bufferedImageToByteArray(BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public void createTemporaryProfilePicture(String userId, byte[] imageContent) throws IOException {
        String tempDirectory = System.getProperty("java.io.tmpdir");
        Path tempDirectoryPath = Paths.get(tempDirectory);
        InputStream inputStream = new ByteArrayInputStream(imageContent);
        Path filePath = tempDirectoryPath.resolve(String.format("%s-profile-pic.png", userId));
        Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Override
    public File createTemporaryImageFile() throws IOException {
        int width = 250;
        int height = 250;

        // Constructs a BufferedImage of one of the predefined image types.
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // Create a graphics which can be used to draw into the buffered image
        Graphics2D g2d = bufferedImage.createGraphics();

        // fill all the image with white
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, width, height);

        // create a circle with black
        g2d.setColor(Color.black);
        g2d.fillOval(0, 0, width, height);

        // create a string with yellow
        g2d.setColor(Color.yellow);
        g2d.drawString("MNRC", 50, 120);

        // Disposes of this graphics context and releases any system resources that it is using.
        g2d.dispose();

        // Save as PNG
        File file = File.createTempFile(String.format("temp-image-%s", new Date().getTime()), ".png");
        ImageIO.write(bufferedImage, "png", file);

        return file;
    }
}
