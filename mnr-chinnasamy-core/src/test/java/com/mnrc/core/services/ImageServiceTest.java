package com.mnrc.core.services;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageServiceTest {

    @Autowired
    private ImageService imageService;

    @Test
    public void testNotNull(){
        Assert.assertNotNull(this.imageService);
    }

    @Test
    public void testResizeImageTo_265_By_293() throws IOException {
        InputStream inputStream = this.getClass().getResourceAsStream("/images/user-profile-pic.png");
        int availableBytes = inputStream.available();
        byte[] content = new byte[availableBytes];
        inputStream.read(content);
        inputStream.close();

        BufferedImage bufferedImage = this.imageService.resizeImageTo_265_By_293(content);

        Assert.assertNotNull(bufferedImage);
    }

    @Test
    public void testBufferedImageToByteArray() throws IOException {
        InputStream inputStream = this.getClass().getResourceAsStream("/images/user-profile-pic.png");
        int availableBytes = inputStream.available();
        byte[] content = new byte[availableBytes];
        inputStream.read(content);
        inputStream.close();
        BufferedImage bufferedImage = this.imageService.resizeImageTo_265_By_293(content);

        byte[] result = this.imageService.bufferedImageToByteArray(bufferedImage);

        Assert.assertNotNull(result);
        Assert.assertTrue(content.length > result.length);
    }

    @Test
    public void testCreateTemporaryProfilePicture() throws IOException {
        InputStream inputStream = this.getClass().getResourceAsStream("/images/user-profile-pic.png");
        int availableBytes = inputStream.available();
        byte[] content = new byte[availableBytes];
        inputStream.read(content);
        inputStream.close();
        BufferedImage bufferedImage = this.imageService.resizeImageTo_265_By_293(content);
        byte[] result = this.imageService.bufferedImageToByteArray(bufferedImage);

        this.imageService.createTemporaryProfilePicture(String.valueOf(new Date().getTime()), result);

        Assert.assertNotNull(result);
        Assert.assertTrue(content.length > result.length);
    }

    @Test
    public void testCreateTemporaryImageFile() throws IOException {
        File imageFile = this.imageService.createTemporaryImageFile();
        Assert.assertNotNull(imageFile);
    }

}
