package com.example.exemploS3;

import java.util.List;

public class FileManager {

    public static void main(String[] args) {
        manipulateBucket();

    }

    private static void manipulateBucket() {
        OperationsS3 operationsS3 = new OperationsS3(Credentials.ACCESS_KEY, Credentials.SECRET_KEY);
        String bucketName = "first-bucket-test";
        operationsS3.createBucket(bucketName);
        operationsS3.bucketsList().forEach(System.out.println();
        operationsS3.deleteBucket(bucketName);
    }

    private static void manipulateFile() {
        var operationsS3 = new OperationsS3(Credentials.ACCESS_KEY, Credentials.SECRET_KEY);
        var bucketName = "bucket-test";
        var sourceFile = "/home/user/Pessoal/Estudos/aws-s3";
        var destinationFile = "/home/user/Pessoal/Estudos/fotos/foto.jpg";

        operationsS3.enviarArquivo(bucketName, destinationFile, sourceFile);
        operationsS3.listarArquivos(bucketName).forEach(System.out::println);

        operationsS3.deletarArquivo(bucketName, destinationFile);
        operationsS3.listarArquivos(bucketName).forEach(System.out::println);
    }
}
