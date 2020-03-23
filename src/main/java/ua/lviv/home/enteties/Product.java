package ua.lviv.home.enteties;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Product {
    private Integer id;
    private String name;
    private String description;
    private Double price;

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private int id;
        private String name;
        private String description;
        private double price;

        public Builder setId(int id){
            this.id = id;
            return this;
        }

        public Builder setName(String name){
            this.name = name;
            return this;
        }

        public Builder setDescription(String description){
            this.description = description;
            return this;
        }

        public Builder setPrice(double price){
            this.price = price;
            return this;
        }

        public Product build(){
            Product product = new Product();
            product.setId(id);
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);

            return product;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public static Product of(ResultSet result) {
        try {
            Integer productId = result.getInt("id");
            String name = result.getString("name");
            String description = result.getString("description");
            Double purchasePrice = result.getDouble("price");
            return Product.builder()
                    .setId(productId)
                    .setName(name)
                    .setDescription(description)
                    .setPrice(purchasePrice)
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException("Error");
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + "]";
    }
}
