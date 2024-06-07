package lab4.tp4.Controllers;

import Gestor.InstrumentoPrintManager;
import lab4.tp4.DTOs.InstrumentoDTO;
import lab4.tp4.Services.ChartManager;
import lab4.tp4.Services.InstrumentosService;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/Instrumentos")
public class InstrumentosController {

    @Autowired
    public InstrumentosService service;

    @GetMapping("{id}")
    public ResponseEntity<?> GetInstrumento(@PathVariable("id") Integer id){
        try {
            return new ResponseEntity<>(service.Get(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> GetAllInstrumentos(){
        return new ResponseEntity<>(service.GetAll(), HttpStatus.OK);
    }

    @GetMapping("List/{id}")
    public ResponseEntity<?> GetAllInstrumentosByCategoria(@PathVariable("id") Integer id){
        return new ResponseEntity<>(service.GetAllByCategoria(id), HttpStatus.OK);
    }

    @PostMapping("Create/Massive")
    public ResponseEntity<?> CreateInstrumentos(@RequestBody Map<String, List<InstrumentoDTO>> instrumentosData){
        try {
            service.CreateMassive(instrumentosData.get("instrumentos"));
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("Create")
    public ResponseEntity<?> CreateInstrumento(@RequestBody InstrumentoDTO instrumentoDTO){
        try {
            var res = service.Create(instrumentoDTO);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> UpdateInstrumento(@RequestBody InstrumentoDTO instrumentoDTO, @PathVariable Integer id){
        try {
            var res = service.Update(instrumentoDTO, id);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> DeleteInstrumento(@PathVariable Integer id){
        try {
            var res = service.Delete(id);
            return new ResponseEntity<>(res, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("api/downloadExcelPlatos")
    public ResponseEntity<byte[]> downloadExcelPlatos() throws SQLException {
        try {
            InstrumentoPrintManager mPrintPlato = new InstrumentoPrintManager();
            SXSSFWorkbook libroExcel = mPrintPlato.imprimirExcelPlatos();
            // Escribir el libro de trabajo en un flujo de bytes
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            libroExcel.write(outputStream);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            headers.setContentDispositionFormData("attachment", "datos.xlsx");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("http://localhost:8080/api/Instrumentos/api/downloadPdfPlato/{idPlato}")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable String idPlato) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            InstrumentoPrintManager mPrintPlato = new InstrumentoPrintManager();
            // Crear un nuevo documento
            mPrintPlato.imprimirPlatoPdf(Integer.valueOf(idPlato), outputStream);

            // Establecer las cabeceras de la respuesta
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/pdf"));
            headers.setContentDispositionFormData("attachment", "documento.pdf");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            // Devolver el archivo PDF como parte de la respuesta HTTP
            return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("api/datachartline")
//    public List<List<Object>> getDataChartLine() throws SQLException {
//        List<List<Object>> data = new ArrayList<>();
//        data.add(Arrays.asList("Articulo", "Precio Compra", "Precio Venta"));
//
//        ChartManager mChart = new ChartManager();
//        ResultSet rs = mChart.getDatosChart();
//        while (rs.next()) {
//            data.add(Arrays.asList(rs.getString("denominacionArticulo"), FuncionApp.getFormatNumberToDecimal(rs.getDouble("montoCompra")), FuncionApp.getFormatNumberToDecimal(rs.getDouble("montoVenta"))));
//        }
//        return data;
//    }

    @GetMapping("api/datachartpie")
    public List<List<Object>> getDataChartPie() throws SQLException {
        List<List<Object>> data = new ArrayList<>();
        data.add(Arrays.asList("Instrumento", "Cantidad"));

        ChartManager mChart = new ChartManager();
        ResultSet rs = mChart.getDatosChart();
        while (rs.next()) {
            data.add(Arrays.asList(rs.getString("instrumento"), rs.getInt("cantidad_vendida")));
        }
        return data;
    }

}
