package com.example.secondspringAirlinesproject.service;
import com.example.secondspringAirlinesproject.Dto.AirlinesDto;
import com.example.secondspringAirlinesproject.Exception.AirlineNotFoundException;
import com.example.secondspringAirlinesproject.entity.AirlineFileHandling;
import com.example.secondspringAirlinesproject.entity.Airlines;
import com.example.secondspringAirlinesproject.mapper.AirlinesMapper;
import com.example.secondspringAirlinesproject.repository.Airlinesrepository;
import com.example.secondspringAirlinesproject.repository.FileHandlingRepository;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AirlinesServiceImpl implements AirlinesService {

//Repository Dependency: This line declares a private, final field for the Airlinesrepository.
// It is used to interact with the database for operations related to the Airlines entity.
    @Autowired
    private final Airlinesrepository airlinesRepository;


    // This line declares a field for FileHandlingRepository and uses the @Autowired annotation to inject it.
    // This repository is used to handle file operations related to the AirlineFileHandling entity.
    //The @Autowired annotation tells Spring to inject the FileHandlingRepository bean into this field
    @Autowired
    private FileHandlingRepository fileHandlingRepository;

    @Override
    public AirlinesDto saveOrUpdateAirline(AirlinesDto airlineDto) {
        // Convert DTO to entity
        Airlines airline = AirlinesMapper.mapToAirlines(airlineDto);
        // Save or update entity
        Airlines savedAirline = airlinesRepository.save(airline);
        // Convert entity back to DTO
        return AirlinesMapper.mapToAirlinesDto(savedAirline);
    }


     @Override
     public List<AirlinesDto> getAllAirlines() {
        List<Airlines> airlines = airlinesRepository.findAll();
        //Maps each Airlines entity to an AirlinesDto using the AirlinesMapper.
        return airlines.stream().map(AirlinesMapper::mapToAirlinesDto)
                .collect(Collectors.toList());
     }

    @Override
    public Optional<AirlinesDto> getAirlineById(Long id) {
        Airlines airline = airlinesRepository.findById(id)
                .orElseThrow(() -> new AirlineNotFoundException(
                        "Airline not found with id: " + id));
        return Optional.of(AirlinesMapper.mapToAirlinesDto(airline));
    }

    @Override
    public void deleteAirlineById(Long id) {
        if (!airlinesRepository.existsById(id)) {
            throw new AirlineNotFoundException("Airline not found with id: " + id);
        }
        airlinesRepository.deleteById(id);
    }

    @Override
    public void deleteAirline(AirlinesDto airlineDto) {
        Airlines airline = AirlinesMapper.mapToAirlines(airlineDto);
        if (!airlinesRepository.existsById(airline.getAirlineNumber())) {
            throw new AirlineNotFoundException("Airline not found with id: " + airline.getAirlineNumber());
        }
        airlinesRepository.delete(airline);
    }


    

//MultipartFile is an interface provided by Spring Framework to handle file uploads in web applications.
// It's part of the org.springframework.web.multipart package and is commonly used in Spring Boot
// applications to manage file upload and processing tasks.

    // Method for saving a file into the database
    @Override
    public void saveFile(MultipartFile file, Long airlineNumber) throws Exception {
        Airlines airline = airlinesRepository.findById(airlineNumber)
                .orElseThrow(() -> new Exception("Airline record not found"));


        //AirlineFileHandling fileHandling = new AirlineFileHandling();:
        // Creates a new instance of the AirlineFileHandling entity
        // which will hold the file's metadata and content.
        // Creates a new instance of the AirlineFileHandling entity, which will hold the file's metadata and content.
        AirlineFileHandling fileHandling = new AirlineFileHandling();
        fileHandling.setFilename(file.getOriginalFilename());
        fileHandling.setFiletype(getFileExtension(file.getOriginalFilename()));
        fileHandling.setData(file.getBytes());
        fileHandling.setUploadedtime(LocalDateTime.now());
        fileHandling.setAirlineNumber(airline);

        fileHandlingRepository.save(fileHandling);
    }


    // Utility method to get the file extension
        private String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1);
         }

    // Method for downloading a file from the database
    @Override
    public AirlineFileHandling getFile(Long id) {
        return fileHandlingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found with id " + id));
    }

    // Method for generating a PDF with airline details
    @Override
    public ByteArrayInputStream generatePdf() {
        List<Airlines> airlines = airlinesRepository.findAll();

        Document document = new Document();  //Creates a new Document object from the iText library, which represents the PDF document being created.
        ByteArrayOutputStream out = new ByteArrayOutputStream();//: Creates a ByteArrayOutputStream to capture the PDF output as a byte array. This stream will be used to write the PDF content.

        try {

            PdfWriter.getInstance(document, out);//Associates the PdfWriter with the Document and "ByteArrayOutputStream". This writer is responsible for generating the PDF content.

            document.open();

            Paragraph title = new Paragraph("Airline Details");   //Creates a new Paragraph object with the text "Airline Details", which will be the title of the PDF.
            title.setAlignment(Element.ALIGN_CENTER);  // Centers the title text in the document.
            document.add(title);

           document.add(new Paragraph(" "));
            //Create Table Creates a new table with 8 columns. The number 8 corresponds to the number of columns in the table.

            PdfPTable table = new PdfPTable(8); // 8 columns
            table.setWidthPercentage(100);  //Sets the table width to 100% of the document width,

            addTableHeader(table, "Airline Number");//addTableHeader method creates and adds header cells to the table for each column name specified.
            addTableHeader(table, "Passenger Name");
            addTableHeader(table, "Source Country");
            addTableHeader(table, "Destination Country");
            addTableHeader(table, "Booking Pass Number");
            addTableHeader(table, "Passport Number");
            addTableHeader(table, "Aadhar Number");  // Added Aadhar Number column
            addTableHeader(table, "Mobile Number");  // Added Mobile Number column

            for (Airlines airline : airlines) {
                //The syntax of the ternary conditional operator is:  condition ? valueIfTrue : valueIfFalse
                //table.addCell(...): This adds a cell to the PDF table. For each attribute of the airline object, it checks if the attribute is null.
                table.addCell(airline.getAirlineNumber() != null ? airline.getAirlineNumber().toString() : "N/A");
                table.addCell(airline.getPassengerName() != null ? airline.getPassengerName() : "N/A");
                table.addCell(airline.getSourceCountry() != null ? airline.getSourceCountry() : "N/A");
                table.addCell(airline.getDestinationCountry() != null ? airline.getDestinationCountry() : "N/A");
                table.addCell(airline.getBookingPassNumber() != null ? airline.getBookingPassNumber() : "N/A");
                table.addCell(airline.getPassportNumber() != null ? airline.getPassportNumber() : "N/A");
                table.addCell(airline.getAadhaarNumber() != null ? airline.getAadhaarNumber() : "N/A");
                // Added Aadhar Number data
                table.addCell(airline.getMobileNumber() != null ? airline.getMobileNumber().toString() : "N/A");
            }
                document.add(table);
                document.close();
//Converts the ByteArrayOutputStream, which holds the PDF content, into a ByteArrayInputStream. This stream can be used to read or return the PDF content as a byte stream.
//Return Value: The method returns this ByteArrayInputStream, which allows the PDF to be sent as a response or used further.
            return new ByteArrayInputStream(out.toByteArray());
        } catch (DocumentException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Utility method to add a header to the PDF table
    private void addTableHeader(PdfPTable table, String headerTitle) {
        PdfPCell header = new PdfPCell();//Represents a single cell in the table,  //new PdfPCell(): Creates a new PdfPCell object. By default, this cell is empty and has standard properties.
        header.setPhrase(new Phrase(headerTitle));
        table.addCell(header);
    }

    // Method for generating an Excel file with airline details
    @Override
    public ByteArrayInputStream generateExcel() {
        List<Airlines> airlines = airlinesRepository.findAll();

        try (Workbook workbook = new XSSFWorkbook();//Creates a new XSSFWorkbook object, which represents an Excel workbook in the .xlsx format.
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {//Creates a ByteArrayOutputStream to hold the bytes of the Excel file in memory.
            Sheet sheet = workbook.createSheet("Airline Details");//: Creates a new sheet in the workbook named "Airline Details".

            // Creating the header row
            Row headerRow = sheet.createRow(0);// Creates the first row of the sheet (row index 0) for the header.
            String[] headers = {"Airline Number", "Passenger Name", "Source Country", "Destination Country", "Booking Pass Number", "Passport Number", "Aadhar Number", "Mobile Number"};  // Added Aadhar Number and Mobile Number headers

            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);//Creates a new cell in the header row at index i.
                cell.setCellValue(headers[i]);//Sets the value of the cell to the corresponding header text.
                cell.setCellStyle(createHeaderCellStyle(workbook));// Applies a custom style to the cell created using the createHeaderCellStyle method.
            }

            // Adding data to the Excel sheet
            int rowIndex = 1;
            for (Airlines airline : airlines) {
                Row row = sheet.createRow(rowIndex++); // Creates a new row for each Airlines object, and increments the row index.
                row.createCell(0).setCellValue(airline.getAirlineNumber() != null ? airline.getAirlineNumber() : 0);
                row.createCell(1).setCellValue(airline.getPassengerName() != null ? airline.getPassengerName() : "N/A");
                row.createCell(2).setCellValue(airline.getSourceCountry() != null ? airline.getSourceCountry() : "N/A");
                row.createCell(3).setCellValue(airline.getDestinationCountry() != null ? airline.getDestinationCountry() : "N/A");
                row.createCell(4).setCellValue(airline.getBookingPassNumber() != null ? airline.getBookingPassNumber() : "N/A");
                row.createCell(5).setCellValue(airline.getPassportNumber() != null ? airline.getPassportNumber() : "N/A");
                row.createCell(6).setCellValue(airline.getAadhaarNumber() != null ? airline.getAadhaarNumber() : "N/A");  // Added Aadhar Number data
                row.createCell(0).setCellValue(airline.getMobileNumber() != null ? airline.getMobileNumber() : 0);
            }

            workbook.write(out);// Writes the content of the workbook to the ByteArrayOutputStream
            return new ByteArrayInputStream(out.toByteArray());// Returns a ByteArrayInputStream containing the byte data of the Excel file, which can be used to download or further process the file.
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Utility method to create a header cell style for Excel
    private CellStyle createHeaderCellStyle(Workbook workbook) {//Creates a new CellStyle object.
        CellStyle headerCellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();// Creates a new Font object to customize the font style.
        font.setBold(true);//Creates a new Font object to customize the font style.
        //font.setBold(true);: Sets the font to bold.
        headerCellStyle.setFont(font);//Applies the bold font to the cell style.
        return headerCellStyle;
    }
}
//It uses the Apache POI library to handle Excel file creation and formatting, with a header row containing
// column names and data rows populated with airline information.











