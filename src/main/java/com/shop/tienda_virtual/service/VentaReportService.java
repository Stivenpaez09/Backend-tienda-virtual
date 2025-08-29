package com.shop.tienda_virtual.service;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.shop.tienda_virtual.dto.FastReadVentasDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;

@Service
public class VentaReportService implements IVentaReportService {
    private final VentaService ventaService;

    @Autowired
    public VentaReportService(VentaService ventaService) {
        this.ventaService = ventaService;
    }



    @Override
    public byte[] generateReportVentas() {
        try {
            List<FastReadVentasDTO> listaVentas = this.ventaService.getShowVentas();
            Document document = new Document(PageSize.A4.rotate());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, out);
            document.open();

            //Se realiza el titulo
            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, Color.RED);
            Paragraph title = new Paragraph("Reporte de Ventas Tienda Virtual", fontTitle);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(20);
            document.add(title);

            //Se genera la tabla con los datos
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{1, 2, 2, 2, 1, 2});

            Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA, 14, Color.WHITE);
            String[] headers = {"ID", "Nombre cliente", "C.C cliente", "Fecha Venta", "Cantidad productos ", "Valor total"};
            for(String header : headers) {
                PdfPCell cell = new PdfPCell(new Phrase(header, fontHeader));
                cell.setBackgroundColor(Color.GRAY);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);
            }

            Font fontData = FontFactory.getFont(FontFactory.HELVETICA, 14, Color.BLACK);
            for(FastReadVentasDTO venta : listaVentas) {
                table.addCell(new Phrase(venta.getCodigo_venta().toString(), fontData));
                table.addCell(new Phrase(venta.getNombreCliente(), fontData));
                table.addCell(new Phrase(venta.getCedulaCliente(), fontData));
                table.addCell(new Phrase(venta.getFecha_venta().toString(), fontData));
                table.addCell(new Phrase(String.valueOf(venta.getCantidadProductos()), fontData));
                table.addCell(new Phrase("$ "+venta.getTotal().toString(), fontData));
            }
            document.add(table);

            //Se genera la fecha de generacion del reporte
            Font fontDate = FontFactory.getFont(FontFactory.HELVETICA, 12, Color.DARK_GRAY);
            Paragraph date = new Paragraph("Fecha de generación: " + LocalDate.now(), fontDate);
            date.setAlignment(Element.ALIGN_RIGHT);
            document.add(date);

            document.close();
            return out.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error al generar el reporte de ventas");
        }
    }
}
