package ua.lviv.home.servlets;

import ua.lviv.home.enteties.Product;
import ua.lviv.home.services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    ProductService productService = ProductService.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String productId = request.getParameter("id");
        Product product = productService.read(Integer.parseInt(productId));
        request.setAttribute("productName", product.getName());
        request.setAttribute("productD", product.getDescription());
        request.setAttribute("productP", product.getPrice());
        request.setAttribute("productId", product.getId());

        request.getRequestDispatcher("singleProduct.jsp").forward(request, response);
    }

    // to get resource (product)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    // to update resource (product)
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        super.doPut(req, resp);
    }

    // to delete resource (product)
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        super.doDelete(req, resp);
    }

}
