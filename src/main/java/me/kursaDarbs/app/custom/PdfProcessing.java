package me.kursaDarbs.app.custom;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class PdfProcessing {
    private String cinema;
    private String city;
    private String address;
    private String movie;
    private Date date;
    private Integer row;
    private Integer col;
    private String orderNumber;
    private double price;


    public PdfProcessing(String cinema, String city, String address, String movie, Date date,
                         Integer row, Integer col, String orderNumber, double price) {
        this.cinema = cinema;
        this.city = city;
        this.address = address;
        this.movie = movie;
        this.date = date;
        this.row = row;
        this.col = col;
        this.orderNumber = orderNumber;
        this.price = price;
    }
    public String GetOrderNumber() {
        return orderNumber;
    }

    public void WritePdf(OutputStream outputStream) throws Exception {
        Rectangle pagesize = PageSize.A5;

        Document document = new Document();
        document.setPageSize(pagesize);
        document.setMargins(2, 2, 2, 2);
        PdfWriter.getInstance(document, outputStream);
        document.open();
        Font regular = new Font(Font.FontFamily.HELVETICA, 10);
        Font bold = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 30, Font.BOLD);

        Paragraph titleParagraph = new Paragraph(cinema, titleFont);
        titleParagraph.setAlignment(Paragraph.ALIGN_CENTER);

        Paragraph cityParagraph = new Paragraph("City: ", bold);
        cityParagraph.add(new Chunk(city, regular));

        Paragraph addressParagraph = new Paragraph("Address: ", bold);
        addressParagraph.add(new Chunk(address, regular));

        Paragraph movieParagraph = new Paragraph("Movie: ", bold);
        movieParagraph.add(new Chunk(movie, regular));

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");

        Paragraph dateParagraph = new Paragraph("Date: ", bold);
        dateParagraph.add(new Chunk(df.format(date), regular));

        Paragraph rowParagraph = new Paragraph("Row: ", bold);
        rowParagraph.add(new Chunk(Integer.toString(row), regular));

        Paragraph colParagraph = new Paragraph("Column: ", bold);
        colParagraph.add(new Chunk(Integer.toString(col), regular));

        Paragraph orderNumberParagraph = new Paragraph("Order-number: ", bold);
        orderNumberParagraph.add(new Chunk(orderNumber, regular));

        Paragraph priceParagraph = new Paragraph("Price: ", bold);
        priceParagraph.add(new Chunk(String.valueOf(price) + " $", regular));

        document.add(titleParagraph);
        document.add(cityParagraph);
        document.add(addressParagraph);
        document.add(movieParagraph);
        document.add(dateParagraph);
        document.add(rowParagraph);
        document.add(colParagraph);
        document.add(orderNumberParagraph);
        document.add(priceParagraph);

        document.close();
    }





}
