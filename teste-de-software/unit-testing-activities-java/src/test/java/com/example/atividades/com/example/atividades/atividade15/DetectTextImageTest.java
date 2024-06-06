package com.example.atividades.atividade15;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import software.amazon.awssdk.services.rekognition.RekognitionClient;
import software.amazon.awssdk.services.rekognition.model.DetectTextRequest;
import software.amazon.awssdk.services.rekognition.model.DetectTextResponse;
import software.amazon.awssdk.services.rekognition.model.TextDetection;

public class DetectTextImageTest {

    @Test
    public void testDefaultConstructor() {
        DetectTextImage dti = new DetectTextImage();
        assertEquals("data/img-example-for-aws-task.jpg", dti.sourceImage);
        assertNotNull(dti.rekClient);
    }

    @Test
    public void testConstructorWithParameters() {
        RekognitionClient rekClient = mock(RekognitionClient.class);
        String sourceImage = "test.jpg";
        DetectTextImage dti = new DetectTextImage(sourceImage, rekClient);
        assertEquals(sourceImage, dti.sourceImage);
        assertEquals(rekClient, dti.rekClient);
    }

    @Test
    public void testDetectTextLabels() {
        RekognitionClient rekClient = mock(RekognitionClient.class);
        String sourceImage = "data/img-example-for-aws-task.jpg";
        String resultFilePath = "data/detect-text-results.txt";
        DetectTextImage dti = new DetectTextImage(sourceImage, rekClient);

        TextDetection textDetection = TextDetection.builder()
                .detectedText("some text")
                .confidence(99.0f)
                .id(1)
                .type("LINE")
                .build();

        List<TextDetection> textDetections = Arrays.asList(textDetection);
        DetectTextResponse response = DetectTextResponse.builder()
                .textDetections(textDetections)
                .build();

        when(rekClient.detectText(any(DetectTextRequest.class))).thenReturn(response);

        dti.detectTextLabels(resultFilePath);

        ArgumentCaptor<DetectTextRequest> argumentCaptor = ArgumentCaptor.forClass(DetectTextRequest.class);
        verify(rekClient).detectText(argumentCaptor.capture());
    }

    @Test
    public void testDetectTextLabelsWithInvalidResultFilePath() {
        RekognitionClient rekClient = mock(RekognitionClient.class);
        String sourceImage = "data/img-example-for-aws-task.jpg";
        String resultFilePath = "invalid/result.txt";
        DetectTextImage dti = new DetectTextImage(sourceImage, rekClient);

        when(rekClient.detectText(any(DetectTextRequest.class))).thenAnswer(invocation -> {
            Method method = DetectTextImage.class.getDeclaredMethod("saveResultToTextFile", List.class, String.class);
            method.setAccessible(true);
            method.invoke(dti, Arrays.asList(TextDetection.builder().build()), resultFilePath);
            return null;
        });

        assertThrows(NullPointerException.class, () -> dti.detectTextLabels(resultFilePath));
    }

    @Test
    public void testDetectTextLabelsFileNotFoundException() {
        RekognitionClient rekClient = mock(RekognitionClient.class);
        String sourceImage = "invalid.jpg";
        String resultFilePath = "data/detect-text-results.txt";
        DetectTextImage dti = new DetectTextImage(sourceImage, rekClient);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        dti.detectTextLabels(resultFilePath);

        assertTrue(outContent.toString().contains("invalid.jpg"));
    }
}