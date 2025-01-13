/*
 * This file is generated by jOOQ.
 */
package generated_sources.tables.pojos;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private BigDecimal importPrice;
    private BigDecimal sellingPrice;
    private Long soldQuantity;
    private Long quantity;
    private String name;
    private String category;

    public Product() {}

    public Product(Product value) {
        this.id = value.id;
        this.title = value.title;
        this.description = value.description;
        this.createdAt = value.createdAt;
        this.importPrice = value.importPrice;
        this.sellingPrice = value.sellingPrice;
        this.soldQuantity = value.soldQuantity;
        this.quantity = value.quantity;
        this.name = value.name;
        this.category = value.category;
    }

    public Product(
        Long id,
        String title,
        String description,
        LocalDateTime createdAt,
        BigDecimal importPrice,
        BigDecimal sellingPrice,
        Long soldQuantity,
        Long quantity,
        String name,
        String category
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.importPrice = importPrice;
        this.sellingPrice = sellingPrice;
        this.soldQuantity = soldQuantity;
        this.quantity = quantity;
        this.name = name;
        this.category = category;
    }

    /**
     * Getter for <code>public.product.id</code>.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Setter for <code>public.product.id</code>.
     */
    public Product setId(Long id) {
        this.id = id;
        return this;
    }

    /**
     * Getter for <code>public.product.title</code>.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Setter for <code>public.product.title</code>.
     */
    public Product setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Getter for <code>public.product.description</code>.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter for <code>public.product.description</code>.
     */
    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Getter for <code>public.product.created_at</code>.
     */
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Setter for <code>public.product.created_at</code>.
     */
    public Product setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    /**
     * Getter for <code>public.product.import_price</code>.
     */
    public BigDecimal getImportPrice() {
        return this.importPrice;
    }

    /**
     * Setter for <code>public.product.import_price</code>.
     */
    public Product setImportPrice(BigDecimal importPrice) {
        this.importPrice = importPrice;
        return this;
    }

    /**
     * Getter for <code>public.product.selling_price</code>.
     */
    public BigDecimal getSellingPrice() {
        return this.sellingPrice;
    }

    /**
     * Setter for <code>public.product.selling_price</code>.
     */
    public Product setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
        return this;
    }

    /**
     * Getter for <code>public.product.sold_quantity</code>.
     */
    public Long getSoldQuantity() {
        return this.soldQuantity;
    }

    /**
     * Setter for <code>public.product.sold_quantity</code>.
     */
    public Product setSoldQuantity(Long soldQuantity) {
        this.soldQuantity = soldQuantity;
        return this;
    }

    /**
     * Getter for <code>public.product.quantity</code>.
     */
    public Long getQuantity() {
        return this.quantity;
    }

    /**
     * Setter for <code>public.product.quantity</code>.
     */
    public Product setQuantity(Long quantity) {
        this.quantity = quantity;
        return this;
    }

    /**
     * Getter for <code>public.product.name</code>.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for <code>public.product.name</code>.
     */
    public Product setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Getter for <code>public.product.category</code>.
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * Setter for <code>public.product.category</code>.
     */
    public Product setCategory(String category) {
        this.category = category;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Product other = (Product) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.title == null) {
            if (other.title != null)
                return false;
        }
        else if (!this.title.equals(other.title))
            return false;
        if (this.description == null) {
            if (other.description != null)
                return false;
        }
        else if (!this.description.equals(other.description))
            return false;
        if (this.createdAt == null) {
            if (other.createdAt != null)
                return false;
        }
        else if (!this.createdAt.equals(other.createdAt))
            return false;
        if (this.importPrice == null) {
            if (other.importPrice != null)
                return false;
        }
        else if (!this.importPrice.equals(other.importPrice))
            return false;
        if (this.sellingPrice == null) {
            if (other.sellingPrice != null)
                return false;
        }
        else if (!this.sellingPrice.equals(other.sellingPrice))
            return false;
        if (this.soldQuantity == null) {
            if (other.soldQuantity != null)
                return false;
        }
        else if (!this.soldQuantity.equals(other.soldQuantity))
            return false;
        if (this.quantity == null) {
            if (other.quantity != null)
                return false;
        }
        else if (!this.quantity.equals(other.quantity))
            return false;
        if (this.name == null) {
            if (other.name != null)
                return false;
        }
        else if (!this.name.equals(other.name))
            return false;
        if (this.category == null) {
            if (other.category != null)
                return false;
        }
        else if (!this.category.equals(other.category))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.title == null) ? 0 : this.title.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.importPrice == null) ? 0 : this.importPrice.hashCode());
        result = prime * result + ((this.sellingPrice == null) ? 0 : this.sellingPrice.hashCode());
        result = prime * result + ((this.soldQuantity == null) ? 0 : this.soldQuantity.hashCode());
        result = prime * result + ((this.quantity == null) ? 0 : this.quantity.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.category == null) ? 0 : this.category.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Product (");

        sb.append(id);
        sb.append(", ").append(title);
        sb.append(", ").append(description);
        sb.append(", ").append(createdAt);
        sb.append(", ").append(importPrice);
        sb.append(", ").append(sellingPrice);
        sb.append(", ").append(soldQuantity);
        sb.append(", ").append(quantity);
        sb.append(", ").append(name);
        sb.append(", ").append(category);

        sb.append(")");
        return sb.toString();
    }
}