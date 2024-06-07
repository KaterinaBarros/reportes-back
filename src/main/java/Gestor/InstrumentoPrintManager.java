package Gestor;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import lab4.tp4.DTOs.InstrumentoDTO;
import lab4.tp4.Services.InstrumentosService;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import lab4.tp4.Mappers.InstrumentoMapper;

public class InstrumentoPrintManager {

    protected static Font titulo = new Font(Font.COURIER, 14, Font.BOLD);
    protected static Font redFont = new Font(Font.COURIER, 12, Font.NORMAL, Color.RED);
    protected static Font textoHeader = new Font(Font.COURIER, 17, Font.BOLD);
    protected static Font texto = new Font(Font.COURIER, 11, Font.NORMAL);
    protected static Font textoBold = new Font(Font.COURIER, 11, Font.BOLD);
    protected static Font textoMini = new Font(Font.COURIER, 8, Font.NORMAL);
    protected static Font textoMiniBold = new Font(Font.COURIER, 8, Font.BOLD);
    protected static Font textoBig = new Font(Font.COURIER, 50, Font.BOLD);
    LocalDateTime hoy = LocalDateTime.now();
    public InstrumentoMapper instrumentoMapper;
    public SXSSFWorkbook imprimirExcelPlatos() throws IOException, SQLException{

        // Se crea el libro
        SXSSFWorkbook libro = new SXSSFWorkbook(50); //importante !! el 50 indica el tamaño de paginación
        // Se crea una hoja dentro del libro
        SXSSFSheet hoja = libro.createSheet();
        //estilo
        XSSFFont font = (XSSFFont) libro.createFont();
        font.setBold(true);
        XSSFCellStyle style = (XSSFCellStyle) libro.createCellStyle();
        style.setFont(font);
        int nroColumna = 0;
        // Se crea una fila dentro de la hoja
        SXSSFRow row = hoja.createRow(0);
        // Se crea una celda dentro de la fila
        SXSSFCell cell = row.createCell(nroColumna);
        cell.setCellValue("Fecha Pedido");
        cell.setCellStyle(style);
        cell = row.createCell(++nroColumna);
        cell.setCellValue("Instrumento");
        cell.setCellStyle(style);
        cell = row.createCell(++nroColumna);
        cell.setCellValue("Marca");
        cell.setCellStyle(style);
        cell = row.createCell(++nroColumna);
        cell.setCellValue("Modelo");
        cell = row.createCell(++nroColumna);
        cell.setCellValue("Cantidad");
        cell = row.createCell(++nroColumna);
        cell.setCellValue("Precio");
        cell = row.createCell(++nroColumna);
        cell.setCellValue("Subtotal");
        cell.setCellStyle(style);

        int nroFila = 1;
        nroColumna = 0;
        InstrumentosService mPlato = new InstrumentosService(instrumentoMapper);
        List<InstrumentoDTO> platos = mPlato.GetAll();
        if(platos != null){
            for (InstrumentoDTO plato : platos) {
                nroColumna = 0;
                row = hoja.createRow(nroFila);
                ++nroFila;
                cell = row.createCell(nroColumna);
                cell.setCellValue(String.valueOf(hoy));
                cell = row.createCell(++nroColumna);
                cell.setCellValue(plato.getInstrumento());
                cell = row.createCell(++nroColumna);
                cell.setCellValue(plato.getMarca());
                cell = row.createCell(++nroColumna);
                cell.setCellValue(plato.getModelo());
                cell = row.createCell(++nroColumna);
                cell.setCellValue(plato.getCantidadVendida());
                cell = row.createCell(++nroColumna);
                cell.setCellValue(plato.getPrecio());
                cell = row.createCell(++nroColumna);
                cell.setCellValue(plato.getPrecio()*plato.getCantidadVendida());
            }
        }
        return libro;

    }

    public static void addMetaData(Document document) {
        document.addTitle("Reporte PDF");
        document.addSubject("Ejemplo PDF");
        document.addKeywords("PDF");
        document.addAuthor("Gerardo Magni");
        document.addCreator("Gerardo Magni");
    }

    public static void addEmptyLine(Document document, int number) {
        try {
            Paragraph espacio = new Paragraph();
            for (int i = 0; i < number; i++) {
                espacio.add(new Paragraph(" "));
            }
            document.add(espacio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLineaReporte(Document document) throws DocumentException, MalformedURLException, IOException{
        PdfPTable linea = new PdfPTable(1);
        linea.setWidthPercentage(100.0f);
        PdfPCell cellOne = new PdfPCell(new Paragraph(""));
        cellOne.setBorder(Rectangle.BOTTOM);
        cellOne.setBorder(Rectangle.TOP);
        linea.addCell(cellOne);

        document.add(linea);
    }

    public void imprimirPlatoPdf(Integer idPlato, ByteArrayOutputStream outputStream) throws SQLException  {
        try{
            Document document = new Document(PageSize.A4, 30, 30, 0, 30);//float marginLeft, float marginRight, float marginTop, float marginBottom
//            addMetaData(document);

            InstrumentosService mPlato = new InstrumentosService(instrumentoMapper);
            InstrumentoDTO plato = mPlato.Get(idPlato);

            PdfWriter.getInstance(document, outputStream); // Code 2
            document.open();
            //Encabezado
//            PdfPTable tableCabecera = new PdfPTable(2);
//            tableCabecera.setWidthPercentage(100f);
//
//            Image imgCabeceraLeft = Image.getInstance("http://localhost:8080/images/images.jpg");
//            imgCabeceraLeft.scalePercent(70f);
//            imgCabeceraLeft.setBorderColorBottom(Color.BLACK);
//            Image imgCabeceraRight = Image.getInstance("http://localhost:8080/images/utn.png");
//            imgCabeceraRight.scalePercent(70f);
//            imgCabeceraRight.setBorderColorBottom(Color.BLACK);
//
//            PdfPCell logoBuenSabor = new PdfPCell(imgCabeceraLeft);
//            logoBuenSabor.setBorder(Rectangle.NO_BORDER);
//            logoBuenSabor.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
//            PdfPCell logoUTN = new PdfPCell(imgCabeceraRight);
//            logoUTN.setBorder(Rectangle.NO_BORDER);
//            logoUTN.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
//
//            tableCabecera.addCell(logoBuenSabor);
//            tableCabecera.addCell(logoUTN);
//
//            document.add(tableCabecera);
//
//            addEmptyLine(document, 1);
//            setLineaReporte(document);
            //Fin encabezado

            Paragraph paragraph = new Paragraph(plato.getInstrumento().toUpperCase(), titulo);
            paragraph.setAlignment(Element.ALIGN_LEFT);
            document.add(paragraph);

            Image imgPlato = Image.getInstance("http://localhost:8080/images/"+plato.getImagen());
            imgPlato.scaleAbsolute(500f, 300f);

            document.add(imgPlato);

            addEmptyLine(document, 2);

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);

            PdfPCell celdaIzq = new PdfPCell(new Phrase("Instrumento:", textoBold));
            celdaIzq.setBorder(Rectangle.NO_BORDER);
            PdfPCell celdaDer = new PdfPCell(new Phrase(plato.getInstrumento(), texto));
            celdaDer.setBorder(Rectangle.NO_BORDER);
            table.addCell(celdaIzq);
            table.addCell(celdaDer);

            celdaIzq = new PdfPCell(new Phrase("Precio:", textoBold));
            celdaIzq.setBorder(Rectangle.NO_BORDER);
            celdaDer = new PdfPCell(new Phrase(String.valueOf(plato.getPrecio()), texto));
            celdaDer.setBorder(Rectangle.NO_BORDER);
            table.addCell(celdaIzq);
            table.addCell(celdaDer);

            celdaIzq = new PdfPCell(new Phrase("Marca:", titulo));
            celdaIzq.setBorder(Rectangle.NO_BORDER);
            celdaDer = new PdfPCell(new Phrase(plato.getMarca(), titulo));
            celdaDer.setBorder(Rectangle.NO_BORDER);
            table.addCell(celdaIzq);
            table.addCell(celdaDer);

            celdaIzq = new PdfPCell(new Phrase("Modelo:", textoBold));
            celdaIzq.setBorder(Rectangle.NO_BORDER);
            celdaDer = new PdfPCell(new Phrase(plato.getModelo(), texto));
            celdaDer.setBorder(Rectangle.NO_BORDER);
            table.addCell(celdaIzq);
            table.addCell(celdaDer);


            celdaIzq = new PdfPCell(new Phrase("Envio:", textoBold));
            celdaIzq.setBorder(Rectangle.NO_BORDER);
            celdaDer = new PdfPCell(new Phrase(plato.getCostoEnvio(), texto));
            celdaDer.setBorder(Rectangle.NO_BORDER);
            table.addCell(celdaIzq);
            table.addCell(celdaDer);

            document.add(table);

            document.close();

        }catch(DocumentException | IOException e){
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
