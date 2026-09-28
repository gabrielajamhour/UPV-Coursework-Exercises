import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.IntegerProperty;

public class WalletEntry {
    
    private final StringProperty Crypto = new SimpleStringProperty();
    private final IntegerProperty Cantidad = new SimpleIntegerProperty();
    
    public WalletEntry(String crypt, Integer q){
        Crypto.setValue(crypt);
        Cantidad.set(q);
    }
    public final StringProperty CryptoProperty() {
        return this.Crypto;
    }
    public final String getCrypto() {
        return this.CryptoProperty().get();
    }
    public final void setCrypto(final java.lang.String crypt) {
        this.CryptoProperty().set(crypt);
    }
    public final IntegerProperty CantidadProperty() {
        return this.Cantidad;
    }
    public final Integer getCantidad() {
        return this.CantidadProperty().get();
    }
    public final void setCantidad(final java.lang.Integer q) {
        this.CantidadProperty().set(q);
    }
    
    public String toString() {
        return String.format("%-10s%-5s%-4d",this.getCrypto(),"", this.getCantidad());
    }
}
