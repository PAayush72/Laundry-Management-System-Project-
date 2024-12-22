// PdfBean.java
import cdi.orderBean;
import cdi.orderItemBean;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Tblorderitem;
import entities.Tblservice;
import java.io.ByteArrayOutputStream;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Named("PdfBean")
@RequestScoped
public class PdfBean {

    @Inject
    private orderItemBean orderItemBean;

    @Inject
    private orderBean orderBean;

    public String generatePdf() {
        // Create a new document
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            // Generate the PDF content
            PdfWriter.getInstance(document, baos);
            document.open();

            // Add a paragraph with some content
            Font boldFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            document.add(new Paragraph("Order Details", boldFont));

            // Fetch order items
            List<Tblorderitem> orderItems = new ArrayList<>(orderItemBean.getAllOrderitemByOrderId(orderBean.getOrder().getOrderId()));

            // Create a table with appropriate columns
            PdfPTable table = new PdfPTable(6); // Adjust the number of columns as needed
            table.addCell("OrderItemId");
            table.addCell("Material");
            table.addCell("Qty");
            table.addCell("ServiceId");
            table.addCell("Service Charge");
            table.addCell("Total Charge");

            // Populate the table with order item details
            for (Tblorderitem item : orderItems) {
                table.addCell(String.valueOf(item.getOrderItemId()));
                table.addCell(item.getMaterial());
                table.addCell(String.valueOf(item.getQty()));
                table.addCell(String.valueOf(item.getServiceId().getServicesId()));
                Tblservice service = orderItemBean.getAllServiceById(item.getServiceId().getServicesId());
                table.addCell(String.valueOf(service.getCharge()));
                table.addCell(String.valueOf(item.getQty() * service.getCharge()));
            }

            // Add the table to the document
            document.add(table);

            // Calculate and add the grand total
            double grandTotal = orderItemBean.calculateGrandTotal(orderItems);
            document.add(new Paragraph("Grand Total: " + grandTotal, boldFont));

            // Close the document
            document.close();

            // Get the response and set the content type
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=generatedPdf.pdf");

            // Write the PDF to the response output stream
            response.getOutputStream().write(baos.toByteArray());
            facesContext.responseComplete(); // Mark the response as complete

        } catch (Exception e) {
            e.printStackTrace(); // Log error for debugging
        }

        return null; // No navigation outcome is necessary here
    }
}
