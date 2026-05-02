// PdfBean.java
import cdi.orderBean;
import cdi.orderItemBean;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
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
import java.text.SimpleDateFormat;
import java.util.Date;

@Named("PdfBean")
@RequestScoped
public class PdfBean {

    @Inject
    private orderItemBean orderItemBean;

    @Inject
    private orderBean orderBean;

    // Define colors
    private static final BaseColor PRIMARY_COLOR = new BaseColor(28, 62, 114);  // #1C3E72
    private static final BaseColor LIGHT_GRAY = new BaseColor(242, 242, 242);

    public String generatePdf() {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, baos);
            document.open();

            // Add title
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLUE);
            document.add(new Paragraph("Laundry Service Bill", titleFont));
            document.add(new Paragraph("\n"));

            // Add customer details
            Font normalFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
            document.add(new Paragraph("Customer Name: " + orderBean.getOrder().getCustomerId().getCustomerName(), normalFont));
            document.add(new Paragraph("Phone: " + orderBean.getOrder().getCustomerId().getPhno(), normalFont));
            document.add(new Paragraph("Order ID: " + orderBean.getOrder().getOrderId(), normalFont));
            document.add(new Paragraph("Date: " + new SimpleDateFormat("dd/MM/yyyy").format(orderBean.getOrder().getOrderDate()), normalFont));
            document.add(new Paragraph("\n"));

            // Create table
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);

            // Add headers
            table.addCell("OrderItemId");
            table.addCell("Material");
            table.addCell("Qty");
            table.addCell("Service");
            table.addCell("Rate");
            table.addCell("Amount");

            // Add items
            List<Tblorderitem> orderItems = new ArrayList<>(orderItemBean.getAllOrderitemByOrderId(orderBean.getOrder().getOrderId()));
            for (Tblorderitem item : orderItems) {
                table.addCell(String.valueOf(item.getOrderItemId()));
                table.addCell(item.getMaterial());
                table.addCell(String.valueOf(item.getQty()));
                table.addCell(String.valueOf(item.getServiceId().getServiceType()));
                Tblservice service = orderItemBean.getAllServiceById(item.getServiceId().getServicesId());
                table.addCell(String.valueOf(service.getCharge()));
                table.addCell(String.valueOf(item.getQty() * service.getCharge()));
            }

            document.add(table);
            document.add(new Paragraph("\n"));

            // Add total
            double grandTotal = orderItemBean.calculateGrandTotal(orderItems);
            Font boldFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            document.add(new Paragraph("Grand Total: ₹" + String.format("%.2f", grandTotal), boldFont));

            document.close();

            // Set response
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=LaundryBill.pdf");
            response.getOutputStream().write(baos.toByteArray());
            facesContext.responseComplete();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private void addHeader(Document document) throws Exception {
        // Add logo
        String logoPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/assets/img/logo/logo.png");
        Image logo = Image.getInstance(logoPath);
        logo.scaleToFit(150, 150);
        logo.setAlignment(Element.ALIGN_LEFT);
        document.add(logo);

        // Add company info
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD, PRIMARY_COLOR);
        Font infoFont = new Font(Font.FontFamily.HELVETICA, 11, Font.NORMAL, BaseColor.DARK_GRAY);

        Paragraph companyInfo = new Paragraph();
        companyInfo.add(new Phrase("Your Laundry Service\n", titleFont));
        companyInfo.add(new Phrase("\n"));
        companyInfo.add(new Phrase("789/A, Green road NYC-9089\n", infoFont));
        companyInfo.add(new Phrase("Phone: (90) 898 789-8957\n", infoFont));
        companyInfo.add(new Phrase("Email: laundry@567.com\n", infoFont));
        companyInfo.setAlignment(Element.ALIGN_RIGHT);
        document.add(companyInfo);
    }

    private void addBillInfo(Document document) throws Exception {
        Font headingFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, PRIMARY_COLOR);
        Font normalFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.DARK_GRAY);

        // Add Invoice heading
        Paragraph invoiceHeading = new Paragraph("INVOICE", headingFont);
        invoiceHeading.setAlignment(Element.ALIGN_CENTER);
        invoiceHeading.setSpacingBefore(30);
        invoiceHeading.setSpacingAfter(30);
        document.add(invoiceHeading);

        // Create customer and order info table
        PdfPTable infoTable = new PdfPTable(2);
        infoTable.setWidthPercentage(100);

        // Customer Information
        PdfPCell customerCell = new PdfPCell();
        customerCell.setBorder(Rectangle.NO_BORDER);
        customerCell.addElement(new Phrase("Bill To:\n", headingFont));
        customerCell.addElement(new Phrase(orderBean.getOrder().getCustomerId().getCustomerName() + "\n", normalFont));
        customerCell.addElement(new Phrase(orderBean.getOrder().getCustomerId().getCustomerAddress() + "\n", normalFont));
        customerCell.addElement(new Phrase("Phone: " + orderBean.getOrder().getCustomerId().getPhno(), normalFont));
        infoTable.addCell(customerCell);

        // Order Information
        PdfPCell orderCell = new PdfPCell();
        orderCell.setBorder(Rectangle.NO_BORDER);
        orderCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        orderCell.addElement(new Phrase("Order Details:\n", headingFont));
        orderCell.addElement(new Phrase("Order ID: " + orderBean.getOrder().getOrderId() + "\n", normalFont));
        orderCell.addElement(new Phrase("Date: " + new SimpleDateFormat("dd/MM/yyyy").format(orderBean.getOrder().getOrderDate()) + "\n", normalFont));
        orderCell.addElement(new Phrase("Status: " + orderBean.getOrder().getStatus(), normalFont));
        infoTable.addCell(orderCell);

        document.add(infoTable);
    }

    private void addLineSeparator(Document document) throws Exception {
        LineSeparator line = new LineSeparator();
        line.setLineColor(PRIMARY_COLOR);
        document.add(new Chunk(line));
    }

    private void addOrderItemsTable(Document document, List<Tblorderitem> orderItems) throws Exception {
        Font tableHeaderFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
        Font tableBodyFont = new Font(Font.FontFamily.HELVETICA, 11, Font.NORMAL, BaseColor.DARK_GRAY);

        PdfPTable table = new PdfPTable(new float[]{2, 3, 2, 3, 2, 2});
        table.setWidthPercentage(100);
        table.setSpacingBefore(20);
        table.setSpacingAfter(20);

        // Add headers
        String[] headers = {"Item No.", "Material", "Quantity", "Service", "Rate", "Amount"};
        for (String header : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(header, tableHeaderFont));
            cell.setBackgroundColor(PRIMARY_COLOR);
            cell.setPadding(8);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
        }

        // Add items
        boolean alternate = false;
        for (Tblorderitem item : orderItems) {
            Tblservice service = orderItemBean.getAllServiceById(item.getServiceId().getServicesId());
            BaseColor rowColor = alternate ? LIGHT_GRAY : BaseColor.WHITE;
            
            addStyledCell(table, String.valueOf(item.getOrderItemId()), tableBodyFont, rowColor);
            addStyledCell(table, item.getMaterial(), tableBodyFont, rowColor);
            addStyledCell(table, String.valueOf(item.getQty()), tableBodyFont, rowColor);
            addStyledCell(table, item.getServiceId().getServiceType(), tableBodyFont, rowColor);
            addStyledCell(table, String.format("₹%.2f", service.getCharge()), tableBodyFont, rowColor);
            addStyledCell(table, String.format("₹%.2f", item.getQty() * service.getCharge()), tableBodyFont, rowColor);
            
            alternate = !alternate;
        }

        document.add(table);
    }

    private void addStyledCell(PdfPTable table, String content, Font font, BaseColor backgroundColor) {
        PdfPCell cell = new PdfPCell(new Phrase(content, font));
        cell.setBackgroundColor(backgroundColor);
        cell.setPadding(8);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
    }

    private void addTotalSection(Document document, List<Tblorderitem> orderItems) throws Exception {
        Font totalFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, PRIMARY_COLOR);
        
        PdfPTable totalTable = new PdfPTable(2);
        totalTable.setWidthPercentage(40);
        totalTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
        totalTable.setSpacingBefore(20);

        double grandTotal = orderItemBean.calculateGrandTotal(orderItems);
        
        PdfPCell labelCell = new PdfPCell(new Phrase("Grand Total:", totalFont));
        labelCell.setBorder(Rectangle.NO_BORDER);
        labelCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        totalTable.addCell(labelCell);

        PdfPCell amountCell = new PdfPCell(new Phrase(String.format("₹%.2f", grandTotal), totalFont));
        amountCell.setBorder(Rectangle.NO_BORDER);
        amountCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        totalTable.addCell(amountCell);

        document.add(totalTable);
    }

    private void addFooter(Document document) throws Exception {
        Font footerFont = new Font(Font.FontFamily.HELVETICA, 10, Font.ITALIC, BaseColor.GRAY);
        
        Paragraph footer = new Paragraph();
        footer.setSpacingBefore(40);
        footer.setAlignment(Element.ALIGN_CENTER);
        footer.add(new Phrase("Thank you for choosing our service!\n", footerFont));
        footer.add(new Phrase("For any queries, please contact us at (90) 898 789-8957", footerFont));
        
        document.add(footer);
    }
}