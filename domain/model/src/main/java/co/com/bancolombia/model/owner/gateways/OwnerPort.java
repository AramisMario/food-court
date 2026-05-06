package co.com.bancolombia.model.owner.gateways;
import co.com.bancolombia.model.owner.Owner;
public interface OwnerPort {
    Owner getOwner(int ownerId);
}
