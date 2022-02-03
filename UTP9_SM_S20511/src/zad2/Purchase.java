/**
 *
 *  @author Smilianets Mykhailo S20511
 *
 */

package zad2;


import java.beans.*;

public class Purchase {
    private String prod;
    private String data;
    private Double price;

    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
    private VetoableChangeSupport vetoableChangeSupport = new VetoableChangeSupport(this);

    public Purchase(String prod, String data, Double price) {
        this.prod = prod;
        this.data = data;
        this.price = price;
    }

    public String getProd() {
        return prod;
    }

    public void setProd(String prod) {
        this.prod = prod;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        String oldData = this.data;
        this.data = data;
        propertyChangeSupport.firePropertyChange("data", oldData, data);
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Purchase [prod=" + this.prod + ", data=" + this.data + ", price=" + this.price + "]";
    }

    public void setPrice(Double price) throws PropertyVetoException {
        Double oldPrice = this.price;
        vetoableChangeSupport.fireVetoableChange("price", oldPrice, price);
        this.price = price;
        propertyChangeSupport.firePropertyChange("price", oldPrice, price);
    }

    public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        propertyChangeSupport.addPropertyChangeListener(propertyChangeListener);
    }

    public void removePropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        propertyChangeSupport.removePropertyChangeListener(propertyChangeListener);
    }

    public void addVetoableChangeListener(VetoableChangeListener vetoableChangeListener) {
        vetoableChangeSupport.addVetoableChangeListener(vetoableChangeListener);
    }

    public void removeVetoableChangeListener(VetoableChangeListener vetoableChangeListener) {
        vetoableChangeSupport.removeVetoableChangeListener(vetoableChangeListener);
    }
}  
