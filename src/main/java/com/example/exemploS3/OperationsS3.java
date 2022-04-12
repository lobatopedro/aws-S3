package com.example.exemploS3;

import java.util.List;
import java.util.stream.Collectors;

public class OperationsS3 {

    private final AmazonS3 clientS3;

    public OperationsS3(String accessKey, String secretKey) {
        var credentials = new BasicAWSCredentials(accessKey, secretKey);
        clientS3 = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1)
                .build();
    }

    public void createBucket(final String bucketName) {
        if(clientS3.doesBucketExistV2(bucketName)) {
            System.out.println("The name of this bucket has already been used [" + bucketName + "] + Try another name");
            return;
        }
        clientS3.createBucket(bucketName);
    }

    public List<String> bucketsList() {
        return clientS3.bucketsList()
                .stream()
                .map(bucket -> bucket.getName())
                .collect(Collectors.toList());
    }

    public void deleteBucket(final String bucketName) {
        if (!clientS3.doesBucketExistV2(bucketName)) {
            System.out.println("The name of this bucket has already been used [" + bucketName + "] + Try another name");
            return;
        }
        clientS3.deleteBucket(bucketName);
    }

    public void sendFiles(String bucketName, String destinationFile, String sourceFile) {
        if (!clientS3.doesBucketExistV2(bucketName)) {
            System.out.println("The given bucket does not exist [" + bucketName + "] ");
            return;
        }
        clientS3.putObject(bucketName, destinationFile, sourceFile);
    }

    public List<String> listFiles(String bucketName) {
        var objectsList = clientS3.listObjects(bucketName);
        return objectsList.getObjectSummaries()
                .stream()
                .map(sumary -> sumary.getKey())
                .collect(Collectors.toList());
    }

    public void deleteFiles(String bucketName, String fileKey) {
        clientS3.deleteObject(bucketName, fileKey);
    }
}
