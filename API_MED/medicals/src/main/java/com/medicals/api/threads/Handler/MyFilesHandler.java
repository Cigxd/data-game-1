package com.medicals.api.threads.Handler;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.bson.Document;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.io.*;
import java.util.*;


public class MyFilesHandler {
    // Function to download a file from the given URL and save it to the specified path
    public String downloadFile(String fileURL, String savePath) throws IOException {
        URL url = new URL(fileURL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        
        // Set the request method and timeout values
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setReadTimeout(5000);
        
        // Get the input stream from the connection
        try (InputStream inputStream = httpURLConnection.getInputStream();
             FileOutputStream fileOutputStream = new FileOutputStream(savePath)) {
            
            // Buffer for reading the file
            byte[] buffer = new byte[4096];
            int bytesRead;
            
            // Write data from input stream to output file
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }
        } finally {
            httpURLConnection.disconnect();
        }
        
        System.out.println("File downloaded to: " + savePath);
        return savePath;
    }
    public String convertXlsxToJson(String xlsxFilePath, String jsonFilePath) throws IOException, InvalidFormatException {
        // Open the Excel file
        FileInputStream fis = new FileInputStream(xlsxFilePath);
        Workbook workbook = WorkbookFactory.create(fis);
        Sheet sheet = workbook.getSheetAt(0);  // Get the first sheet

        // Create a list to store JSON objects
        List<Map<String, Object>> jsonList = new ArrayList<>();

        // Assuming the first row contains the headers
        Row headerRow = sheet.getRow(0);
        int numColumns = headerRow.getPhysicalNumberOfCells();
        List<String> headers = new ArrayList<>();
        
        // Add headers and convert them to camelCase
        for (int i = 0; i < numColumns; i++) {
            String header = headerRow.getCell(i).getStringCellValue();  // Get header value
            headers.add(convertToCamelCase(header));  // Convert header to camelCase
        }

        // Iterate over the rows, starting from row 1 (data rows)
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Map<String, Object> rowData = new HashMap<>();
            for (int j = 0; j < numColumns; j++) {
                Cell cell = row.getCell(j);
                String header = headers.get(j);  // Get camelCase header

                // Handle different cell types (String, Numeric, etc.)
                switch (cell.getCellType()) {
                    case STRING:
                        rowData.put(header, cell.getStringCellValue());
                        break;
                    case NUMERIC:
                        rowData.put(header, cell.getNumericCellValue());
                        break;
                    case BOOLEAN:
                        rowData.put(header, cell.getBooleanCellValue());
                        break;
                    default:
                        rowData.put(header, null);
                        break;
                }
            }
            jsonList.add(rowData);
        }

        // Convert the list to JSON and write it to the specified path
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File(jsonFilePath);
        jsonFile.getParentFile().mkdirs();  // Ensure the directories exist

        // Writing JSON to file in pretty format
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, jsonList);

        // Close the resources
        workbook.close();
        fis.close();

        // Print the success message with line breaks to the console
        System.out.println("\nJSON updated successfully!\n");
        return jsonFilePath;
    }

    // Utility method to convert underscores to camelCase
    private String convertToCamelCase(String str) {
        StringBuilder camelCaseString = new StringBuilder();
        boolean nextUpperCase = false;
        
        for (char c : str.toCharArray()) {
            if (c == '_') {
                nextUpperCase = true;  // Set flag to uppercase the next letter
            } else {
                if (nextUpperCase) {
                    camelCaseString.append(Character.toUpperCase(c));  // Convert to uppercase
                    nextUpperCase = false;
                } else {
                    camelCaseString.append(Character.toLowerCase(c));  // Convert to lowercase
                }
            }
        }
        
        return camelCaseString.toString();
    }



    public void updateDatabaseFromJson(String jsonPath, String databaseName, String collectionName) throws IOException {
        // 1. Connect to MongoDB
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase(databaseName);
            MongoCollection<Document> collection = database.getCollection(collectionName);

            // 2. Remove all data from the collection (clear it)
            collection.deleteMany(new Document());  // Removes all documents

            // 3. Read data from the JSON file into a list of maps
            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, Object>> data = objectMapper.readValue(new File(jsonPath), objectMapper.getTypeFactory().constructCollectionType(List.class, Map.class));

            // 4. Convert the list of maps into Documents for MongoDB insertion
            if (!data.isEmpty()) {
                // Convert Map to Document and insert into MongoDB
                for (Map<String, Object> entry : data) {
                    Document document = new Document(entry);
                    collection.insertOne(document);
                }
            }

            // 5. Print success message
            System.out.println("\nData from JSON has been inserted successfully into the collection!\n");
        }
    }
}
